package MotionCBS.client.logic;


import MotionCBS.server.Admin;
import MotionCBS.server.Medlem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainController {

    Scanner input = new Scanner(System.in);

    //Attributter oprettes
    private Data data;
    private Medlem nuvaerendeMedlem;
    private Admin nuvaerendeAdmin;
    private MedlemController medlemController;
    private AdminController adminController;


    //Constructor
    public MainController() {
        this.data = new Data();
        this.medlemController = new MedlemController(data, nuvaerendeMedlem);
        this.adminController = new AdminController(data, nuvaerendeMedlem);
    }

    public void run() {

        //Run sættes til at være lig true, hvilket starter et loop for programmet, som man først kommer ud af
        //når run lig false.
        boolean run = true;

        do {    //Hovedmenuen printes ud.
            System.out.println(" \n");
            System.out.println("****************************************");
            System.out.println("*                                      *");
            System.out.println("*        VELKOMMEN TIL MOTION CBS      *");
            System.out.println("*                                      *");
            System.out.println("****************************************");
            System.out.println("Hvad ønsker du at gøre?");
            System.out.println("\t1. Log-in som medlem");
            System.out.println("\t2. Log-in som administrator");
            System.out.println("\t3. Opret dig som medlem");
            System.out.println("\t0. Luk programmet");
            int valg = -1;

            try {   //Try-catch bruges til at fange ugyldige inputs
                valg = input.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Dette er ikke en mulighed... Prøv igen");
            }

            //switch statement, med cases der kører metoder baseret på bruger valg.
            switch (valg) {
                case 1:
                    logIn();
                    break;
                case 2:
                    adminLogIn();
                    break;
                case 3:
                    opretMedlem();
                    break;
                case 0: //Sætter rul lig false, der fører til at programmet afsluttes.
                    run = false;
                    break;

                default:
                    System.out.println("Dette er ikke en mulighed... Prøv igen");
                    break;
            }
        } while (run);
    }


    //Case1
    public void logIn() {
        String username;
        String password;
        System.out.println("Indtast dit brugernavn: ");
        username = input.next();
        System.out.println("Indtast dit password: ");
        password = input.next();

        //Tjekker om de indtastede oplysninger findes i systemet via userExists()-metoden
        if (UserExists(username, password)) {
            System.out.println("\nVelkommen tilbage " + nuvaerendeMedlem.getFornavn() + " " + nuvaerendeMedlem.getEfternavn());
            medlemController.setNuvaerendeMedlem(nuvaerendeMedlem);
            System.out.println("\nDu bliver nu viderestillet til din personlige medlemsmenu...\n");
            System.out.print("_____________________________________________________________\n");
            medlemController.visMedlemMenu();
        } else {
            System.out.println("Du er ikke medlem, opret dig som medlem");
            run();
        }
    }


    //Case2
    public void adminLogIn() {
        String username;
        String password;

        System.out.println("Indtast dit brugernavn:");
        username = input.next();
        System.out.println("Indtast dit password:");
        password = input.next();

        //Tjekker om de indtastede oplysninger findes i systemet for admin
        for (Admin admin : data.getAdmin()) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                nuvaerendeAdmin = admin;
                System.out.println("Velkommen tilbage " + nuvaerendeAdmin.getNavn());
                System.out.println("\nDu bliver nu viderestillet til administratormenuen...\n");
                adminController.visAdminMenu();
            } else {
                System.out.println("Du er ikke registreret som admin, prøv venligst igen");
                run();
            }
        }
    }

    //Case3
    public void opretMedlem() {
        String fornavn;
        String efternavn;
        int alder;
        String koen;
        int medlemstype;
        String username;
        String password;
        System.out.print("\nIndtast dit fornavn: ");
        fornavn = input.next();
        System.out.print("Indtast dit efternavn: ");
        efternavn = input.next();
        System.out.print("Indtast din alder: ");
        alder = input.nextInt();
        System.out.print("Vælg dit køn (Mand eller kvinde): ");
        koen = input.next();
        System.out.println("Vælg din medlemstype: ");
        System.out.print("\t(1) Medlemstype 1: Fitnesstræning\n");
        System.out.print("\t(2) Medlemstype 2: Holdtræning\n");
        System.out.print("\t(3) Medlemstype 3: Hold- og fitnesstræning\n");
        medlemstype = input.nextInt();


        System.out.print("Opret et brugernavn: ");
        username = input.next();
        System.out.print("Opret et password: ");
        password = input.next();

        //Der tjekkes om brugernavnet allerede findes i systemet via checkUsername()-metoden
        while (!checkUsername(username)) {
            username = input.next();
        }

        //Opretter et objekt af medlem
        Medlem nytMedlem = new Medlem(fornavn, efternavn, alder, koen, medlemstype, username, password);


        //Dette objekt tilføjes til ArrayListen medlemmer.
        data.getMedlemmer().add(nytMedlem);
        medlemController.setNuvaerendeMedlem(nytMedlem);
        System.out.println("\nTillykke, du er nu oprettet som medlem, god træning!");
        System.out.println("Du bliver nu viderestillet til din personlige medlemsmenu...\n");
        medlemController.visMedlemMenu();
    }

    //Tjekker om brugeren kombinationen af brugernavn og password findes i Array listen medlem.
    //Returnerer true, hvis kombinationen eksisterer, og false hvis den ikke eksisterer.
    public boolean UserExists(String username, String password) {
        for (Medlem medlem : data.getMedlemmer()) {
            if (medlem.getUsername().equals(username) && medlem.getPassword().equals(password)) {
                this.nuvaerendeMedlem = medlem;
                return true;
            }
        }
        return false;
    }

    //Tjekker om brugernavnet ligger i Arraylisten. Returnerer false, hvis det er tilfældet.
    public boolean checkUsername(String username) {
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