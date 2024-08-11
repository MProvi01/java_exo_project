/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Hopital {
    String idhop, nom, ville;

    public String getIdhop() {
        return idhop;
    }

    public void setIdhop(String idhop) {
        this.idhop = idhop;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
    
    
    
    public Hopital(String idhop, String nom, String ville){
    this.idhop = idhop;
    this.nom = nom;
    this.ville = ville;
    
}
public Hopital(){
    
}
}
