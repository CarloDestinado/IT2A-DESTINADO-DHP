
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
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
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
         System.out.print("\nEnter Diagnosis: ");
            String diag = sc.nextLine();
           
     LocalDate currdate = LocalDate.now();
      DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
      String date = currdate.format(format);
      
      String treatment = "ON GOING";
      
      String sql = "INSERT INTO tbl_diagnosis(p_id, md_id, d_symptoms, d_date, d_treatment) VALUES (?, ?, ?, ?, ?6)";
      conf.addpatient(sql, Pid, MDid, diag, date, treatment);
            System.out.println("Added Successfully...");
      
    }
}

    
