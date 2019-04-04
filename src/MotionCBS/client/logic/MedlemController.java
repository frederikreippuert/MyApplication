package MotionCBS.client.logic;


import MotionCBS.server.Medlem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MedlemController {
    Scanner input = new Scanner(System.in);

    //Attributer oprettes
    private Data data;
    private Medlem nuvaerendeMedlem;


    //Constructor oprettes
    public MedlemController (Data data, Medlem nuvaerendeMedlem) {
        this.data = data;
        this.nuvaerendeMedlem = nuvaerendeMedlem;
    }


    //Først printes medlemsmenuen ud.
    public void visMedlemMenu() {
        do {    //Løkken kører så længe nuværende medlem ikke er null.
            int valg = -1;
            System.out.println("****************************************");
            System.out.println("*                                      *");
            System.out.println("* VELKOMMEN TIL MOTION CBS BRUGERPORTAL*");
            System.out.println("*                                      *");
            System.out.println("****************************************");
            System.out.println("Hvad ønsker du at gøre?");
            System.out.println("\t1. Vis alle dine oplysninger");
            System.out.println("\t2. Ændr i dine oplysninger");
            System.out.println("\t3. Slet dig som medlem");
            System.out.println("\t0. Log ud");

            //Try-catch: hvis brugeren ikke indtaster et gyldigt valg, bliver løkken kørt igen.
            try {
                valg = input.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Dette er ikke en mulighed... Prøv igen");
            }

            //Switchen indeholder de 4 cases, som brugeren kan vælge, der kører en metode hver.
            switch (valg) {
                case 1:
                    visOplysninger();
                    break;
                case 2:
                    aendrOplysinger();
                    break;
                case 3:
                    sletMedlem();
                    break;
                case 0:     //Sætter nuvaerende medlem lig null, som er betingelsen for at slutte løkken.
                    System.out.println("\nDu bliver nu viderestillet til programmets hovedmenu...\n");
                    nuvaerendeMedlem = null;
                    break;
                default:
                    System.out.println("\"Du har indtastet et ugyldigt valg... Prøv igen");
                    break;
            }
        } while (nuvaerendeMedlem != null);
    }

    //Case1: Printer informationen for nuværende medlem, ved brug af getters.
    public void visOplysninger() {
        int i = 1;
        System.out.println("\n\nHer er de oplysninger, vi har registreret på dig:\n");
        System.out.printf("%-15s%-15s%-15s%-18s%-15s%-15s%-15s%-20s\n", "Nr.", "Fornavn:", "Efternavn:", "Alder:", "Køn:", "Medlemstype:", "Brugernavn:", "Password:");
        System.out.printf("%-15s%-15s%-15s%-18s%-15s%-15s%-15s%-20s\n\n", i, (getNuvaerendeMedlem().getFornavn()), getNuvaerendeMedlem().getEfternavn(),
                getNuvaerendeMedlem().getAlder(), getNuvaerendeMedlem().getKoen(), getNuvaerendeMedlem().getMedlemstype(), getNuvaerendeMedlem().getUsername(),
                getNuvaerendeMedlem().getPassword());
        System.out.println("Du viderestilles nu tilbage til din personlige menu");
        visMedlemMenu();
    }


    //Case2: Ændrer i medlemmets oplysninger. Først printes medlemmets oplysninger ved brug af getters.
    public void aendrOplysinger() {
        System.out.println("\n\nHer er de oplysninger, vi har registreret på dig:\n");
        int i = 1;
        System.out.printf("%-15s%-15s%-15s%-18s%-15s%-15s%-15s%-20s\n", "Nr.", "Fornavn:", "Efternavn:", "Alder:", "Køn:", "Medlemstype:", "Brugernavn:", "Password:");
        System.out.printf("%-15s%-15s%-15s%-18s%-15s%-15s%-15s%-20s\n", i, (nuvaerendeMedlem.getFornavn()), nuvaerendeMedlem.getEfternavn(),
                nuvaerendeMedlem.getAlder(), nuvaerendeMedlem.getKoen(), nuvaerendeMedlem.getMedlemstype(), nuvaerendeMedlem.getUsername(),
                nuvaerendeMedlem.getPassword());

        //Printer ud hvad der ønskes ændret.
        System.out.println("\nHvad ønsker du at ændre?");
        System.out.println("\t1. Fornavn");
        System.out.println("\t2. Efternavn");
        System.out.println("\t3. Alder");
        System.out.println("\t4. Køn");
        System.out.println("\t5. Medlemstype");
        System.out.println("\t6. Brugernavn");
        System.out.println("\t7. Password");
        System.out.println("\t8. Gå tilbage til din personlige medlemsmenu");

        //Try-catch sikrer at valget er gyldigt.
        int valg = -1;
        try {
            valg = input.nextInt();
        } catch (InputMismatchException e) {
            input.nextLine();
            System.out.println("Dette er ikke en mulighed... Prøv igen");
            aendrOplysinger();
        }

        //Cases til de valg der kan foretages. Fælles for casene er, at de printer de nuværende værdier ud, og
        //opretter en variabel at gemme String/int at gemme inputet.next() i. Så kaldes en getter for nuværeende
        //medlem og en setter for at gemme den indtastede værdi, hvorefter den bliver printet ud igen.
        switch (valg) {
            case 1:
                System.out.println("Dit nuværende fornavn er:\n" + nuvaerendeMedlem.getFornavn());
                System.out.println("\nIndtast nyt fornavn: ");
                String nytFornavn = input.next();
                getNuvaerendeMedlem().setFornavn(nytFornavn);
                System.out.println("Dit fornavn er nu ændret til:\n" + nuvaerendeMedlem.getFornavn());
                break;
            case 2:
                System.out.println("Dit nuværende efternavn er:\n" + nuvaerendeMedlem.getEfternavn());
                System.out.println("\nIndtast nyt efternavn: ");
                String nytEfternavn = input.next();
                getNuvaerendeMedlem().setEfternavn(nytEfternavn);
                System.out.println("Dit efternavn er nu ændret til:\n " + nuvaerendeMedlem.getEfternavn());
                break;
            case 3:
                System.out.println("Din nuværende alder er:\n" + nuvaerendeMedlem.getAlder());
                System.out.println("\nIndtast ny alder: ");
                int nyAlder = input.nextInt();
                getNuvaerendeMedlem().setAlder(nyAlder);
                System.out.println("Din alder er nu ændret til: \n" + nuvaerendeMedlem.getAlder());
                break;
            case 4:
                System.out.println("Dit nuværende køn er:\n" + nuvaerendeMedlem.getKoen());
                System.out.println("\nIndtast nyt køn (Mand eller kvinde)");
                String nytKoen = input.next();
                getNuvaerendeMedlem().setKoen(nytKoen);
                System.out.println("Dit køn er nu ændret til: " + nuvaerendeMedlem.getKoen());
                break;
            case 5:
                System.out.println("Din nuværende medlemstype er:\n" + nuvaerendeMedlem.getMedlemstype());
                System.out.println("\nIndtast ny medlemstype");
                int skiftMedlemstype = input.nextInt();
                getNuvaerendeMedlem().setMedlemstype(skiftMedlemstype);
                System.out.println("Din medlemstype er nu ændret til: \n" + nuvaerendeMedlem.getMedlemstype());
                break;
            case 6:
                System.out.println("Dit nuværende brugernavn er:\n" + nuvaerendeMedlem.getUsername());
                System.out.println("\nIndtast nyt brugernavn");
                String nytBrugernavn = input.next();
                getNuvaerendeMedlem().setUsername(nytBrugernavn);
                System.out.println("Dit nye brugernavn er: \n" + nuvaerendeMedlem.getUsername());
                break;
            case 7:
                System.out.println("Dit nuværende password er:\n" + nuvaerendeMedlem.getPassword());
                System.out.println("\nIndtast nyt password");
                String nytPassword = input.next();
                getNuvaerendeMedlem().setPassword(nytPassword);
                System.out.println("Dit nye password er: \n" + nuvaerendeMedlem.getPassword());
                break;
            case 8:
                visMedlemMenu();
                break;
            default:
                System.out.println("Ugyldigt valg prøv igen");
                aendrOplysinger();
        }

        //Pompter brugeren om der ønskes flere ændringer.
        System.out.println("\nØnsker du at ændre mere?");
        System.out.println("\t1. Ja");
        System.out.println("\t2. Nej");

        //Den samme try-catch, som ovenover.
        int valg1 = -1;
        try {
            valg1 = input.nextInt();
        } catch (InputMismatchException e) {
            input.nextLine();
            System.out.println("Dette er ikke en mulighed... Prøv igen");
            aendrOplysinger();
        }

        //Switch statement med 2 cases, som kører aendreOplysninger eller visMedlemMenu baseret på hvad brugeren vælger.
        switch (valg1) {
            case 1:
                aendrOplysinger();
                break;
            case 2:
                visMedlemMenu();
        }
    }



    //Case 3: Sletter et medlem.
    public void sletMedlem() {
        //Først printes en en advarselsbesked
        System.out.println("Ønsker du at slette dig som medlem?");
        System.out.println("1. Ja");
        System.out.println("0. Gå tilbage til din personlige medlemsmenu");

        //Try-catch bruges til at sikre at valget er gyldigt.
        int valg = -1;
        try {
            valg = input.nextInt();
        } catch (InputMismatchException e) {
            input.nextLine();
            System.out.println("Dette er ikke en mulighed... Prøv igen");
            sletMedlem();
        }

        //Switch sataement med to muligheder.
        switch (valg) {
            case 1: //Sletter medlemmet ved brug af en getter og metoden .remove(), der sletter det element, der
                // indeholder nuværende medlem
                this.data.getMedlemmer().remove(nuvaerendeMedlem);
                System.out.println("Du er nu slettet fra systemet... Viderestiller til hovedmenu");
                nuvaerendeMedlem = null;
                break;

            case 0: //kører visMedlemMenu metoden.
                System.out.println("Du bliver nu sendt tilbage til din personlige medlemsmenu...\n");
                visMedlemMenu();
                break;
        }

    }

    //Get+set for nuværende medlem
    public Medlem getNuvaerendeMedlem() {
        return nuvaerendeMedlem;
    }

    public void setNuvaerendeMedlem(Medlem nuvaerendeMedlem) {
        this.nuvaerendeMedlem = nuvaerendeMedlem;
    }


}
