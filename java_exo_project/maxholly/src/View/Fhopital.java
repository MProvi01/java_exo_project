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
import Model.Hopital;

public class Fhopital extends JFrame implements ActionListener{
    JLabel labIdHop,labNom, labVille;
    JTextField txtIdHop, txtNom, txtville;
    JButton add,view,delete,update ,search;
    ArrayList <Hopital> listeHopital = new ArrayList();
    JTable tbHopital;
    private DefaultTableModel model = null;
    Hopital hopital = null;
    
    
public Fhopital(){
        labIdHop = new JLabel("IdHopital");
        labIdHop.setBounds(10, 30, 100, 30);
        this.getContentPane().add(labIdHop);
        
        labNom = new JLabel("Nom");
        labNom.setBounds(10, 70, 100, 30);
        this.getContentPane().add(labNom);
        
        labVille = new JLabel("Ville");
        labVille.setBounds(10, 110, 100, 30);
        this.getContentPane().add(labVille);
               
        txtIdHop = new JTextField();
        txtIdHop.setBounds(120, 30, 200, 30);
        this.getContentPane().add(txtIdHop);
        
        txtNom = new JTextField();
        txtNom.setBounds(120, 70, 200, 30);
        this.getContentPane().add(txtNom);

        txtville= new JTextField();
        txtville.setBounds(120, 110, 200, 30);
        this.getContentPane().add(txtville);
        
        search = new JButton("🔍 Search");
        search.setBounds(330, 30, 100, 30);
        search.addActionListener(this);
        this.getContentPane().add(search);
        
        add = new JButton("Add");
        add.setBounds(30, 150, 100, 30);
        add.addActionListener(this);
        this.getContentPane().add(add);
        
        view = new JButton("view");
        view.setBounds(150, 150, 100, 30);
        view.addActionListener(this);
        this.getContentPane().add(view);
        
        update = new JButton("update");
        update.setBounds(270, 150, 100, 30);
        update.addActionListener(this);
        this.getContentPane().add(update);
        
        delete = new JButton("delete");
        delete.setBounds(390, 150, 100, 30);
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
        model.addColumn("IdHopital");
        model.addColumn("Nom");
        model.addColumn("Ville");
        
        
       this.getContentPane().setLayout(null);
    
    }
public void actionPerformed(ActionEvent e){
    if(e.getSource()==add){
            int idhop = Integer.parseInt(txtIdHop.getText());
            String nom = txtNom.getText();
            String ville = txtville.getText();
            
            try {
                Factory.insererHopital(idhop, nom, ville);
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
                String id = txtIdHop.getText();
                Factory.supprimerHopital(id);
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
                hopital = Factory.rechercheHopital(nom);
                if (hopital != null) {
            completerHopital(hopital);
        }
            } catch (Exception ex) {
                ex.printStackTrace();
            }  
        }

        else if(e.getSource()==update){
            String nom = txtNom.getText();
            String ville = txtville.getText();
        
            try {
                String id = txtIdHop.getText();
                Factory.modifierHopital(nom, ville,id);
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
        listeHopital = Factory.getHopital();
        for(Hopital hop : listeHopital){
            model.addRow(new Object[]{
                hop.getIdhop(),hop.getNom(), hop.getVille()});
        }
        tbHopital = new JTable(model);
        JScrollPane p = new JScrollPane(tbHopital);
        p.setBounds(30,400,600,150);
        this.getContentPane().add(p);
    }
public void completerHopital(Hopital hop) {
        txtIdHop.setText(hop.getIdhop());
        txtNom.setText(hop.getNom());
        txtville.setText(hop.getVille());
    }

}

