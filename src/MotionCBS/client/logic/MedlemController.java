package MotionCBS.client.logic;


import MotionCBS.server.Medlem;



public class MedlemController {


    //Attributer oprettes

    private Medlem nuvaerendeMedlem;


    //Constructor oprettes
    public MedlemController (Medlem nuvaerendeMedlem) {
        this.nuvaerendeMedlem = nuvaerendeMedlem;
    }


    //Get+set for nuværende medlem
    public Medlem getNuvaerendeMedlem() {
        return nuvaerendeMedlem;
    }

    public void setNuvaerendeMedlem(Medlem nuvaerendeMedlem) {
        this.nuvaerendeMedlem = nuvaerendeMedlem;
    }


}
