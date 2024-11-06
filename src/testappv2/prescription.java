
package testappv2;

import java.util.Scanner;


public class prescription {
    public void pPrescription(){
    Scanner sc = new Scanner(System.in);
    String resp;
         
    prescription pres = new prescription(); 
         
         do {
            System.out.println("-----------------------");
            System.out.println("--PATIENT PRESCRIPTION--");
            System.out.println("-----------------------");
            System.out.println("\n1. ADD PATIENT PRESCRIPTION");
            System.out.println("2. VIEW PATIENT PRESCRIPTION");
            System.out.println("3. UPDATE PATIENT PRESCRIPTION");
            System.out.println("4. DELETE PATIENT PRESCRIPTION");
            System.out.println("5. EXIT");

            System.out.print("\nEnter Action: ");
            int action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 1:
                    pres.addprescription();
                    break;
                case 2:
                    pres.viewprescription();
                    break;
                case 3:
                    pres.viewprescription();
                    pres.updateprescription();
                    break;
                case 4:
                    pres.viewprescription();
                    pres.deleteprescription();
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
    
            public void addprescription() {
             Scanner sc = new Scanner(System.in);
             config conf = new config();
             patient cs = new patient();
             cs.viewpatient();
             
            System.out.println("Enter ID of the Patient: ");
             int presc_id = sc.nextInt();
             
        String pressql = "SELECT p_id FROM tbl_prescription WHERE p_id = ? ";
             while(conf.getSingleValue(pressql, presc_id) == 0){
                 System.out.println("Patient Doesn't Exist, Enter Again: ");
                 presc_id = sc.nextInt(); 
        }
}
            
}