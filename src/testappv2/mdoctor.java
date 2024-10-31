
package testappv2;

import java.util.Scanner;


public class mdoctor {
    public void mdInformation(){
    
    Scanner sc = new Scanner(System.in);
        String resp;
       
        mdoctor md = new mdoctor(); 

        do {
            System.out.println("------------------");
            System.out.println("--MEDICAL DOCTOR--");
            System.out.println("------------------");
            System.out.println("\n1. ADD DOCTOR");
            System.out.println("2. VIEW DOCTOR");
            System.out.println("3. UPDATE DOCTOR");
            System.out.println("4. DELETE DOCTOR");
            System.out.println("5. EXIT");

            System.out.print("\nEnter Action: ");
            int action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 1:
                    md.adddoctor();
                    break;
                case 2:
                    md.viewdoctor();
                    break;
                case 3:
                    md.viewdoctor();
                    md.updatedoctor();
                    break;
                case 4:
                    md.deletedoctor();
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
        public void adddoctor() {
           Scanner sc = new Scanner(System.in);
           config conf = new config();
           System.out.print("\nDoctor Full Name: ");
           String name = sc.nextLine();
           System.out.print("Doctor Specialty: ");
           String specialty = sc.nextLine();
           System.out.print("Doctor Email: ");
           String email = sc.nextLine();
           System.out.print("Doctor Phone No.: ");
           String phone = sc.nextLine();

           String sql = "INSERT INTO tbl_medical_doctor(md_name, md_specialty, md_email, md_phone) VALUES (?, ?, ?, ?)";

           conf.addpatient(sql, name, specialty, email, phone);
        }
        public void viewdoctor() {
            String qry = "SELECT * FROM tbl_medical_doctor";
            String[] hdrs = {"ID", "Doctor", "Specialty", "Email", "Phone No."};
            String[] clms = {"md_id", "md_name", "md_specialty", "md_email", "md_phone"};

            config conf = new config();
            conf.viewpatient(qry, hdrs, clms);
            }
    
        private void updatedoctor() {
      
            Scanner sc = new Scanner(System.in);
            config conf = new config();
            System.out.print("Enter the ID to Update: ");
            int pid = sc.nextInt();

            while(conf.getSingleValue("SELECT md_id FROM tbl_medical_doctor WHERE md_id = ?",pid)== 0){
            System.out.println("Selected ID doesn't exist");
            System.out.println("Select Patient ID again!!: ");
            pid=sc.nextInt();
        }
            sc.nextLine(); 
            System.out.print("\nNew Name (Full Name): ");
            String nname = sc.nextLine();
            System.out.print("New Specialty: ");
            String nspecialty = sc.nextLine();
            System.out.print("New Email: ");
            String nemail = sc.nextLine();
            System.out.print("New Phone No.: ");
            String nphone = sc.nextLine();

            String qry = "UPDATE tbl_medical_doctor SET md_name = ?, md_specialty = ?, md_email = ?, md_phone = ? WHERE md_name = ?";

            conf.updatepatient(qry, nname, nspecialty, nemail, nphone, pid);
        }
        private void deletedoctor() {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the ID to Delete: ");
            int id = sc.nextInt();

            String qry = "DELETE FROM tbl_patients WHERE p_id = ?";
            config conf = new config();
            conf.deletepatient(qry, id);
        }
            
    }
            

