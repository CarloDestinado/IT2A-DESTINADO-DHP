package testappv2;

import java.util.Scanner;

public class prescription {

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
                case 1:
                    searchAndAddPrescription();
                    break;
                case 2:
                    searchAndViewPrescription();
                    break;
                case 3:
                    updatePrescription();
                    break;
                case 4:
                    searchAndViewPrescription();
                    deletePrescription();
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
        config conf = new config(); // Assuming Config handles DB operations
        patient cs = new patient();
        cs.viewpatient(); // Assuming viewPatient() lists all patients

        System.out.println("Enter ID of the Patient: ");
        int Pid = sc.nextInt();

        String psql = "SELECT p_id FROM tbl_patients WHERE p_id = ?";
        while (conf.getSingleValue(psql, Pid) == 0) {
            System.out.println("Patient Doesn't Exist, Enter Again: ");
            Pid = sc.nextInt();
        }

        System.out.println("Patient Found!");

        sc.nextLine(); // Consume newline
        String symptoms;
        do {
            System.out.print("Enter Symptoms: ");
            symptoms = sc.nextLine().trim(); // Trim to remove leading/trailing spaces
            if (symptoms.isEmpty()) {
                System.out.println("Symptoms cannot be blank. Please enter valid symptoms.");
            }
        } while (symptoms.isEmpty()); // Keep asking if input is blank

        String sql = "INSERT INTO tbl_prescription(p_id, prescription) VALUES (?, ?)";
        conf.addpatient(sql, Pid, symptoms);

        System.out.println("Prescription Added Successfully for Patient ID: " + Pid);
    }

    public void searchAndViewPrescription() {
        String qry = "SELECT prescrip_ID, patient_fname, prescription  FROM tbl_prescription "
                + "LEFT JOIN tbl_patients ON tbl_patients.p_id = tbl_prescription.p_id";
                
        String[] hdrs = {" ID ", " Patient ", " Prescription "};
        String[] clms = {"prescrip_ID", "patient_fname", "prescription"};

        config conf = new config();
        conf.viewpatient(qry, hdrs, clms);
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

        sc.nextLine(); // Consume newline
        String newDetails;
        do {
            System.out.print("Enter new prescription details: ");
            newDetails = sc.nextLine().trim(); // Trim to remove leading/trailing spaces
            if (newDetails.isEmpty()) {
                System.out.println("Prescription details cannot be blank. Please enter valid details.");
            }
        } while (newDetails.isEmpty()); // Keep asking if input is blank

        String qry = "UPDATE tbl_prescription SET prescription = ? WHERE p_id = ?";
        conf.updatepatient(qry, newDetails, pid);

        System.out.println("Prescription updated successfully for Patient ID: " + pid);
    }

    public void deletePrescription() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Enter the ID to Delete: ");
        int pid = sc.nextInt();

        String qry = "DELETE FROM tbl_prescription WHERE prescrip_ID = ?";
        conf.deletepatient(qry, pid);

        System.out.println("Prescription deleted successfully for Patient ID: " + pid);
    }
}
