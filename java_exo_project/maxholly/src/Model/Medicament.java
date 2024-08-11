/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Medicament {
    String idmed, nom, prix,dateexp;

    public String getIdmed() {
        return idmed;
    }

    public void setIdmed(String idmed) {
        this.idmed = idmed;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getDateexp() {
        return dateexp;
    }

    public void setDateexp(String dateexp) {
        this.dateexp = dateexp;
    }
    
    
    
    public Medicament(String idmed, String nom, String prix,String dateexp){
    this.idmed = idmed;
    this.nom = nom;
    this.prix = prix;
    this.dateexp = dateexp;
    
}
public Medicament(){
    
}
}
