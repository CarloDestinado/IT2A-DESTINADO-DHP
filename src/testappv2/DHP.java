package testappv2;

import java.util.Scanner;

public class DHP{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String resp;
       
        DHP test = new DHP();  // Create a single instance for the main menu

        do {
            System.out.println("1. ADD");
            System.out.println("2. VIEW");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. EXIT");

            System.out.print("Enter Action: ");
            int action = sc.nextInt();
            sc.nextLine();  // Consume the newline character

            switch (action) {
                case 1:
                    test.addDHP();
                    break;
                case 2:
                    test.viewDHP();
                    break;
                case 3:
                    test.viewDHP();
                    test.updateDHP();
                    break;
                case 4:
                    test.viewDHP();
                    test.deleteDHP();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;  // Exit the program
                default:
                    System.out.println("Invalid action. Please choose again.");
            }

            System.out.print("Continue? (yes/no): ");
            resp = sc.nextLine();
        } while (resp.equalsIgnoreCase("yes"));

        System.out.println("Thank You!");
    }
    
    public void addDHP() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Patient First Name: ");
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
       
        conf.addDHP(sql, fname, lname, email, address, status);
    }
    
    private void viewDHP() {
        String qry = "SELECT * FROM tbl_patients";
        String[] hdrs = {"ID", "First Name", "Last Name", "Email", "Address", "Status"};
        String[] clms = {"id", "patient_fname", "patient_lname", "email", "address", "status"};

        config conf = new config();
        conf.viewDHP(qry, hdrs, clms);
    }
    
    private void updateDHP() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ID to Update: ");
        int id = sc.nextInt();
        sc.nextLine();  // Consume newline

        System.out.print("New First Name: ");
        String nfname = sc.nextLine();
        System.out.print("New Last Name: ");
        String nlname = sc.nextLine();
        System.out.print("New Email: ");
        String nemail = sc.nextLine();
        System.out.print("New Address: ");
        String naddress = sc.nextLine();
        System.out.print("New Status: ");
        String nstatus = sc.nextLine();

        String qry = "UPDATE tbl_patients SET patient_fname = ?, patient_lname = ?, email = ?, address = ?, status = ? WHERE id = ?";
        config conf = new config();
        conf.updateDHP(qry, nfname, nlname, nemail, naddress, nstatus, id);
    }
    
    private void deleteDHP() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ID to Delete: ");
        int id = sc.nextInt();

        String qry = "DELETE FROM tbl_patients WHERE id = ?";
        config conf = new config();
        conf.deleteDHP(qry, id);
    }
}