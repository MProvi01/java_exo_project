/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;
import Control.Factory;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Button;
import org.jdatepicker.impl.*;
import java.util.Properties;
import java.text.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import javax.swing.JFormattedTextField.AbstractFormatter;
import Control.*;
import Model.*;

public class Frdv  extends JFrame implements ActionListener {
    JLabel labidrdv, labdate,labpat, labmed, labmotif;
    JTextField txtidrdv, txtmotif;
    JButton add,view,delete,update ,search;
    JTable tbrdv;
    JComboBox CombPatient, CombMedecin;
    private DefaultTableModel model = null;
    JDatePickerImpl datePicker;
    ArrayList <Rdv> listeRdv = new ArrayList();
    ArrayList <Patient > listePatient = Factory.getPatient ();
    ArrayList <Medecin > listeMedecin = Factory.getMedecin ();
    JTable tbMedecin, tbPatient;
    Rdv rdv;
    Patient patient = null;
    Medecin medecin = null;
    
    
    public Frdv(){
        labidrdv= new JLabel("IdRdv");
        labidrdv.setBounds(10, 30, 100, 30);
        this.getContentPane().add(labidrdv);
        
        labdate = new JLabel("Date");
        labdate.setBounds(10, 70, 100, 30);
        this.getContentPane().add(labdate);
        
        labpat = new JLabel("idPatient");
        labpat.setBounds(10, 110, 100, 30);
        this.getContentPane().add(labpat);
//        
        labmed = new JLabel("idMedecin");
        labmed.setBounds(10, 150, 100, 30);
        this.getContentPane().add(labmed);
        
        labmotif = new JLabel("Motif");
        labmotif.setBounds(10, 190, 100, 30);
        this.getContentPane().add(labmotif);
        
        txtidrdv = new JTextField();
        txtidrdv.setBounds(120, 30, 200, 30);
        this.getContentPane().add(txtidrdv);
        
        UtilDateModel dmodel = new UtilDateModel();
        dmodel.setSelected(true);
        Properties p = new Properties();
        p.put("text.today","Today");
        p.put("text.month","Month");
        p.put("text.year","Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(dmodel,p);
        datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
        datePicker.setBounds(120, 70, 200, 30);
        this.getContentPane().add(datePicker);
        
        CombPatient = new JComboBox();
        for (Patient m : listePatient) {
        CombPatient.addItem(m.getIdpatient());
        }
        CombPatient.setBounds(120, 110, 200, 30);
        this.getContentPane().add(CombPatient);
        
        CombMedecin = new JComboBox();
        for (Medecin m : listeMedecin) {
        CombMedecin.addItem(m.getIdmed());
        }
        CombMedecin.setBounds(120, 150, 200, 30);
        this.getContentPane().add(CombMedecin);

        txtmotif= new JTextField();
        txtmotif.setBounds(120, 190, 200, 30);
        this.getContentPane().add(txtmotif);
        
        search = new JButton("🔍 Search id");
        search.setBounds(330, 30, 150, 30);
        search.addActionListener(this);
        this.getContentPane().add(search);
        
        add = new JButton("Add");
        add.setBounds(30, 230, 100, 30);
        add.addActionListener(this);
        this.getContentPane().add(add);
        
        view = new JButton("view");
        view.setBounds(150, 230, 100, 30);
        view.addActionListener(this);
        this.getContentPane().add(view);
        
        update = new JButton("update");
        update.setBounds(270, 230, 100, 30);
        update.addActionListener(this);
        this.getContentPane().add(update);
        
        delete = new JButton("delete");
        delete.setBounds(390, 230, 100, 30);
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
        model.addColumn("idrdv");
        model.addColumn("Date");
        model.addColumn("Patient");
        model.addColumn("Medecin");
        model.addColumn("Motif");
        
       this.getContentPane().setLayout(null);
    
    }
class DateLabelFormatter extends AbstractFormatter {
    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
    @Override
    public Object stringToValue(String text) throws ParseException {
       return dateFormatter.parseObject(text);}
    public String valueToString(Object value)throws ParseException{
        if (value != null){
            Calendar cal = (Calendar)value;
            return dateFormatter.format(cal.getTime());
        }            
    return "";
    }

}


public void actionPerformed(ActionEvent e){
    if(e.getSource()==add){
            int idrdv = Integer.parseInt(txtidrdv.getText());
            String idpatient = CombPatient.getSelectedItem().toString();
            String idmedecin = CombMedecin.getSelectedItem().toString();
            String motif = txtmotif.getText();
            
            java.util.Date DateEmb = (java.util.Date)datePicker.getModel().getValue();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(DateEmb);
           
            //malade = new Malade(IdCl, Nom,Prenom,Adresse,Nat,Gender);
            try {
                Factory.insererRdv(idrdv, formattedDate,idpatient, idmedecin,motif);
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
                String id = txtidrdv.getText();
                Factory.supprimerRdv(id);
                String message = "Suprimé avec SUCCES";
                JOptionPane.showConfirmDialog(this, message);
                Afficher();
            } catch (Exception ex) {
                ex.printStackTrace();
            }  
        }

        else if(e.getSource()==search){
            try {
                String idrdv = txtidrdv.getText();
                rdv = Factory.rechercheRdv(idrdv);
                if (rdv != null) {
            completerRdv(rdv);
        }
            } catch (Exception ex) {
                ex.printStackTrace();
            }  
        }
}
public void Afficher(){
        model.setRowCount(0);
        listeRdv = Factory.getRdv();
        for(Rdv rdv : listeRdv){
            model.addRow(new Object[]{
                rdv.getIdrdv() ,rdv.getDate(),rdv.getIdpatient(), rdv.getIdmed(),rdv.getMotif()});
        }
        tbPatient = new JTable(model);
        JScrollPane p = new JScrollPane(tbPatient);
        p.setBounds(30,400,600,150);
        this.getContentPane().add(p);
    }
public void completerRdv(Rdv rdv) {
        txtidrdv.setText(rdv.getIdrdv());
//        datePicker.setText(rdv.getDate());
//        CombPatient.setText(c.getIdpatient());
//        CombMedecin.setText(c.getIdmed());
        txtmotif.setText(rdv.getMotif());
    }

}
