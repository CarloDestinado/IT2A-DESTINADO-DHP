package testappv2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class diagnosis {
    
    public void dInformation() {
        Scanner sc = new Scanner(System.in);
        String resp;

        diagnosis pd = new diagnosis(); 

        do {
            System.out.println("---------------------");
            System.out.println("--PATIENT DIAGNOSIS--");
            System.out.println("---------------------");
            System.out.println("\n1. ADD DIAGNOSIS");
            System.out.println("2. VIEW DIAGNOSIS");
            System.out.println("3. UPDATE DIAGNOSIS");
            System.out.println("4. DELETE DIAGNOSIS");
            System.out.println("5. EXIT");

            System.out.print("\nEnter Action: ");
            int action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 1:
                    pd.addDiagnosis();
                    pd.viewDiagnosis(); 
                    break;
                case 2:
                    pd.viewDiagnosis(); 
                    break;
                case 3:
                    pd.viewDiagnosis(); 
                    pd.updatediagnosis();   
                    break;
                case 4:
                    pd.viewDiagnosis(); 
                    pd.deleteDiagnosis();
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
    
    public void addDiagnosis() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        patient cs = new patient();
        cs.viewpatient();
        
        // Patient ID Validation
        int Pid;
        do {
            System.out.println("Enter ID of the Patient: ");
            Pid = sc.nextInt();
            sc.nextLine(); // Consume newline
            
            String psql = "SELECT p_id FROM tbl_patients WHERE p_id = ?";
            if (conf.getSingleValue(psql, Pid) == 0) {
                System.out.println("Patient doesn't exist. Please enter a valid Patient ID.");
            }
        } while (conf.getSingleValue("SELECT p_id FROM tbl_patients WHERE p_id = ?", Pid) == 0);

        // Doctor ID Validation
        mdoctor md = new mdoctor();
        md.viewdoctor();
        
        int MDid;
        do {
            System.out.println("Enter ID of the Doctor: ");
            MDid = sc.nextInt();
            sc.nextLine(); // Consume newline
            
            String mdsql = "SELECT md_id FROM tbl_medical_doctor WHERE md_id = ?";
            if (conf.getSingleValue(mdsql, MDid) == 0) {
                System.out.println("Doctor doesn't exist. Please enter a valid Doctor ID.");
            }
        } while (conf.getSingleValue("SELECT md_id FROM tbl_medical_doctor WHERE md_id = ?", MDid) == 0);

        // Current date and treatment status
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);
        String treatment = "ON GOING";
        
        // Insert diagnosis
        String sql = "INSERT INTO tbl_diagnosis(p_id, md_id, d_date, d_treatment) VALUES (?, ?, ?, ?)";
        conf.addpatient(sql, Pid, MDid, date, treatment);
        
        System.out.println("Diagnosis added successfully...");
    }

    public void viewDiagnosis() {
        String qry = "SELECT d_id, patient_fname, md_name, d_date, d_treatment  FROM tbl_diagnosis "
                + "LEFT JOIN tbl_patients ON tbl_patients.p_id = tbl_diagnosis.p_id"
                + " LEFT JOIN tbl_medical_doctor ON tbl_medical_doctor.md_id = tbl_diagnosis.md_id ";
                
        String[] hdrs = {"ID", "Patient", "Doctor", "Date", "Treatment"};
        String[] clms = {"d_id", "patient_fname", "md_name", "d_date", "d_treatment"};

        config conf = new config();
        conf.viewpatient(qry, hdrs, clms);
    }

    private void updatediagnosis() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        // Diagnosis ID validation
        System.out.print("Enter the ID to Update: ");
        int pid = sc.nextInt();
        sc.nextLine();
        while (conf.getSingleValue("SELECT d_id FROM tbl_diagnosis WHERE d_id = ?", pid) == 0) {
            System.out.println("Selected ID doesn't exist. Please enter a valid ID.");
            System.out.print("Enter the ID to Update: ");
            pid = sc.nextInt();
            sc.nextLine(); // Consume newline
        }

        // New Patient ID validation
        patient cs = new patient();
        cs.viewpatient();
        int nPid;
        do {
            System.out.println("Enter New ID of the Patient: ");
            nPid = sc.nextInt();
            sc.nextLine(); // Consume newline

            String psql = "SELECT p_id FROM tbl_patients WHERE p_id = ?";
            if (conf.getSingleValue(psql, nPid) == 0) {
                System.out.println("Patient doesn't exist. Please enter a valid Patient ID.");
            }
        } while (conf.getSingleValue("SELECT p_id FROM tbl_patients WHERE p_id = ?", nPid) == 0);

        // New Doctor ID validation
        mdoctor md = new mdoctor();
        md.viewdoctor();
        int nMDid;
        do {
            System.out.println("Enter New ID of the Doctor: ");
            nMDid = sc.nextInt();
            sc.nextLine(); // Consume newline

            String mdsql = "SELECT md_id FROM tbl_medical_doctor WHERE md_id = ?";
            if (conf.getSingleValue(mdsql, nMDid) == 0) {
                System.out.println("Doctor doesn't exist. Please enter a valid Doctor ID.");
            }
        } while (conf.getSingleValue("SELECT md_id FROM tbl_medical_doctor WHERE md_id = ?", nMDid) == 0);

        // Current date and treatment status
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);
        String treatment = "ON GOING";
        
        // Update diagnosis
        String sql = "UPDATE tbl_diagnosis SET p_id = ?, md_id = ?, d_date = ?, d_treatment = ? WHERE d_id = ?";
        conf.updatepatient(sql, nPid, nMDid, date, treatment, pid);
        
        System.out.println("Diagnosis updated successfully...");
    }

    private void deleteDiagnosis() {
        Scanner sc = new Scanner(System.in);

        // Diagnosis ID validation
        System.out.print("Enter the ID to Delete: ");
        int did = sc.nextInt();
        sc.nextLine(); // Consume newline

        String qry = "DELETE FROM tbl_diagnosis WHERE d_id = ?";
        config conf = new config();
        conf.deletepatient(qry, did);

        System.out.println("Diagnosis deleted successfully...");
    }
}
