/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Button;
import Control.Factory;
import Model.Medecin;

public class Fmedecin  extends JFrame implements ActionListener{
    JLabel labIdmed,labNom, labPrenom,labTel, labAdresse,labdiplome;
    JTextField txtIdmed, txtNom, txtPrenom,txtdiplome, txtAdresse, txtTel;
    JButton add,view,delete,update ,search;
    ArrayList <Medecin> listeMedecin = new ArrayList();
    JTable tbMedecin;
    private DefaultTableModel model = null;
    Medecin medecin = null;
    
    
public Fmedecin(){
        labIdmed = new JLabel("IdMedecin");
        labIdmed.setBounds(10, 30, 100, 30);
        this.getContentPane().add(labIdmed);
        
        labNom = new JLabel("Nom");
        labNom.setBounds(10, 70, 100, 30);
        this.getContentPane().add(labNom);
        
        labPrenom = new JLabel("Prenom");
        labPrenom.setBounds(10, 110, 100, 30);
        this.getContentPane().add(labPrenom);
        
        labTel = new JLabel("Telephone");
        labTel.setBounds(10, 150, 100, 30);
        this.getContentPane().add(labTel);
        
        labAdresse = new JLabel("Adresse");
        labAdresse.setBounds(10, 190, 100, 30);
        this.getContentPane().add(labAdresse);
        
        labdiplome = new JLabel("Diplome");
        labdiplome.setBounds(10, 230, 100, 30);
        this.getContentPane().add(labdiplome);
               
        txtIdmed = new JTextField();
        txtIdmed.setBounds(120, 30, 200, 30);
        this.getContentPane().add(txtIdmed);
        
        txtNom = new JTextField();
        txtNom.setBounds(120, 70, 200, 30);
        this.getContentPane().add(txtNom);

        txtPrenom = new JTextField();
        txtPrenom.setBounds(120, 110, 200, 30);
        this.getContentPane().add(txtPrenom);

        txtTel = new JTextField();
        txtTel.setBounds(120, 150, 200, 30);
        this.getContentPane().add(txtTel);
        
        txtAdresse = new JTextField();
        txtAdresse.setBounds(120, 190, 200, 30);
        this.getContentPane().add(txtAdresse);
        
        txtdiplome = new JTextField();
        txtdiplome.setBounds(120, 230, 200, 30);
        this.getContentPane().add(txtdiplome);
        
        search = new JButton("🔍 Search Nom");
        search.setBounds(330, 30, 150, 30);
        search.addActionListener(this);
        this.getContentPane().add(search);
        
        add = new JButton("Add");
        add.setBounds(30, 330, 100, 30);
        add.addActionListener(this);
        this.getContentPane().add(add);
        
        view = new JButton("view");
        view.setBounds(150, 330, 100, 30);
        view.addActionListener(this);
        this.getContentPane().add(view);
        
        update = new JButton("update");
        update.setBounds(270, 330, 100, 30);
        update.addActionListener(this);
        this.getContentPane().add(update);
        
        delete = new JButton("delete");
        delete.setBounds(390, 330, 100, 30);
        delete.addActionListener(this);
        this.getContentPane().add(delete);
        
        delete.setBackground(new java.awt.Color(255, 0, 0));
        delete.setForeground(new java.awt.Color(255, 255, 255));
        
        update.setBackground(new java.awt.Color(37, 81, 100));
        update.setForeground(new java.awt.Color(255, 255, 255));
        
        view.setBackground(new java.awt.Color(37, 81, 100));
        view.setForeground(new java.awt.Color(255, 255, 255));
        
        add.setBackground(new java.awt.Color(37, 81, 100));
        add.setForeground(new java.awt.Color(255, 255, 255));
        
        
        model = new DefaultTableModel();
        model.addColumn("IdPatient");
        model.addColumn("Nom");
        model.addColumn("Prenom");
        model.addColumn("Telephone");
        model.addColumn("Adresse");
        model.addColumn("Maladie");
        
        
       this.getContentPane().setLayout(null);
    
    }
public void actionPerformed(ActionEvent e){
    if(e.getSource()==add){
            int idmed = Integer.parseInt(txtIdmed.getText());
            String nom = txtNom.getText();
            String prenom = txtPrenom.getText();
            int tel = Integer.parseInt(txtTel.getText());
            String adresse = txtAdresse.getText();
            String diplome = txtdiplome.getText();
           
            try {
                Factory.insererMedecin(idmed, nom, prenom,tel, adresse,diplome);
                System.out.print("SUCCESS ADD");
                String message = "Ajouté avec SUCCES";
                JOptionPane.showConfirmDialog(this, message);
                Afficher();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
           
        }
        
        else if(e.getSource()==view){
            Afficher();  
        }
        
        else if(e.getSource()==delete){
            try {
                String id = txtIdmed.getText();
                Factory.supprimerMedecin(id);
                String message = "Suprimé avec SUCCES";
                JOptionPane.showConfirmDialog(this, message);
                Afficher();
            } catch (Exception ex) {
                ex.printStackTrace();
            }  
        }
        else if(e.getSource()==search){
            try {
                String nom = txtNom.getText();
                medecin = Factory.rechercheMedecin(nom);
                if (medecin != null) {
            completerMedecin(medecin);
        }
            } catch (Exception ex) {
                ex.printStackTrace();
            }  
        }
        else if(e.getSource()==update){
            String nom = txtNom.getText();
            String prenom = txtPrenom.getText();
            int tel = Integer.parseInt(txtTel.getText());
            String adresse = txtAdresse.getText();
            String diplome = txtdiplome.getText();
        
            try {
                String id = txtIdmed.getText();
                Factory.modifierMedecin(nom, prenom,tel,adresse, diplome,id);
                String message = "Modifié avec SUCCES";
                JOptionPane.showConfirmDialog(this, message);
                Afficher();
            } catch (Exception ex) {
                ex.printStackTrace();
            }  
        }

}
public void Afficher(){
        model.setRowCount(0);
        listeMedecin = Factory.getMedecin();
        for(Medecin med : listeMedecin){
            model.addRow(new Object[]{
                med.getIdmed(),med.getNom(), med.getPrenom(),med.getTelephone(),med.getAdresse(),med.getDiplome()});
        }
        tbMedecin = new JTable(model);
        JScrollPane p = new JScrollPane(tbMedecin);
        p.setBounds(30,400,600,150);
        this.getContentPane().add(p);
    }
public void completerMedecin(Medecin med) {
        txtIdmed.setText(med.getIdmed());
        txtNom.setText(med.getNom());
        txtPrenom.setText(med.getPrenom());
        txtTel.setText(String.valueOf(med.getTelephone()));
        txtAdresse.setText(med.getAdresse());
        txtdiplome.setText(String.valueOf(med.getDiplome()));
    }



}


