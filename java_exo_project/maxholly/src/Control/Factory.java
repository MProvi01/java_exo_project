/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import java.sql.*;
import java.util.ArrayList;
import View.*;
import Model.*;

public class Factory {
     public static Statement stm;
    public static PreparedStatement pstmet = null;
    public static ResultSet rs = null;
    private static final String url = "jdbc:mysql://localhost:3306/maxholly";
    private static final String username = "root";
    private static final String password = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Échec du chargement du pilote JDBC MySQL");
        }
    }

    public static void insererPatient(int id, String nom, String prenom,int tel,String adresse,String maladie) throws SQLException {
    Connection connection = Factory.getConnection();
    String sql = "INSERT INTO patient (id_patient, nom, prenom, num_tel, adresse, maladie) VALUES (?,?,?,?,?,?)";
    try (java.sql.PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, id);
        statement.setString(2, nom);
        statement.setString(3, prenom);
        statement.setInt(4, tel);
        statement.setString(5, adresse);
        statement.setString(6, maladie);
        
        statement.executeUpdate();
    } finally {
        connection.close();
    }
}
    public static void insererMedecin(int id,String nom, String prenom,int tel,String adresse,String diplome) throws SQLException {
    Connection connection = Factory.getConnection();
    String sql = "INSERT INTO medecin (id_medecin,nom, prenom, num_tel, adresse, diplome) VALUES (?,?,?,?,?,?)";
    try (java.sql.PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, id);
        statement.setString(2, nom);
        statement.setString(3, prenom);
        statement.setInt(4, tel);
        statement.setString(5, adresse);
        statement.setString(6, diplome);
        
        statement.executeUpdate();
    } finally {
        connection.close();
    }
}
    public static void insererMedicament(int id,String nom, String prix,String date) throws SQLException {
    Connection connection = Factory.getConnection();
    String sql = "INSERT INTO medicaments (nom, prix, date_exp) VALUES (?,?,?)";
    try (java.sql.PreparedStatement statement = connection.prepareStatement(sql)) {
        
        statement.setString(1, nom);
        statement.setString(2, prix);
        statement.setString(3, date);
        
        statement.executeUpdate();
    } finally {
        connection.close();
    }
}
    public static void insererHopital(int id,String nom, String ville) throws SQLException {
    Connection connection = Factory.getConnection();
    String sql = "INSERT INTO hopital (id_hopital, nom, ville) VALUES (?,?,?)";
    try (java.sql.PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1,id);
        statement.setString(2, nom);
        statement.setString(3, ville);
        
        statement.executeUpdate();
    } finally {
        connection.close();
    }
}
    public static void insererRdv(int idrdv,String date, String idpatient,String idmed,String motif) throws SQLException {
    Connection connection = Factory.getConnection();
    String sql = "INSERT INTO rdv (id_rdv,date, id_patient, id_medecin,motif) VALUES (?,?,?,?,?)";
    try (java.sql.PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, idrdv);
        statement.setString(2, date);
        statement.setString(3, idpatient);
        statement.setString(4, idmed);
        statement.setString(5, motif);
        
        statement.executeUpdate();
    } finally {
        connection.close();
    }
}
    
    
    //----------------afficher--------
    public static ArrayList<Patient> getPatient(){
    ArrayList<Patient> listpat = new ArrayList();
    Patient pat = null;

    try{
    Connection connection = Factory.getConnection();
    stm = connection.createStatement();
    rs = stm.executeQuery("select * from patient");

    while(rs.next()){
        pat = new Patient(rs.getString("id_patient"),rs.getString("nom"),rs.getString("prenom"),
                rs.getInt("num_tel"),rs.getString("adresse"),rs.getString("maladie"));
        listpat.add(pat);
    }
    connection.close();
    rs.close();
    }
    catch(Exception e){
    }
    return listpat;
    }
    public static ArrayList<Medecin> getMedecin(){
        ArrayList<Medecin> listmed = new ArrayList();
        Medecin med = null;

        try{
        Connection connection = Factory.getConnection();
        stm = connection.createStatement();
        rs = stm.executeQuery("select * from medecin");

        while(rs.next()){
            med = new Medecin(rs.getString("id_medecin"),rs.getString("nom"),rs.getString("prenom"),
                    rs.getInt("num_tel"),rs.getString("adresse"),rs.getString("diplome"));
            listmed.add(med);
        }
        connection.close();
        rs.close();
        } 
        catch(Exception e){
        }
        return listmed;
    }
    public static ArrayList<Hopital> getHopital(){
        ArrayList<Hopital> listhop = new ArrayList();
        Hopital hop = null;

        try{
        Connection connection = Factory.getConnection();
        stm = connection.createStatement();
        rs = stm.executeQuery("select * from hopital");

        while(rs.next()){
            hop = new Hopital(rs.getString("id_hopital"),rs.getString("nom"),rs.getString("ville"));
            listhop.add(hop);
        }
        connection.close();
        rs.close();
        } 
        catch(Exception e){
        }
        return listhop;
    }
    public static ArrayList<Rdv> getRdv(){
        ArrayList<Rdv> listrdv = new ArrayList();
        Rdv rdv = null;

        try{
        Connection connection = Factory.getConnection();
        stm = connection.createStatement();
        rs = stm.executeQuery("select * from rdv");

        while(rs.next()){
            rdv = new Rdv(rs.getString("id_rdv"),rs.getString("date"),rs.getString("id_patient"),
                    rs.getString("id_medecin"),rs.getString("motif"));
            listrdv.add(rdv);
        }
        connection.close();
        rs.close();
        } 
        catch(Exception e){
        }
        return listrdv;
    }
    public static ArrayList<Medicament> getMedicament(){
    ArrayList<Medicament> listmedoc = new ArrayList();
    Medicament medoc = null;

    try{
    Connection connection = Factory.getConnection();
    stm = connection.createStatement();
    rs = stm.executeQuery("select * from medicaments");

    while(rs.next()){
        medoc = new Medicament(rs.getString("id_medicament"),rs.getString("nom"),rs.getString("prix"),
                rs.getString("date_exp"));
        listmedoc.add(medoc);
    }
    connection.close();
    rs.close();
    } 
    catch(Exception e){
    }
    return listmedoc;
}
    
    
    //--------------suprimer-------------
    public static void supprimerPatient(String idpatient) throws SQLException {
        Connection connection = Factory.getConnection();
        String sql = "DELETE FROM patient WHERE id_patient = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, idpatient);
            statement.executeUpdate();
        } finally {
            connection.close();
        }
    }
    public static void supprimerMedecin(String idmedecin) throws SQLException {
        Connection connection = Factory.getConnection();
        String sql = "DELETE FROM medecin WHERE id_medecin = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, idmedecin);
            statement.executeUpdate();
        } finally {
            connection.close();
        }
    }
    public static void supprimerHopital(String idhopital) throws SQLException {
        Connection connection = Factory.getConnection();
        String sql = "DELETE FROM hopital WHERE id_hopital = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, idhopital);
            statement.executeUpdate();
        } finally {
            connection.close();
        }
    }
    public static void supprimerRdv(String idrdv) throws SQLException {
        Connection connection = Factory.getConnection();
        String sql = "DELETE FROM rdv WHERE id_rdv = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, idrdv);
            statement.executeUpdate();
        } finally {
            connection.close();
        }
    }
    public static void supprimerMedicament(String idmedoc) throws SQLException {
        Connection connection = Factory.getConnection();
        String sql = "DELETE FROM medicaments WHERE id_medicament= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, idmedoc);
            statement.executeUpdate();
        } finally {
            connection.close();
        }
    }


    //------------search-----------------
    public static Patient recherchePatient(String nom) throws SQLException {
    Connection connection = Factory.getConnection();
    String sql = "SELECT * FROM patient WHERE nom = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, nom);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Patient pat = new Patient(resultSet.getString("id_patient"), 
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getInt("num_tel"),
                    resultSet.getString("adresse"),
                    resultSet.getString("maladie"));
            return pat;
        } else {
            return null;
        }
    } finally {
        connection.close();
    }
}
    public static Medecin rechercheMedecin(String nom) throws SQLException {
    Connection connection = Factory.getConnection();
    String sql = "SELECT * FROM medecin WHERE nom = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, nom);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Medecin med = new Medecin(resultSet.getString("id_medecin"), 
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getInt("num_tel"),
                    resultSet.getString("adresse"),
                    resultSet.getString("diplome"));
            return med;
        } else {
            return null;
        }
    } finally {
        connection.close();
    }
}
    public static Medicament rechercheMedicament(String nom) throws SQLException {
    Connection connection = Factory.getConnection();
    String sql = "SELECT * FROM medicaments WHERE nom = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, nom);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Medicament medoc = new Medicament(resultSet.getString("id_medicament"), 
                    resultSet.getString("nom"),
                    resultSet.getString("prix"),
                    resultSet.getString("date_exp"));
            return medoc;
        } else {
            return null;
        }
    } finally {
        connection.close();
    }
}
    public static Rdv rechercheRdv(String idrdv) throws SQLException {
    Connection connection = Factory.getConnection();
    String sql = "SELECT * FROM rdv WHERE id_rdv = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, idrdv);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Rdv rdv = new Rdv(resultSet.getString("id_rdv"), 
                    resultSet.getString("date"),
                    resultSet.getString("id_patient"),
                    resultSet.getString("id_medecin"),
                    resultSet.getString("motif"));
            return rdv;
        } else {
            return null;
        }
    } finally {
        connection.close();
    }
}
    public static Hopital rechercheHopital(String nom) throws SQLException {
    Connection connection = Factory.getConnection();
    String sql = "SELECT * FROM hopital WHERE nom = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, nom);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Hopital hop = new Hopital(resultSet.getString("id_hopital"), 
                    resultSet.getString("nom"),
                    resultSet.getString("ville"));
            return hop;
        } else {
            return null;
        }
    } finally {
        connection.close();
    }
}
    
    
    
//------------------------update-----------
public static void modifierPatient(String nom, String prenom,int tel,String adresse,String maladie,String idPatient) throws SQLException {
    Connection connection = Factory.getConnection();
    String sql = "UPDATE patient SET nom = ?,prenom = ?, num_tel = ?,adresse = ?, maladie = ? WHERE id_patient= ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        
        statement.setString(1, nom);
        statement.setString(2, prenom);
        statement.setInt(3, tel);
        statement.setString(4, adresse);
        statement.setString(5, maladie);
        statement.setString(6, idPatient);
        statement.executeUpdate();
    } finally {
        connection.close();
    }
}
public static void modifierMedecin(String nom, String prenom,int tel,String adresse,String diplome,String idmedecin) throws SQLException {
    Connection connection = Factory.getConnection();
    String sql = "UPDATE medecin SET nom = ?,prenom = ?, num_tel = ?,adresse = ?, diplome = ? WHERE id_medecin = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        
        statement.setString(1, nom);
        statement.setString(2, prenom);
        statement.setInt(3, tel);
        statement.setString(4, adresse);
        statement.setString(5, diplome);
        statement.setString(6, idmedecin);
        statement.executeUpdate();
    } finally {
        connection.close();
    }
}
public static void modifierHopital(String nom, String ville, String idhopital) throws SQLException {
    Connection connection = Factory.getConnection();
    String sql = "UPDATE hopital SET nom = ?,ville = ? WHERE id_hopital = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        
        statement.setString(1, nom);
        statement.setString(2, ville);
        statement.setString(3, idhopital);
        statement.executeUpdate();
    } finally {
        connection.close();
    }
}
public static void modifierMedicament(String nom, String prix,String date, String idmedicament) throws SQLException {
    Connection connection = Factory.getConnection();
    String sql = "UPDATE medicaments SET nom = ?,prix = ?,date_exp = ? WHERE id_medicament = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        
        statement.setString(1, nom);
        statement.setString(2, prix);
        statement.setString(3, date);
        statement.setString(4, idmedicament);
        statement.executeUpdate();
    } finally {
        connection.close();
    }
}
public static void modifierRdv(String date, String idpatient, String idmedecin,String motif, String idrdv) throws SQLException {
    Connection connection = Factory.getConnection();
    String sql = "UPDATE rdv SET date = ?,id_patient = ?, id_medecin=?, motif= ? WHERE id_rdv = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        
        statement.setString(1, date);
        statement.setString(2, idpatient);
        statement.setString(3, idmedecin);
        statement.setString(3, idrdv);
        statement.executeUpdate();
    } finally {
        connection.close();
    }
}



    
    
    
    
    
    
}