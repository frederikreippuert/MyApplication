package MotionCBS.client.logic;

import MotionCBS.server.Medlem;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class AdminController {
    Scanner input = new Scanner(System.in);
    private ArrayList<Medlem> medlemmer = new ArrayList<>();


    //Opretter attributter
    private Data data;
    private Medlem nuvaerendeMedlem;
    private Medlem medlem;
    private MedlemController medlemController;

    //Opretter en constructor
    public AdminController(Data data, Medlem nuvaerendeMedlem) {
        this.data = data;
        this.nuvaerendeMedlem = nuvaerendeMedlem;
        this.medlemController = new MedlemController(data, nuvaerendeMedlem);
    }


    //Først printes adminmenuen ud
    public void visAdminMenu() {
        do {    //Løkken kører så længe nuværende medlem ikke er null.
            int valg = -1;
            System.out.println("****************************************");
            System.out.println("*                                      *");
            System.out.println("* VELKOMMEN TIL MOTION CBS ADMINPORTAL *");
            System.out.println("*                                      *");
            System.out.println("****************************************");
            System.out.println("Hvad ønsker du at gøre?");
            System.out.println("\t1. Opret et medlem");
            System.out.println("\t2. Ændr i et medlems oplysninger");
            System.out.println("\t3. Slet et medlem");
            System.out.println("\t4. Vis oplysninger om et medlem");
            System.out.println("\t5. Vis alle oplysninger om alle medlemmer");
            System.out.println("\t6. Vis alle oplysninger om alle medlemmer sorteret efter medlemstype");
            System.out.println("\t7. Vis statistik over aldersfordelingen og køn på hver medlemstype");
            System.out.println("\t0. Log ud");

            ////Try-catch: hvis brugeren ikke indtaster et gyldigt valg, bliver løkken kørt igen.
            try {
                valg = input.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Du har indtastet et ugyldigt valg... Prøv igen");
                visAdminMenu();
            }

            //Switchen indeholder de 8 cases, som brugeren kan vælge, der kører en metode hver.
            switch (valg) {
                case 1:
                    opretMedlemAdmin();
                    break;
                case 2:
                    aendrMedlem();
                    break;
                case 3:
                    sletMedlem();
                    break;
                case 4:
                    visMedlem();
                    break;
                case 5:
                    visAlleMedlemmer();
                    break;
                case 6:
                    statistik();
                    break;
                case 7:
                    medlemStatistik();
                    break;
                case 0: //Kører logAf metoden, som sætter nuværende medlem lig null.
                    logAf();
                    break;

                default:
                    System.out.println("Du har indtastet et ugyldigt valg... Prøv igen");
                    break;
            }
        } while (nuvaerendeMedlem != null);

    }

    //Case1: Opretter et medlem.
    public void opretMedlemAdmin() {

        //Attributter oprettes
        String fornavn;
        String efternavn;
        int alder;
        String koen;
        int medlemstype;
        String username;
        String password;

        //Brugeren promptes og intaster de værdier, der skal knyttes til medlemmet.
        System.out.print("Indtast fornavn: ");
        fornavn = input.next();
        System.out.print("Indtast efternavn: ");
        efternavn = input.next();
        System.out.print("Indtast alder: ");
        alder = input.nextInt();
        System.out.print("Indtast køn (Mand eller kvinde): ");
        koen = input.next();
        System.out.print("Vælg den ønskede medlemstype: \n");
        System.out.print("\t(1) Medlemstype 1: Fitnesstræning\n");
        System.out.print("\t(2) Medlemstype 2: Holdtræning\n");
        System.out.print("\t(3) Medlemstype 3: Hold- og fitnesstræning\n");
        medlemstype = input.nextInt();
        System.out.print("Opret et brugernavn: ");
        username = input.next();
        System.out.print("Opret et password: ");
        password = input.next();

        //Tjekker om brugernavnet er taget
        while (!checkUsername2(username))
            username = input.next();

        //Opretter et objekt af medlemmet
        Medlem medlemmer = new Medlem(fornavn, efternavn, alder, koen, medlemstype, username, password);


        //Tilføjer det nylige oprettet medlem til ArrayListen medlemmer
        this.data.getMedlemmer().add(medlemmer);
        System.out.print("\nMedlemmet er nu oprettet. Du viderestilles nu til adminportalen\n");
        visAdminMenu();
    }


    //Case2: Ændrer i et medlem.
    public void aendrMedlem() {

        //der oprettes to variable
        int indexMedlemAtAendre;
        int i = 0;

        //En formateret string printes ud, der fungerer som header
        System.out.printf(" %-15s%-15s%-15s%-18s%-15s%-15s %-15s%-20s\n", "Nr.", "Fornavn:", "Efternavn:", "Alder:", "Køn:", "Medlemstype:", "Brugernavn:", "Password:");

        //for løkke der printer alle medlemmer ud, hvor i er index-nummeret i Arraylisten.
        for (Medlem medlemmer : data.getMedlemmer()) {
            System.out.printf("%-15s %-15s %-15s %-15s%-15s%-15s%-15s%-20s\n", i, medlemmer.getFornavn(), medlemmer.getEfternavn(),
                    medlemmer.getAlder(), medlemmer.getKoen(), medlemmer.getMedlemstype(), medlemmer.getUsername(),
                    medlemmer.getPassword());
            i++;
        }

        System.out.println("Vælg ID-nummer på den bruger, du ønsker at ændre: ");
        indexMedlemAtAendre = -1;
        //Try-Catch, hvis der bliver indtastet noget andet end en integer
        try {
            indexMedlemAtAendre = input.nextInt();
        } catch (InputMismatchException e) {
            input.nextLine();
            System.out.println("Du har indtastet et ugyldigt valg.. Prøv igen\n");
            aendrMedlem();
        }

        //nuværende medlem bliver via en getter det medlem i arraylisten, der har det index, som er lig indexMedlemAtAendre.
        nuvaerendeMedlem = this.data.getMedlemmer().get(indexMedlemAtAendre);
        medlemController.setNuvaerendeMedlem(nuvaerendeMedlem);

        //Valg printes ud
        System.out.println("Hvad ønsker du at ændre?");
        System.out.println("\t1. Fornavn");
        System.out.println("\t2. Efternavn");
        System.out.println("\t3. Alder");
        System.out.println("\t4. Køn");
        System.out.println("\t5. Medlemstype");
        System.out.println("\t6. Brugernavn");
        System.out.println("\t7. Password");
        System.out.println("\t8. Gå tilbage til administratormenuen");
        int valg = -1;

        //Try-Catch, hvis der bliver indtastet noget andet end en integer
        try {
            valg = input.nextInt();
        } catch (InputMismatchException e) {
            input.nextLine();
            System.out.println("\nDu har indtastet et ugyldigt valg.. Prøv igen\n");
            aendrMedlem();
        }

        //Cases til de valg der kan foretages. Fælles for casene er, at de printer de nuværende værdier ud, og
        //opretter en variabel at gemme String/int at gemme inputet.next() i. Så kaldes en getter for nuværeende
        //medlem og en setter for at gemme den indtastede værdi, hvorefter den bliver printet ud igen.
        switch (valg) {
            case 1:
                System.out.println("Brugerens nuværende fornavn er: " + medlemController.getNuvaerendeMedlem().getFornavn());
                System.out.println("Indtast nyt fornavn: ");
                String nytFornavn = input.next();
                medlemController.getNuvaerendeMedlem().setFornavn(nytFornavn);
                System.out.println("Fornavnet er nu ændret til " + medlemController.getNuvaerendeMedlem().getFornavn());
                break;
            case 2:
                System.out.println("Brugerens nuværende efternavn er: " + medlemController.getNuvaerendeMedlem().getEfternavn());
                System.out.println("Indtast nyt efternavn: ");
                String nytEfternavn = input.next();
                medlemController.getNuvaerendeMedlem().setEfternavn(nytEfternavn);
                System.out.println("Efternavnet er nu ændret til: " + medlemController.getNuvaerendeMedlem().getEfternavn());
                break;
            case 3:
                System.out.println("Brugerens nuværende alder er: " + medlemController.getNuvaerendeMedlem().getAlder());
                System.out.println("Indtast ny alder: ");
                int nyAlder = input.nextInt();
                medlemController.getNuvaerendeMedlem().setAlder(nyAlder);
                System.out.println("Alderen er ændret til: " + medlemController.getNuvaerendeMedlem().getAlder());
                break;
            case 4:
                System.out.println("Brugerens nuværende køn er: " + medlemController.getNuvaerendeMedlem().getKoen());
                System.out.println("Indtast nyt køn (Mand/Kvinde)");
                String nytKoen = input.next();
                medlemController.getNuvaerendeMedlem().setKoen(nytKoen);
                System.out.println("Kønnet er nu ændret til: " + medlemController.getNuvaerendeMedlem().getKoen());
                break;
            case 5:
                System.out.println("Brugerens nuværende medlemstype er: " + medlemController.getNuvaerendeMedlem().getMedlemstype());
                System.out.println("Indtast ny medlemstype (1, 2 eller 3");
                int skiftMedlemstype = input.nextInt();
                medlemController.getNuvaerendeMedlem().setMedlemstype(skiftMedlemstype);
                System.out.println("Medlemstypen er nu ændret til: " + medlemController.getNuvaerendeMedlem().getMedlemstype());
                break;
            case 6:
                System.out.println("Brugerens nuværende brugernavn er: " + medlemController.getNuvaerendeMedlem().getUsername());
                System.out.println("Indtast nyt brugernavn");
                String nytBrugernavn = input.next();
                medlemController.getNuvaerendeMedlem().setUsername(nytBrugernavn);
                System.out.println("Det nye brugernavn er: " + medlemController.getNuvaerendeMedlem().getUsername());
                break;
            case 7:
                System.out.println("Brugerens nuværende password er: " + medlemController.getNuvaerendeMedlem().getPassword());
                System.out.println("Indtast nyt password");
                String nytPassword = input.next();
                medlemController.getNuvaerendeMedlem().setPassword(nytPassword);
                System.out.println("Det nye brugernavn er: " + medlemController.getNuvaerendeMedlem().getPassword());
                break;
            case 8:
                visAdminMenu(); //Kører visAdminMenuen
                break;
        }

        //Brugeren promptes om der skal ændres mere.
        System.out.println("\nØnsker du at ændre mere?");
        System.out.println("\t1. Ja");
        System.out.println("\t2. Nej");
        int valg1 = -1;

        //Try-Catch, hvis der bliver indtastet noget andet end en integer
        try {
            valg1 = input.nextInt();
        } catch (InputMismatchException e) {
            input.nextLine();
            System.out.println("Du har indtastet et ugyldigt valg.. Prøv igen");
            aendrMedlem();
        }

        //Switch statement med 2 cases, som kører aendreMedlem eller visAdminMenu baseret på hvad brugeren vælger.
        switch (valg1) {
            case 1:
                aendrMedlem();
                break;
            case 2:
                System.out.println("Du bliver nu viderestillet til administratormenuen...\n");
                visAdminMenu();
                break;
            default:
                System.out.println("Du har indtastet et ugyldigt tal... Prøv igen");
        }

    }

    //Case3: Sletter et medlem.
    public void sletMedlem() {

        //der oprettes to variable
        int indexMedlemAtSlette;
        int i = 1;

        //En formateret string printes ud, der fungerer som header
        System.out.printf(" %-15s%-15s%-15s%-18s%-15s%-15s %-15s%-20s\n", "Nr.", "Fornavn:", "Efternavn:", "Alder:", "Køn:", "Medlemstype:", "Brugernavn:", "Password:");

        //for løkke der printer alle medlemmer ud, hvor i vokser med en for hver iteration.
        for (Medlem medlemmer : data.getMedlemmer()) {
            System.out.printf("%-15s %-15s %-15s %-15s%-15s%-15s%-15s%-20s\n", i, ((Medlem) medlemmer).getFornavn(), ((Medlem) medlemmer).getEfternavn(),
                    ((Medlem) medlemmer).getAlder(), ((Medlem) medlemmer).getKoen(), ((Medlem) medlemmer).getMedlemstype(), ((Medlem) medlemmer).getUsername(),
                    ((Medlem) medlemmer).getPassword());
            i++;
        }
        System.out.println("Vælg ID-nummer på den bruger, du ønsker at slette: ");
        indexMedlemAtSlette = -1;
        //Try-Catch, hvis der bliver indtastet noget andet end en integer
        try {
            indexMedlemAtSlette = input.nextInt();
        } catch (InputMismatchException e) {
            input.nextLine();
            System.out.println("Du har indtastet et ugyldigt valg.. Prøv igen");
            sletMedlem();
        }

        //Sletter medlemmet ved brug af en getter og metoden .remove(), der sletter det element, der
        //indeholder indexMedlemAtSlette.
        this.data.getMedlemmer().remove(indexMedlemAtSlette);
        System.out.println("Denne bruger er nu slettet");
        visAlleMedlemmer();
    }


    //Case4: Viser et medlem
    private void visMedlem() {

        //der oprettes to variable
        int indexMedlemAtVise;
        int i = 1;

        //En formateret string printes ud, der fungerer som header
        System.out.printf(" %-15s%-15s%-15s%-18s%-15s%-15s %-15s%-20s\n", "Nr.", "Fornavn:", "Efternavn:", "Alder:", "Køn:",
                "Medlemstype:", "Brugernavn:", "Password:");

        //for løkke der printer alle medlemmer ud, hvor i vokser med en for hver iteration.
        for (Medlem medlemmer : data.getMedlemmer()) {
            System.out.printf("%-15s %-15s %-15s %-15s%-15s%-15s%-15s%-20s\n", i, ((Medlem) medlemmer).getFornavn(),
                    ((Medlem) medlemmer).getEfternavn(), ((Medlem) medlemmer).getAlder(), ((Medlem) medlemmer).getKoen(),
                    ((Medlem) medlemmer).getMedlemstype(), ((Medlem) medlemmer).getUsername(), ((Medlem) medlemmer).getPassword());
            i++;
        }
        System.out.println("Vælg ID-nummer på den bruger, du ønsker at se: ");
        indexMedlemAtVise = -1;
        //Try-Catch, hvis der bliver indtastet noget andet end en integer
        try {
            indexMedlemAtVise = input.nextInt();
        } catch (InputMismatchException e) {
            input.nextLine();
            System.out.println("Du har indtastet et ugyldigt valg.. Prøv igen");
            visMedlem();
        }
        //sætter nuværende medlem til det medlem der ligger i det valgte index
        nuvaerendeMedlem = this.data.getMedlemmer().get(indexMedlemAtVise);
        medlemController.setNuvaerendeMedlem(nuvaerendeMedlem);

        //printer navn og oplysninger ud ved brug af getters
        System.out.println("Du har valgt at se " + medlemController.getNuvaerendeMedlem().getFornavn() + " "
                + medlemController.getNuvaerendeMedlem().getEfternavn());
        System.out.println("\nVi har følgende oplysninger registreret på brugeren:");
        System.out.println("____________________________________________________");
        System.out.println("\tAlder: \t\t\t" + medlemController.getNuvaerendeMedlem().getAlder());
        System.out.println("\tKøn: \t\t\t" + medlemController.getNuvaerendeMedlem().getKoen());
        System.out.println("\tMedlemstype: \t" + medlemController.getNuvaerendeMedlem().getMedlemstype());
        System.out.println("\tBrugernavn: \t" + medlemController.getNuvaerendeMedlem().getUsername());
        System.out.println("\tPassword: \t\t" + medlemController.getNuvaerendeMedlem().getPassword());
        System.out.println("____________________________________________________");

        //En valg menu mere efter at de forrige oplysninger er printede ud.
        System.out.println("\nHvad ønsker du at foretage dig?");
        System.out.println("\t1. Ændre oplysninger for et medlem");
        System.out.println("\t2. Gå til administratormenuen");
        System.out.println("\t0. Tilbage til hovedmenuen");
        int valg2 = -1;

        //Try-Catch, hvis der bliver indtastet noget andet end en integer
        try {
            valg2 = input.nextInt();
        } catch (InputMismatchException e) {
            input.nextLine();
            System.out.println("Du har indtastet et ugyldigt valg.. Prøv igen");
            visMedlem();
        }
        switch (valg2) {
            case 1:
                aendrMedlem();
                break;
            case 2:
                visAdminMenu();
                break;
            case 0:
                logAf();
                break;
        }
    }


    //Case5: printer alle medlemmer ud. Er bygget op på samme måde første del af som case 4.
    public void visAlleMedlemmer() {
        int i = 1;
        System.out.printf(" %-15s%-15s%-15s%-15s%-15s%-14s %-15s%-20s\n", "Nr.", "Fornavn:", "Efternavn:",
                "Alder:", "Køn:", "Medlemstype:", "Brugernavn:", "Password:");
        for (Medlem medlemmer : data.getMedlemmer()) {
            System.out.printf("%-16s%-15s%-15s%-15s%-15s%-15s%-15s%-20s\n", i, medlemmer.getFornavn(),
                    medlemmer.getEfternavn(), medlemmer.getAlder(), medlemmer.getKoen(),
                    medlemmer.getMedlemstype(), medlemmer.getUsername(),
                    medlemmer.getPassword());
            i++;
        }

        System.out.println("\n" + "Hvad vil du nu foretage dig?: ");
        System.out.println("1. Administratormenuen: ");
        System.out.println("0. Gå tilbage til hovedmenuen ");
        int valg3 = -1;
        //Try-Catch, hvis der bliver indtastet noget andet end en integer
        try {
            valg3 = input.nextInt();
        } catch (InputMismatchException e) {
            input.nextLine();
            System.out.println("Du har indtastet et ugyldigt valg.. Prøv igen");
            visAlleMedlemmer();
        }

        switch (valg3) {
            case 1:
                visAdminMenu();
                break;
            case 0:
                logAf();
                break;
            default:
                System.out.println("Du har indtastet et ugyldigt valg... Prøv igen\n");
                visAlleMedlemmer();
                break;
        }
    }

    //Case6: Sorterer medlemmer efter medlemstype (stigende)
    public void statistik() {
        System.out.println("Medlemlemstype:" + "\t\t\t" + "Fornavn:" + "\t\t\t\t" + "Efternavn:");

        //for løkke der printer medlemmer ud fra den private arrayliste nedenfor.
        for (Medlem medlem : sorterMedlemstype()) {
            System.out.println(medlem.getMedlemstype() + "\t\t\t\t\t\t" + medlem.getFornavn() + "\t\t\t\t\t" + medlem.getEfternavn());
        }
        //kører admin menu
        visAdminMenu();
    }

    //opretter en privat arrayliste
    private ArrayList<Medlem> sorterMedlemstype() {
        ArrayList<Medlem> sorteretListe = new ArrayList<>();

        //lægger medlemmer til, hvis medlemstyper en lig 1
        for (Medlem medlem : data.getMedlemmer()) {
            if (medlem.getMedlemstype() == 1) {
                sorteretListe.add(medlem);
            }
        }
        //lægger medlemmer til, hvis medlemstyper en lig 2
        for (Medlem medlem : data.getMedlemmer()) {
            if (medlem.getMedlemstype() == 2) {
                sorteretListe.add(medlem);
            }
        }
        //lægger medlemmer til, hvis medlemstyper en lig 3
        for (Medlem medlem : data.getMedlemmer()) {
            if (medlem.getMedlemstype() == 3) {
                sorteretListe.add(medlem);
            }
        }
        //returnerer den sorterede arrayliste
        return sorteretListe;
    }

    //Case7: udregner grundlæggende statistikker over medlemmer
    public void medlemStatistik() {

        //printer menu
        System.out.print("\nHvad ønsker du at gøre?");
        System.out.println("\n\t1. Gennemsnitsalder");
        System.out.println("\t2. Fordeling af køn");
        System.out.println("\t3. Fordeling af medlemstype");
        System.out.println("\t4. Gå tilbage til administratormenuen");

        //opretter atributter
        double nyGennemsnitsAlder;
        double antalMedlemmer = 0;
        int sumAfAlder = 0;
        int valg = input.nextInt();
        int i = 0;
        int j = 0;
        int k = 0;

        switch (valg) {

            case 1: //for løkke der kører igennem alle medlemmer.

                for (Medlem medlem : data.getMedlemmer()) {
                    antalMedlemmer++;                   //antalMedlemmer øges med 1 for hver iteration.
                    sumAfAlder += medlem.getAlder();    //medlemmets alder lægges til sumAfAlder hver iteration.
                }
                nyGennemsnitsAlder = sumAfAlder / antalMedlemmer;   //udregner gennemsnit

                //gennemsnittet printes ud og medlemStatistik køres igen.
                System.out.println("Gennemsnitsalderen af medlemmerne er: " + nyGennemsnitsAlder);
                medlemStatistik();
                break;

            case 2: //for løkke der kører igennem alle medlemmer.
                for (Medlem medlem : data.getMedlemmer()) {
                    if (medlem.getKoen().equalsIgnoreCase("Mand")) {
                        i++;        //lægger en til i, hvis køn er lig Mand/mand
                    } else {
                        j++;        //lægger en til j, hvis køn ikker er lig Mand/mand
                    }
                }

                //antal mænd og kvinder printes ud og medlemStatistik køres igen.
                System.out.println("Antal af medlemmer der er mænd: " + i + "\nAntal medlemmer der er kvinder: " + j);
                medlemStatistik();
                break;

            case 3: //for løkke der kører igennem alle medlemmer.
                for (Medlem medlem : data.getMedlemmer()) {
                    if (medlem.getMedlemstype() == 1) {         //lægger 1 til i hvis medlemstypen er lig 1
                        i++;
                    } else if (medlem.getMedlemstype() == 2) {  //lægger 1 til j hvis medlemstypen er lig 2
                        j++;
                    } else if (medlem.getMedlemstype() == 3){   //lægger 1 til k hvis medlemstypen er lig 3
                        k++;
                    }
                    else {  //Printer brugernavn på brugere, med forkert "melemstype status"
                        System.out.println("Der er en fejl i: " + medlem.getUsername() +"'s oplysninger");
                    }
                }   //Printer antal medlemmer af de forskellige typer
                System.out.println("Antal medlemmer med medlemstype 1: " + i + "\nAntal medlemmer med medlemstype 2: " + j + "\nAntal medlemmer med medlemstype 3: " + k);
                medlemStatistik();
                break;

            case 4:     //kører visAdminMenu()
                visAdminMenu();

            case 0:
                logAf();
                break;

            default:    //kører medlemStatistik()
                medlemStatistik();
        }
    }


    //Case0: logger af. Dette gøres ved at sætte nuværende medlem lig null.
    public void logAf() {
        System.out.println("\nDu er nu logget ud... Viderestiller til login");
        nuvaerendeMedlem = null;

    }

    //Tjekker om brugernavnet ligger i Arraylisten. Returnerer false, hvis det er tilfældet.
    public boolean checkUsername2(String username) {
        boolean check = true;
        for (Medlem medlem : data.getMedlemmer()) {
            if (medlem.getUsername().equalsIgnoreCase(username)) {
                System.out.println("Dette brugernavn er allerede i brug.. Vælg venligst et andet: ");
                check = false;
            }
        }
        return check;
    }
}