/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;
import java.awt.event.*;
import javax.swing.*;
import view.*;
/**
 *
 * @author Administrator
 */
public class MyMenu extends JFrame implements ActionListener{
    JMenuBar menuBar;
    JMenu menuDonn, menuFer;
    JMenuItem mMed, mPat,Mmedoc, mHosp, mrdv,MFer;
    
 public MyMenu(){
     menuBar = new JMenuBar();
        menuDonn = new JMenu("Les Données");
        menuFer = new JMenu("Terminé");
        
        mMed = new JMenuItem("Medecin");
        mMed.addActionListener(this);
        
        mPat = new JMenuItem("Patient");
        mPat.addActionListener(this);
        
        mHosp = new JMenuItem("Hospital");
        mHosp.addActionListener(this);
        
        mrdv = new JMenuItem("Rendez-Vous");
        mrdv.addActionListener(this);
        
        Mmedoc = new JMenuItem(" Medicament");
        Mmedoc.addActionListener(this);
        
        MFer = new JMenuItem("Fermer");
        MFer.addActionListener(this);
        
        menuBar.add(menuDonn);
        menuBar.add(menuFer);
        
        menuDonn.add(mMed);
        menuDonn.add(mPat);
        menuDonn.add(mHosp);
        menuDonn.add(mrdv);
        menuDonn.add(Mmedoc);
        
        menuFer.add(MFer);
        
        this.getContentPane().add(menuBar);
        menuBar.setBounds(200, 10, 300, 50);
        this.setLayout(null);  
//        menuBar.setBackground(new java.awt.Color(51, 153, 255));
//        menuBar.setForeground(new java.awt.Color(255, 255, 255));
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == mHosp){
            Fhopital forHos= new Fhopital();
            forHos.setTitle("HOPITAL");
            forHos.setSize(800, 700);
            forHos.setVisible(true);
        }
        else if(e.getSource() == mMed){
            Fmedecin forMed = new Fmedecin();
            forMed.setTitle("MEDECIN");
            forMed.setSize(800, 700);
            forMed.setVisible(true);
        }
        else if(e.getSource() == mPat){
            Fpatient forPat = new Fpatient();
            forPat.setTitle("PATIENT");
            forPat.setSize(800, 700);
            forPat.setVisible(true);
        }
        else if(e.getSource() == mrdv){
            Frdv forRdv = new Frdv();
            forRdv.setTitle("RDV");
            forRdv.setSize(800, 700);
            forRdv.setVisible(true);            
        }
        else if(e.getSource() == Mmedoc){
            Fmedicament forMedoc = new Fmedicament();
            forMedoc.setTitle("MEDICAMENT");
            forMedoc.setSize(800, 700);
            forMedoc.setVisible(true);            
        }
        else if(e.getSource() == MFer){
            dispose();
        }
    }   
}
