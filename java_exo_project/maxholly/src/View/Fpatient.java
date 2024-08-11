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
import Model.Patient;
public class Fpatient extends JFrame implements ActionListener{
    JLabel labIdPat,labNom, labPrenom,labTel, labAdresse,labMaladie;
    JTextField txtIdPat, txtNom, txtPrenom,txtMaladie, txtAdresse, txtTel;
    JButton add,view,delete,update ,search;
    ArrayList <Patient> listePatient = new ArrayList();
    JTable tbPatient;
    private DefaultTableModel model = null;
    Patient patient = null;
    
    
public Fpatient(){
        labIdPat = new JLabel("IdPatient");
        labIdPat.setBounds(10, 30, 100, 30);
        this.getContentPane().add(labIdPat);
        
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
        
        labMaladie = new JLabel("Maladie");
        labMaladie.setBounds(10, 230, 100, 30);
        this.getContentPane().add(labMaladie);
               
        txtIdPat = new JTextField();
        txtIdPat.setBounds(120, 30, 200, 30);
        this.getContentPane().add(txtIdPat);
        
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
        
        txtMaladie = new JTextField();
        txtMaladie.setBounds(120, 230, 200, 30);
        this.getContentPane().add(txtMaladie);
        
        search = new JButton("🔍 Search nom");
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
            int idpat = Integer.parseInt(txtIdPat.getText());
            String nom = txtNom.getText();
            String prenom = txtPrenom.getText();
            int tel = Integer.parseInt(txtTel.getText());
            String adresse = txtAdresse.getText();
            String maladie = txtMaladie.getText();
            
            try {
                Factory.insererPatient(idpat, nom, prenom,tel, adresse,maladie);
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
                String id = txtIdPat.getText();
                Factory.supprimerPatient(id);
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
                patient = Factory.recherchePatient(nom);
                if (patient != null) {
            completerPatient(patient);
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
            String maladie = txtMaladie.getText();
        
            try {
                String id = txtIdPat.getText();
                Factory.modifierPatient(nom, prenom,tel,adresse, maladie,id);
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
        listePatient = Factory.getPatient();
        for(Patient pat : listePatient){
            model.addRow(new Object[]{
                pat.getIdpatient(),pat.getNom(), pat.getPrenom(),pat.getTelephone(),pat.getAdresse(),pat.getMaladie()});
        }
        tbPatient = new JTable(model);
        JScrollPane p = new JScrollPane(tbPatient);
        p.setBounds(30,400,600,150);
        this.getContentPane().add(p);
    }
public void completerPatient(Patient pat) {
        txtIdPat.setText(pat.getIdpatient());
        txtNom.setText(pat.getNom());
        txtPrenom.setText(pat.getPrenom());
        txtTel.setText(String.valueOf(pat.getTelephone()));
        txtAdresse.setText(pat.getAdresse());
        txtMaladie.setText(String.valueOf(pat.getMaladie()));
    }

}
