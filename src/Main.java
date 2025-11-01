/*
 * Project: SIMULATOR DE CALATORIE
 * Autor: Ciumac Andreea
 * Descriere: Calculează distanța, timpul și costul combustibilului pentru mai multe călătorii.
 * Scop: aplicarea expresiilor matematice, tipurilor numerice și conversiilor .
 */

import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner cit = new Scanner(System.in);

        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String BLUE = "\u001B[34m";
        final String PURPLE = "\u001B[35m";
        final String CYAN = "\u001B[36m";
        final String WHITE = "\u001B[37m";

        int  days, hours, min;
        int num,  comtip;
        double avrspeed, fuellitcost = 0, fuelcon;
        int trtimeh, trtimem;
        BigDecimal totfuelcost = BigDecimal.valueOf(0);
        BigDecimal exptrip = BigDecimal.valueOf(0);
        BigDecimal longtrip = BigDecimal.valueOf(0);
        int trav = 1;

        System.out.println(CYAN + "\n═══════════════════════════════════════════════════════════" + RESET);
        System.out.println(BLUE + "          🚗 BUN VENIT IN SIMULATORUL DE CALATORIE          " + RESET);
        System.out.println(CYAN + "═══════════════════════════════════════════════════════════\n" + RESET);

        do {
            days = hours = min = 0;
            System.out.println(PURPLE + "\n--- ✈️  Calatoria #" + trav + " ---" + RESET);

            int vit;
            do {
                System.out.println(YELLOW + "Doriti sa introduceti viteza in:\n" + "1 - km/h\n" + "2 - mph" + RESET);
                vit = cit.nextInt();
            } while (vit != 1 && vit != 2);

            System.out.print(BLUE + "Introduceti viteza medie: " + RESET);
            avrspeed = cit.nextDouble();
            while (avrspeed <= 0 || avrspeed > 300) {
                System.out.print(RED + "❌ Viteza invalida. Introduceti o valoare intre 1 si 300: " + RESET);
                avrspeed = cit.nextDouble();
            }

            if (vit == 2) {
                avrspeed *= 1.60934;
                System.out.println(GREEN + "Viteza convertita in km/h: " + avrspeed + RESET);
            }

            System.out.print(BLUE + "Introduceti consumul combustibilului (litri/100km): " + RESET);
            fuelcon = cit.nextDouble();
            while (fuelcon <= 0 || fuelcon > 50) {
                System.out.print(RED + "❌ Consumul introdus nu este valid. Introduceti o valoare intre 1 si 50: " + RESET);
                fuelcon = cit.nextDouble();
            }

            System.out.print(BLUE + "Introduceti timpul - ore: " + RESET);
            trtimeh = cit.nextInt();
            while (trtimeh > 50 || trtimeh < 0) {
                System.out.print(RED + "❌ Introduceti o valoare intre 0 si 50 ore: " + RESET);
                trtimeh = cit.nextInt();
            }

            System.out.print(BLUE + "Introduceti timpul - minute: " + RESET);
            trtimem = cit.nextInt();
            while (trtimem > 250 || trtimem < 0) {
                System.out.print(RED + "❌ Introduceti o valoare intre 0 si 250 minute: " + RESET);
                trtimem = cit.nextInt();
            }

            double totalHours = trtimeh + (trtimem / 60.0);
            double totdis = avrspeed * totalHours;
            double timemin = totalHours * 60;
            double timesec = timemin * 60;




            int totalMinutes = (int) Math.round(totalHours * 60);

            days = totalMinutes / (24 * 60);
            hours = (totalMinutes / 60) % 24;
            min = totalMinutes % 60;



            System.out.println(CYAN + "\n🔋 Alegeti tipul de combustibil:" + RESET);
            do {
                System.out.println("1 - Benzină (27.5 MDL/l)\n2 - Motorină (26.0 MDL/l)\n3 - Electric (0.30 MDL/km)");
                comtip = cit.nextInt();
            } while (comtip != 1 && comtip != 2 && comtip != 3);

            switch (comtip) {
                case 1:
                    System.out.println("⛽ Tipul selectat: Benzina.");
                    System.out.print(BLUE + "Introduceti costul combustibilului per litru: " + RESET);
                    fuellitcost = cit.nextDouble();
                    while (fuellitcost <= 0 || fuellitcost > 30) {
                        System.out.print(RED + "❌ Valoare invalida. Introduceti intre 1 si 30 lei/litru: " + RESET);
                        fuellitcost = cit.nextDouble();
                    }
                    break;
                case 2:
                    System.out.println("🛢️ Tipul selectat: Motorina.");
                    System.out.print(BLUE + "Introduceti costul combustibilului per litru: " + RESET);
                    fuellitcost = cit.nextDouble();
                    while (fuellitcost <= 0 || fuellitcost > 30) {
                        System.out.print(RED + "❌ Valoare invalida. Introduceti intre 1 si 30 lei/litru: " + RESET);
                        fuellitcost = cit.nextDouble();
                    }
                    break;
                case 3:
                    System.out.println("⚡ Tipul selectat: Electric.");
                    do {
                        System.out.print(BLUE + "Introduceti costul per km: " + RESET);
                        fuellitcost = cit.nextDouble();
                    } while (fuellitcost <= 0 || fuellitcost > 2);
                    fuellitcost = fuellitcost * totdis;
                    break;
            }

            BigDecimal distanta = BigDecimal.valueOf(totdis);
            BigDecimal consum = BigDecimal.valueOf(fuelcon);
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
            if (longtrip.compareTo( distanta) <0) longtrip = distanta;
            if (exptrip.compareTo(fuelCost) < 0) exptrip = fuelCost;

            System.out.println(GREEN + "\n✅ Rezultatele calatoriei #" + trav + ":" + RESET);
            System.out.printf("📏 Distanta totala: %.2f km%n", totdis);
            System.out.printf("⏱️  Timp total: %.0f minute (%.0f secunde)%n", timemin, timesec);
            System.out.println("⛽ Combustibil necesar: " + fuelneces + " litri");
            System.out.println("💰 Cost combustibil: " + fuelCost + " MDL");
            System.out.println("🕒 Durata: " + days + " zile, " + hours + " ore, " + min + " minute");

            System.out.print(YELLOW + "\nDoriti sa calculati o alta calatorie? (1 = Da, 0 = Nu): " + RESET);
            num = cit.nextInt();
            trav++;

            if (trav == 6) {
                System.out.println(RED + "\n⚠️ Ati ajuns la numarul maxim de calatorii posibile. Sistemul se va inchide." + RESET);
                num = 0;
            }

        } while (num == 1);


        System.out.println(CYAN + "\n═══════════════════════════════════════════════════════════" + RESET);
        System.out.println(BLUE + "                🧭 REZUMATUL CALATORIILOR                  " + RESET);
        System.out.println(CYAN + "═══════════════════════════════════════════════════════════" + RESET);
        System.out.println(GREEN + "Total cost combustibil: " + RESET + totfuelcost + " MDL");
        System.out.println(GREEN + "Cea mai lunga calatorie: " + RESET + longtrip + " km");
        System.out.println(GREEN + "Cea mai scumpa calatorie: " + RESET + exptrip + " MDL");
        System.out.println(GREEN + "Numarul total de calatorii: " + RESET + (trav - 1));
        System.out.println(GREEN + "Distanta medie per calatorie: " + RESET + (longtrip.divide(BigDecimal.valueOf((trav - 1)), 2, RoundingMode.HALF_UP))  + " km");
        System.out.println(GREEN + "Cost mediu per calatorie: " + RESET + totfuelcost.divide(BigDecimal.valueOf(trav - 1), 2, RoundingMode.HALF_UP) + " MDL");
        System.out.println(CYAN + "═══════════════════════════════════════════════════════════" + RESET);

        // Exit animation ✨
        System.out.print(YELLOW + "\nInchidere program" + RESET);
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            Thread.sleep(500);
        }
        System.out.println("\n" + BLUE + "La revedere! 🌍" + RESET);

        cit.close();
    }
}
