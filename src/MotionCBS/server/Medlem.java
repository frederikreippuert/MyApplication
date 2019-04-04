package MotionCBS.server;


public class Medlem {
    //Her defineres attributer for vores medlem
    private String fornavn;
    private String efternavn;
    private int alder;
    private String koen;
    private int medlemstype;
    private String username;
    private String password;
    private Medlem nuvaerendeMedlem;




    //Opretter constructor med de ovenstående attributer
    public Medlem(String fornavn, String efternavn, int alder, String koen, int medlemstype, String username, String password) {
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.alder = alder;
        this.koen = koen;
        this.medlemstype = medlemstype;
        this.username = username;
        this.password = password;
        this.nuvaerendeMedlem = nuvaerendeMedlem;}

    //Opretter get og set for at kunne kalde på de forskellige ting i controllers
    public void setFornavn(String fornavn){this.fornavn = fornavn;}
    public String getFornavn(){return fornavn;}

    public void setEfternavn(String efternavn) { this.efternavn = efternavn;}
    public String getEfternavn() {return efternavn; }

    public void setAlder(int alder) {this.alder = alder;}
    public int getAlder() {return alder;}

    public void setKoen(String koen) {this.koen = koen;}
    public String getKoen() {return koen;}

    public void setMedlemstype(int medlemstype) {this.medlemstype = medlemstype;}
    public int getMedlemstype() {return medlemstype;}

    public void setUsername(String username) {this.username = username;}
    public String getUsername() {return username;}

    public void setPassword(String password) {this.password = password;}
    public String getPassword() {return password;}

    public void setNuvaerendeMedlem(Medlem nuvaerendeMedlem) { this.nuvaerendeMedlem = nuvaerendeMedlem;}
    public Medlem getNuvaerendeMedlem() { return nuvaerendeMedlem;}


}
