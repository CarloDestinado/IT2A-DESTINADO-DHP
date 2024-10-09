/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testappv2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import jdk.nashorn.internal.runtime.regexp.joni.Config;



public class TestAppV2 {



    public void addPatients(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Patient First Name: ");
        String fname = sc.next();
        System.out.print("Patient Last Name: ");
        String lname = sc.next();
        System.out.print("Patient Age: ");
        String age = sc.next();
        System.out.print("Patient Gender: ");
        String gender = sc.next();
        
        String sql = "INSERT INTO tbl_patients (p_fname, p_lname, p_age, p_gender) VALUES (?, ?, ?, ?)";

        
        conf.addRecord(sql, fname, lname, age, gender);
        
    
    }
   

    public void viewPatients() {
        String sql = "SELECT * FROM tbl_patients";
        config conf = new config();
        try (Connection conn = conf.connectDB(); // Use the connectDB method
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-12s | %-25s |\n", "Firstname", "Lastname", "Age", "Gender");
            System.out.println("--------------------------------------------------------------------------------");

            while (rs.next()) {
                String p_fname = rs.getString("p_fname");
                String p_lname = rs.getString("p_lname");
                int p_age = rs.getInt("p_age");
                String p_gender = rs.getString("p_gender");
                System.out.printf("| %-20s | %-12s | %-25d | %-10s |\n", p_fname, p_lname, p_age, p_gender);
            }

            System.out.println("--------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Error retrieving citizens: " + e.getMessage());
        }
    }

    private Connection connectDB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 public void updatePatients() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the Patient ID to Update: ");
    
    int id = sc.nextInt(); // Fixed syntax here

    System.out.print("Enter new First Name: ");
    String nfname = sc.next();
    
    System.out.print("Enter new Last Name: ");
    String nlname = sc.next();
    
    System.out.print("Enter new Age: ");
    int nage = sc.nextInt(); // Changed to int for age
    
    System.out.print("Enter new Gender: ");
    String ngender = sc.next();

    String qry = "UPDATE tbl_patients SET p_fname = ?, p_lname = ?, p_age = ?, p_gender = ? WHERE p_id = ?"; // Corrected SQL syntax
    
    Config conf = new Config() {}; // Ensure Config class is capitalized
    conf.updatePatients(qry, nfname, nlname, nage, ngender, id); // Make sure your method accepts these parameters correctly
}

public void deletePatients() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the Patient ID to Delete: ");
    
    int id = sc.nextInt();
    String qry = "DELETE FROM tbl_patients WHERE p_id = ?"; // Corrected SQL syntax
    Config conf = new Config() {}; // Ensure Config class is capitalized
    conf.deleteRecord(qry, id); // Ensure your method accepts this parameter correctly
}

    void deleteRecord() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}


