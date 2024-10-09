package TestAppV2;

import java.util.Scanner;

public class TestAppV2 {
    
   
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String resp;
        do{

            System.out.println("1. ADD");
            System.out.println("2. VIEW");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. EXIT");

            System.out.print("Enter Action: ");
            int action = sc.nextInt();
            TestAppV2 test = new TestAppV2();
            switch(action){
                case 1:
                    test.addEmployee();
                break;
                case 2:
                    test.viewEmployee();
                break;
                case 3:
                    test.viewEmployee();
                    test.updateEmployee();
                break;
                case 4:
                    test.viewEmployee();
                    test.deleteEmployee();
                    test.viewEmployee();
                break;
            }
            
            System.out.print("Continue? ");
            resp = sc.next();

        }while(resp.equalsIgnoreCase("yes"));
            System.out.println("Thank You!");

    }
    
    public void addEmployee(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Employee First Name: ");
        String fname = sc.nextLine();
        System.out.print("Employee Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Employee Email: ");
        String email = sc.nextLine();
        System.out.print("Employee Status: ");
        String status = sc.nextLine();

        String sql = "INSERT INTO tbl_employees (e_fname, e_lname, e_email, e_status) VALUES (?, ?, ?, ?)";


        conf.addRecord(sql, fname, lname, email, status);
    }
    
    private void viewEmployee() {
        
        String qry = "SELECT * FROM tbl_employees";
        String[] hdrs = {"ID", "First Name", "Last Name", "Email", "Status"};
        String[] clms = {"e_id", "e_fname", "e_lname", "e_email", "e_status"};

        config conf = new config();
        conf.viewRecords(qry, hdrs, clms);
    }
    
    private void updateEmployee(){
    
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter the ID to Update: ");
        int id = sc.nextInt();
        
        System.out.print("Enter new First Name: ");
        String nfname = sc.next();
        System.out.print("Enter new Last Name: ");
        String nlname = sc.next();
        System.out.print("Enter new Email: ");
        String nemail = sc.next();
        System.out.print("Enter new Status: ");
        String nstatus = sc.next();
        
        String qry = "UPDATE tbl_employees SET e_fname = ?, e_lname = ?, e_email = ?, e_status = ? WHERE e_id = ?";
        
        config conf = new config();
        conf.updateRecord(qry, nfname, nlname, nemail, nstatus, id);
        
    }
    
    private void deleteEmployee(){
        
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter the ID to Delete: ");
        int id = sc.nextInt();
        
        String qry = "DELETE FROM tbl_employees WHERE e_id = ?";
        
        config conf = new config();
        conf.deleteRecord(qry, id);
    
    }
    
    
}
