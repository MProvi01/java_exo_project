/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


public class Rdv {
    String idrdv, date, idpatient, idmed, motif;

    public String getIdrdv() {
        return idrdv;
    }

    public void setIdrdv(String idrdv) {
        this.idrdv = idrdv;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdpatient() {
        return idpatient;
    }

    public void setIdpatient(String idpatient) {
        this.idpatient = idpatient;
    }

    public String getIdmed() {
        return idmed;
    }

    public void setIdmed(String idmed) {
        this.idmed = idmed;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }
    
    
    
    public Rdv(String idrdv,String date, String idpatient,String idmed,String motif){
    this.idrdv = idrdv;
    this.date = date;
    this.idpatient = idpatient;
    this.idmed = idmed;
    this.motif = motif;
    
}
public Rdv(){
    
}
}
