package testappv2;

import java.util.Scanner;

public class patient{

    public void pinformation(){
        Scanner sc = new Scanner(System.in);
        String resp;
       
        patient test = new patient(); 

        do {
            System.out.println("-----------------------");
            System.out.println("--PATIENT INFORMATION--");
            System.out.println("-----------------------");
            System.out.println("\n1. ADD PATIENT");
            System.out.println("2. VIEW PATIENT");
            System.out.println("3. UPDATE PATIENT");
            System.out.println("4. DELETE PATIENT");
            System.out.println("5. EXIT");

            System.out.print("\nEnter Action: ");
            int action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 1:
                    test.addpatient();
                    break;
                case 2:
                    test.viewpatient();
                    break;
                case 3:
                    test.viewpatient();
                    test.updatepatient();
                    break;
                case 4:
                    test.viewpatient();
                    test.deletepatient();
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
    
    public void addpatient() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("\nPatient First Name: ");
        String fname = sc.nextLine();
        System.out.print("Patient Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Patient Email: ");
        String email = sc.nextLine();
        System.out.print("Patient Address: ");
        String address = sc.nextLine();
        System.out.print("Patient Status: ");
        String status = sc.nextLine();

        String sql = "INSERT INTO tbl_patients(patient_fname, patient_lname, email, address, status) VALUES (?, ?, ?, ?, ?)";
       
        conf.addpatient(sql, fname, lname, email, address, status);
    }
    
    private void viewpatient() {
        String qry = "SELECT * FROM tbl_patients";
        String[] hdrs = {"ID", "First Name", "Last Name", "Email", "Address", "Status"};
        String[] clms = {"p_id", "patient_fname", "patient_lname", "email", "address", "status"};

        config conf = new config();
        conf.viewpatient(qry, hdrs, clms);
    }
    
    private void updatepatient() {
      
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter the ID to Update: ");
        int pid = sc.nextInt();
        
        while(conf.getSingleValue("SELECT p_id FROM tbl_patients WHERE p_id = ?",pid)== 0){
            System.out.println("Selected ID doesn't exist");
            System.out.println("Select Patient ID again!!: ");
            pid=sc.nextInt();
      }
        sc.nextLine(); 
        System.out.print("\nNew First Name: ");
        String nfname = sc.nextLine();
        System.out.print("New Last Name: ");
        String nlname = sc.nextLine();
        System.out.print("New Email: ");
        String nemail = sc.nextLine();
        System.out.print("New Address: ");
        String naddress = sc.nextLine();
        System.out.print("New Status: ");
        String nstatus = sc.nextLine();

        String qry = "UPDATE tbl_patients SET patient_fname = ?, patient_lname = ?, email = ?, address = ?, status = ? WHERE p_id = ?";
        
        conf.updatepatient(qry, nfname, nlname, nemail, naddress, nstatus, pid);
    }
    
    private void deletepatient() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ID to Delete: ");
        int id = sc.nextInt();

        String qry = "DELETE FROM tbl_patients WHERE p_id = ?";
        config conf = new config();
        conf.deletepatient(qry, id);
    }
}