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

public class Fmedicament extends JFrame implements ActionListener {
    JLabel labidmedoc, labnom,labprix, labdateex;
    JTextField txtidmedoc, txtnom, txtprix;
    JButton add,view,delete,update ,search;
    JTable tbmedoc;
    private DefaultTableModel model = null;
    JDatePickerImpl datePicker;
    ArrayList <Medicament> listeMedoc = new ArrayList();
    Medicament medicament = null;
    
public Fmedicament(){
        labidmedoc= new JLabel("id_Medicament");
        labidmedoc.setBounds(10, 30, 100, 30);
        this.getContentPane().add(labidmedoc);
        
        labnom = new JLabel("Nom");
        labnom.setBounds(10, 70, 100, 30);
        this.getContentPane().add(labnom);
        
        labprix = new JLabel("Prix");
        labprix.setBounds(10, 110, 100, 30);
        this.getContentPane().add(labprix);
//        
        labdateex = new JLabel("date d'exp");
        labdateex.setBounds(10, 150, 100, 30);
        this.getContentPane().add(labdateex);
       
        txtidmedoc= new JTextField();
        txtidmedoc.setBounds(120, 30, 200, 30);
        this.getContentPane().add(txtidmedoc);
        
        txtnom= new JTextField();
        txtnom.setBounds(120, 70, 200, 30);
        this.getContentPane().add(txtnom);
        
        txtprix= new JTextField();
        txtprix.setBounds(120, 110, 200, 30);
        this.getContentPane().add(txtprix);
        
        UtilDateModel dmodel = new UtilDateModel();
        dmodel.setSelected(true);
        Properties p = new Properties();
        p.put("text.today","Today");
        p.put("text.month","Month");
        p.put("text.year","Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(dmodel,p);
        datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
        datePicker.setBounds(120, 150, 200, 30);
        this.getContentPane().add(datePicker);
        
        search = new JButton("🔍 Search");
        search.setBounds(330, 30, 100, 30);
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
        model.addColumn("idmedoc");
        model.addColumn("Nom");
        model.addColumn("Prix");
        model.addColumn("Date d'exp");
        
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
            int idmedoc = Integer.parseInt(txtidmedoc.getText());
            String nom = txtnom.getText();
            String prix = txtprix.getText();
            
            java.util.Date DateEmb = (java.util.Date)datePicker.getModel().getValue();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(DateEmb);
           
            try {
                Factory.insererMedicament(idmedoc,nom, prix,formattedDate);
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
                String id = txtidmedoc.getText();
                Factory.supprimerMedicament(id);
                String message = "Suprimé avec SUCCES";
                JOptionPane.showConfirmDialog(this, message);
                Afficher();
            } catch (Exception ex) {
                ex.printStackTrace();
            }  
        }
        
        else if(e.getSource()==search){
            try {
                String nom = txtnom.getText();
                medicament = Factory.rechercheMedicament(nom);
                if (medicament != null) {
            completerMedicament(medicament);
        }
            } catch (Exception ex) {
                ex.printStackTrace();
            }  
        }
    
        else if(e.getSource()==update){
            int idmedoc = Integer.parseInt(txtidmedoc.getText());
            String nom = txtnom.getText();
            String prix = txtprix.getText();
            
            java.util.Date DateEmb = (java.util.Date)datePicker.getModel().getValue();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(DateEmb);
        
            try {
                String id = txtidmedoc.getText();
                Factory.modifierMedicament(nom, prix,formattedDate,id);
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
        listeMedoc = Factory.getMedicament();
        for(Medicament medoc : listeMedoc){
            model.addRow(new Object[]{
                medoc.getIdmed() ,medoc.getNom(),medoc.getPrix(), medoc.getDateexp()});
        }
        tbmedoc = new JTable(model);
        JScrollPane p = new JScrollPane(tbmedoc);
        p.setBounds(30,400,600,150);
        this.getContentPane().add(p);
    }
public void completerMedicament(Medicament med) {
        txtidmedoc.setText(med.getIdmed());;
        txtnom.setText(med.getNom());
        txtprix.setText(med.getPrix());
    }
}
