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
            sc.nextLine();

            switch (action) {
                case 1:
                    pr.addPrescription();
                    pr.viewPrescription();
                    break;
                case 2:
                    pr.viewPrescription();
                    break;
                case 3:
                    pr.viewPrescription();
                    pr.updatePrescription();
                    break;
                case 4:
                    pr.viewPrescription();
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

    public void addPrescription() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        patient cs = new patient();
        cs.viewpatient();

        System.out.println("Enter ID of the Patient: ");
        int Pid = sc.nextInt();

        String psql = "SELECT p_id FROM tbl_patients WHERE p_id = ? ";
        while (conf.getSingleValue(psql, Pid) == 0) {
            System.out.println("Patient Doesn't Exist, Enter Again: ");
            Pid = sc.nextInt();
        }
        mdoctor md = new mdoctor();
        md.viewdoctor();
        System.out.println("Enter ID of the Doctor: ");
        int MDid = sc.nextInt();

        String mdsql = "SELECT md_id FROM tbl_medical_doctor WHERE md_id = ? ";
        while (conf.getSingleValue(mdsql, MDid) == 0) {
            System.out.println("Doctor Doesn't Exist, Enter Again: ");
            MDid = sc.nextInt();
        }
        sc.nextLine(); // Clear buffer
        System.out.print("\nEnter Symptoms: ");
        String symptoms = sc.nextLine();

        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);

        String treatment = "ON GOING";

        String sql = "INSERT INTO tbl_prescription(p_id, md_id, p_symptoms, p_date, p_treatment) VALUES (?, ?, ?, ?, ?)";
        conf.addpatient(sql, Pid, MDid, symptoms, date, treatment);

        System.out.println("Added Successfully...");
    }

    public void viewPrescription() {
        String qry = "SELECT p_id, patient_fname, md_name, p_symptoms, p_date, p_treatment  FROM tbl_prescription "
                + "LEFT JOIN tbl_patients ON tbl_patients.p_id = tbl_prescription.p_id"
                + " LEFT JOIN tbl_medical_doctor ON tbl_medical_doctor.md_id = tbl_prescription.md_id ";

        String[] hdrs = {"ID", "Patient ", "Doctor ", "Symptoms", "Date", "Treatment"};
        String[] clms = {"p_id", "patient_fname", "md_name", "p_symptoms", "p_date", "p_treatment"};

        config conf = new config();
        conf.viewpatient(qry, hdrs, clms);
    }

    public void updatePrescription() {

        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter the ID to Update: ");
        int pid = sc.nextInt();

        while (conf.getSingleValue("SELECT p_id FROM tbl_prescription WHERE p_id = ?", pid) == 0) {
            System.out.println("Selected ID doesn't exist");
            System.out.println("Select Patient ID again!!: ");
            pid = sc.nextInt();
        }

        // Update logic should be implemented here
    }

    private void deletePrescription() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ID to Delete: ");
        int pid = sc.nextInt();

        String qry = "DELETE FROM tbl_prescription WHERE p_id = ?";
        config conf = new config();
        conf.deletepatient(qry, pid);
    }
}
