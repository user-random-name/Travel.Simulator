/*
*/

import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        Scanner cit = new Scanner(System.in);
        int num, days, hours, min,comtip;
        double avrspeed, fuellitcost =0, fuelcon, longtrip=0;
        int trtimeh, trtimem;
        BigDecimal totfuelcost= BigDecimal.valueOf(0);
        BigDecimal exptrip = BigDecimal.valueOf(0);
        int trav=1;

        do {
            days = hours = min = 0;

            System.out.println("--- Calatoria #"+trav+" ---");

            int vit;
            do {
                System.out.println("Doriti sa introduceti viteza in:\n" + "1 - km/h\n" + "2 - mph ");
                vit = cit.nextInt();
            } while (vit != 1 && vit != 2);

            System.out.println("Introduceti viteza medie: ");
            avrspeed = cit.nextDouble();
            while (avrspeed <= 0 || avrspeed > 300) {
                System.out.println("Viteza introdusa nu este valida. Introduceti o valoare intre 1 si 300.");
                avrspeed = cit.nextDouble();
            }

            if (vit == 2) {
                avrspeed *= 1.60934;
                System.out.println("Viteza convertita in km/h: " + avrspeed);
            }

            System.out.println("Introduceti consumul combustibilului (litri/100km):");
            fuelcon = cit.nextDouble();
            while (fuelcon <= 0 || fuelcon > 10000) {
                System.out.println("Consumul introdus nu este valid. Introduceti o valoare intre 1 si 10000.");
                fuelcon = cit.nextDouble();
            }

            System.out.println("Introduceti timpul - ore: ");
            trtimeh = cit.nextInt();
            while (trtimeh > 100 || trtimeh < 0) {
                System.out.println("Timpul introdus nu este valid. Introduceti o valoare intre 1 si 100 ore.");
                trtimeh = cit.nextInt();
            }

            System.out.println("minute: ");
            trtimem = cit.nextInt();
            while (trtimem > 500 || trtimem < 0) {
                System.out.println("Timpul introdus nu este valid. Introduceti o valoare intre 0 si 500 minute.");
                trtimem = cit.nextInt();
            }

            if (trtimeh >= 24) {
                days = trtimeh / 24;
                hours = trtimeh % 24;
            }

            if (trtimem >= 60) {
                hours += trtimem / 60;
                min = trtimem % 60;
            } else {
                min = trtimem;
            }

            double totalHours = trtimeh + (trtimem / 60.0);
            double totdis = avrspeed * totalHours;
            double timemin = totalHours * 60;
            double timesec = timemin * 60;


            System.out.println("Alegeti tipul de combustibil a automobilului ");
            do {
                System.out.println("1 - Benzină (27.5 MDL/l)\n" + "2 - Motorină (26.0 MDL/l)\n" + "3 - Electric (0.30 MDL/km)");
                comtip = cit.nextInt();
            } while (comtip != 1 && comtip != 2 && comtip != 3);
            switch (comtip) {
                case 1:
                    System.out.println("Tipul de combustibil ales este Benzina (media costului combustibilui este in jur de 27.5 MDL/l).");
                    System.out.println("Introduceti costul combustibilului per litru (disponibil astăzi):");
                    fuellitcost = cit.nextDouble();
                    while (fuellitcost <= 0 || fuellitcost > 30) {
                        System.out.println("Costul introdus nu este valid. Introduceti o valoare intre 1 si 30 lei/litru.");
                        fuellitcost = cit.nextDouble();
                    }
                    break;
                case 2:
                    System.out.println("Tipul de combustibil ales este Motorina (media costului combustibilui este in jur de 26.0 MDL/l).");
                    System.out.println("Introduceti costul combustibilului per litru (disponibil astăzi):");
                    fuellitcost = cit.nextDouble();
                    while (fuellitcost <= 0 || fuellitcost > 30) {
                        System.out.println("Costul introdus nu este valid. Introduceti o valoare intre 1 si 30 lei/litru.");
                        fuellitcost = cit.nextDouble();
                    }
                    break;
                case 3:
                    System.out.println("Tipul de combustibil ales este Electric (media costului combustibilui este in jur de 0.30 MDL/km).");
                    do {
                        System.out.println("Introduceti costul per km:");
                        fuellitcost = cit.nextDouble();
                    } while (fuellitcost <= 0 || fuellitcost > 2);
                    fuellitcost = fuellitcost * totdis;
                    break;

            }

            BigDecimal distanta = BigDecimal.valueOf(totdis);
            BigDecimal consum= BigDecimal.valueOf(fuelcon);
            BigDecimal fuelpret = BigDecimal.valueOf(fuellitcost);
            BigDecimal fuelneces = distanta.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP).multiply(consum);
            BigDecimal fuelCost;
            if (comtip == 3) {
                fuelCost = BigDecimal.valueOf(fuellitcost);
                fuelneces = BigDecimal.ZERO;
            } else {
                fuelCost = fuelneces.multiply(fuelpret);
            }

            fuelneces = fuelneces.setScale(2, RoundingMode.HALF_UP);
            fuelCost = fuelCost.setScale(2, RoundingMode.HALF_UP);


            totfuelcost = totfuelcost.add(fuelCost);
            if(longtrip < totdis){
                longtrip = totdis;
            }
            if(exptrip.compareTo(fuelCost) < 0){
                exptrip = fuelCost;
            }

            System.out.println("Distanta totala parcursa: " + totdis + " km");
            System.out.println("Timp total: " + timemin + " minute (" + timesec + " secunde)");
            System.out.println("Combustibil necesar: " + fuelneces + " litri");
            System.out.println("Cost combustibil: " + fuelCost + " MDL");
            System.out.println(days + " zile, " + hours + " ore, " + min + " minute");

            System.out.println("Doriti sa calculati o alta calatorie? (1 = Da, 0 = Nu)");
            num = cit.nextInt();
            trav++;
            if(trav==6){
                System.out.println("Ati ajuns la numarul maxim de calatorii posibile de introdus. Sistemul se va inchide.");
            num=0;
            }

        } while (num == 1);

        System.out.println("Costul combustibilului pe parcursula tuturor calatoriilor este : "+totfuelcost+" MDL");
        System.out.println("Cea mai lunga calatorie a fost de : "+longtrip+" km");
        System.out.println("Cea mai scumpa calatorie a fost de : "+exptrip+" MDL");
        System.out.println("Numarul total de calatorii: " + (trav - 1));
        System.out.println("Distanta medie per calatorie: " + (longtrip / (trav - 1)) + " km");
        System.out.println("Costul mediu per calatorie: " + totfuelcost.divide(BigDecimal.valueOf(trav - 1), 2, RoundingMode.HALF_UP) + " MDL");


        cit.close();
            
        }
    }
