package testappv2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class prescription {
    public void pInformation() {
        Scanner sc = new Scanner(System.in);
        String resp;

        prescription pr = new prescription();

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
                case 1:
                    pr.searchAndAddPrescription();
                    break;
                case 2:
                    pr.searchAndViewPrescription();
                    break;
                case 3:
                    pr.updatePrescription();
                    break;
                case 4:
                    pr.deletePrescription();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid action. Please choose again.");
            }

            System.out.print("Continue? (yes/no): ");
            resp = sc.nextLine();
        } while (resp.equalsIgnoreCase("yes"));

        System.out.println("Thank You!");
    }

    public void searchAndAddPrescription() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        patient cs = new patient();
        cs.viewpatient();

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
        conf.addpatient(sql, Pid, symptoms, date);

        System.out.println("Prescription Added Successfully for Patient ID: " + Pid);
    }

    public void searchAndViewPrescription() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        patient cs = new patient();
        cs.viewpatient();

        System.out.println("Enter ID of the Patient to View Prescription: ");
        int Pid = sc.nextInt();

        String qry = "SELECT p_id, patient_fname, md_name, p_symptoms, p_date, p_treatment FROM tbl_prescription "
                + "LEFT JOIN tbl_patients ON tbl_patients.p_id = tbl_prescription.p_id "
                + "LEFT JOIN tbl_medical_doctor ON tbl_medical_doctor.md_id = tbl_prescription.md_id "
                + "WHERE tbl_prescription.p_id = ?";

        if (conf.getSingleValue("SELECT p_id FROM tbl_prescription WHERE p_id = ?", Pid) == 0) {
            System.out.println("No prescription found for Patient ID: " + Pid);
        } else {
            String[] hdrs = {"ID", "Patient", "Prescription", "Date"};
            String[] clms = {"p_id", "patient_fname", "prescription", "p_date"};
            conf.viewpatient(qry, hdrs, clms, Pid);
        }
    }

    public void updatePrescription() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Enter the ID to Update: ");
        int pid = sc.nextInt();

        while (conf.getSingleValue("SELECT p_id FROM tbl_prescription WHERE p_id = ?", pid) == 0) {
            System.out.println("Selected ID doesn't exist. Enter again: ");
            pid = sc.nextInt();
        }

        // Implement update logic here
        System.out.println("Update logic not implemented yet.");
    }

    private void deletePrescription() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ID to Delete: ");
        int pid = sc.nextInt();

        String qry = "DELETE FROM tbl_prescription WHERE p_id = ?";
        config conf = new config();
        conf.deletepatient(qry, pid);

        System.out.println("Prescription deleted successfully.");
    }
}
