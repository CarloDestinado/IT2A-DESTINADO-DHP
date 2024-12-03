
package testappv2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class diagnosis {
    public void dInformation(){
    
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
             
            System.out.println("Enter ID of the Patient: ");
             int Pid = sc.nextInt();
             
        String psql = "SELECT p_id FROM tbl_patients WHERE p_id = ? ";
             while(conf.getSingleValue(psql, Pid) == 0){
                 System.out.println("Patient Doesn't Exist, Enter Again: ");
                 Pid = sc.nextInt(); 
        }
             mdoctor md = new mdoctor();
             md.viewdoctor();
            System.out.println("Enter ID of the Doctor: ");
             int MDid = sc.nextInt();
             
        String mdsql = "SELECT md_id FROM tbl_medical_doctor WHERE md_id = ? ";
             while(conf.getSingleValue(mdsql, MDid) == 0){
            System.out.println("Doctor Doesn't Exist, Enter Again: ");
                 MDid = sc.nextInt();
        }
         
           
     LocalDate currdate = LocalDate.now();
      DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
      String date = currdate.format(format);
      
      String treatment = "ON GOING";
      
      String sql = "INSERT INTO tbl_diagnosis(p_id, md_id, d_date, d_treatment) VALUES (?, ?, ?, ?)";
      conf.addpatient(sql, Pid, MDid, date, treatment);
            
      System.out.println("Added Successfully...");
      
    }
        public void viewDiagnosis() {
        String qry = "SELECT d_id, patient_fname, md_name, d_date, d_treatment  FROM tbl_diagnosis "
                + "LEFT JOIN tbl_patients ON tbl_patients.p_id = tbl_diagnosis.p_id"
                + " LEFT JOIN tbl_medical_doctor ON tbl_medical_doctor.md_id = tbl_diagnosis.md_id ";
                
        String[] hdrs = {"ID", "Patient ", "Doctor ", "Date", "Treatment"};
        String[] clms = {"d_id", "patient_fname", "md_name", "d_date", "d_treatment"};

        config conf = new config();
        conf.viewpatient(qry, hdrs, clms);
        
        }
        
        private void updatediagnosis() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Enter the ID to Update: ");
        int pid = sc.nextInt();
        sc.nextLine();
             while (conf.getSingleValue("SELECT d_id FROM tbl_diagnosis WHERE d_id = ?", pid) == 0) {
            System.out.println("Selected ID doesn't exist. Please enter a valid ID.");
            System.out.print("Enter the ID to Update: ");
            pid = sc.nextInt();
            sc.nextLine(); // Consume newline
        }
             patient cs = new patient();
             cs.viewpatient();
            System.out.println("Enter New ID of the Patient: ");
             int nPid = sc.nextInt();
             
        String psql = "SELECT p_id FROM tbl_patients WHERE p_id = ? ";
             while(conf.getSingleValue(psql, nPid) == 0){
                 System.out.println("Patient Doesn't Exist, Enter Again: ");
                 nPid = sc.nextInt(); 
        }
             mdoctor md = new mdoctor();
             md.viewdoctor();
            System.out.println("Enter New ID of the Doctor: ");
             int nMDid = sc.nextInt();
             
        String mdsql = "SELECT md_id FROM tbl_medical_doctor WHERE md_id = ? ";
             while(conf.getSingleValue(mdsql, nMDid) == 0){
            System.out.println("Doctor Doesn't Exist, Enter Again: ");
                 nMDid = sc.nextInt();
        }
         
           
     LocalDate currdate = LocalDate.now();
      DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
      String date = currdate.format(format);
      
      String treatment = "ON GOING";
      
      String sql = "INSERT INTO tbl_diagnosis(p_id, md_id, d_date, d_treatment) VALUES (?, ?, ?, ?)";
      conf.updatepatient(sql, nPid, nMDid, date, treatment);
            
      System.out.println("Added Successfully...");
      
    }
        private void deleteDiagnosis() {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the ID to Delete: ");
            int did = sc.nextInt();

            String qry = "DELETE FROM tbl_diagnosis WHERE d_id = ?";
            config conf = new config();
            conf.deletepatient(qry, did);
        }
}

    
