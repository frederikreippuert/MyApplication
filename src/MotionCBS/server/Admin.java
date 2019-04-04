package MotionCBS.server;

public class Admin {
    //Her defineres attributer for vores admin
    private String navn;
    private String username;
    private String password;


    //Opretter constructor med de ovenstående attributer
    public Admin (String navn, String username, String password) {
        this.navn = navn;
        this.username = username;
        this.password = password; }

    //Opretter get og set for at kunne kalde på de forskellige ting i controllers
    public String getNavn(){return navn;}
    public void setNavn(){this.navn = navn;}

    public String getUsername() {return username;}
    public void setUsername() {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}