package testappv2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Prescription {

    public void pInformation() {
        Scanner sc = new Scanner(System.in);
        String resp;

        do {
            System.out.println("---------------------");
            System.out.println("--PATIENT PRESCRIPTION--");
            System.out.println("---------------------");
            System.out.println("\n1. ADD PRESCRIPTION");
            System.out.println("2. VIEW PRESCRIPTION");
            System.out.println("3. UPDATE PRESCRIPTION");
            System.out.println("4. DELETE PRESCRIPTION");
            System.out.println("5. EXIT");

            System.out.print("\nEnter Action: ");
            int action = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (action) {
                case 1 -> AddPrescription();
                case 2 -> ViewPrescription();
                case 3 -> updatePrescription();
                case 4 -> deletePrescription();
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid action. Please choose again.");
            }

            System.out.print("Continue? (yes/no): ");
            resp = sc.nextLine();
        } while (resp.equalsIgnoreCase("yes"));

        System.out.println("Thank You!");
    }

    public void AddPrescription() {
        Scanner sc = new Scanner(System.in);
        Config conf = new Config();
        Patient cs = new Patient();
        cs.viewPatient();

        // Search for patient
        System.out.println("Enter ID of the Patient: ");
        int Pid = sc.nextInt();

        String psql = "SELECT p_id FROM tbl_patients WHERE p_id = ?";
        while (conf.getSingleValue(psql, Pid) == 0) {
            System.out.println("Patient Doesn't Exist, Enter Again: ");
            Pid = sc.nextInt();
        }

        // Add prescription for found patient
        System.out.println("Patient Found!");

        sc.nextLine(); // Consume newline
        System.out.print("Enter Symptoms: ");
        String symptoms = sc.nextLine();

        LocalDate currDate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currDate.format(format);

        String sql = "INSERT INTO tbl_prescription(p_id, prescription, p_date) VALUES (?, ?, ?)";
        conf.addPatient(sql, Pid, symptoms, date);

        System.out.println("Prescription Added Successfully for Patient ID: " + Pid);
    }

    public void ViewPrescription() {
        Scanner sc = new Scanner(System.in);
        Config conf = new Config();
        Patient cs = new Patient();
        cs.viewPatient();

        System.out.println("Enter ID of the Patient to View Prescription: ");
        int Pid = sc.nextInt();

        String qry = "SELECT prescrip_ID, tbl_prescription.p_id, prescription, p_date " +
                     "FROM tbl_prescription " +
                     "LEFT JOIN tbl_patients ON tbl_patients.p_id = tbl_prescription.p_id " +
                     "WHERE tbl_prescription.p_id = ?";

        if (conf.getSingleValue("SELECT p_id FROM tbl_prescription WHERE p_id = ?", Pid) == 0) {
            System.out.println("No prescription found for Patient ID: " + Pid);
        } else {
            String[] hdrs = {"ID", "Patient ID", "Prescription", "Date"};
            String[] clms = {"prescrip_ID", "p_id", "prescription", "p_date"};
            conf.viewPatient(qry, hdrs, clms);
        }
    }

    public void updatePrescription() {
        Scanner sc = new Scanner(System.in);
        Config conf = new Config();

        System.out.print("Enter the ID to Update: ");
        int pid = sc.nextInt();

        while (conf.getSingleValue("SELECT p_id FROM tbl_prescription WHERE p_id = ?", pid) == 0) {
            System.out.println("Selected ID doesn't exist. Enter again: ");
            pid = sc.nextInt();
        }

        // Update prescription
        sc.nextLine(); // Consume newline
        System.out.print("Enter New Prescription: ");
        String newPrescription = sc.nextLine();

        String updateQry = "UPDATE tbl_prescription SET prescription = ? WHERE p_id = ?";
        conf.updatePatient(updateQry, newPrescription, pid);

        System.out.println("Prescription updated successfully.");
    }

    private void deletePrescription() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ID to Delete: ");
        int pid = sc.nextInt();

        String qry = "DELETE FROM tbl_prescription WHERE p_id = ?";
        Config conf = new Config();
        conf.deletePatient(qry, pid);

        System.out.println("Prescription deleted successfully.");
    }
}
