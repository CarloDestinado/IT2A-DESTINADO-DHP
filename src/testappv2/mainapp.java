
package testappv2;

import java.util.Scanner;

public class mainapp {
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        
        do{
            System.out.println("---WELCOME TO DIAGNOSIS HEALTH PROFILE---");
            System.out.println("-----------------------------------------");
            System.out.println("\n1. PATIENT INFORMATION");
            System.out.println("2. MEDICAL DOCTOR");
            System.out.println("3. PATIENT DIAGNOSIS");
            System.out.println("4. EXIT");
           
            System.out.println("\nEnter Action: ");
            int action = sc.nextInt();

            switch(action){
                case 1:
                    patient cs = new patient();
                    cs.pinformation();
                    break; 
                case 2: 
                    mdoctor md = new mdoctor();
                    md.mdInformation();
                    break;
                case 3:
                    diagnosis pd = new diagnosis(); 
                    pd.dInformation();
                    break;

                case 4:
                    System.out.println("Exiting...... type 'yes' kung ayaw mo na: ");
                    String resp = sc.next();
                    if(resp.equalsIgnoreCase("yes")){
                        exit = false;
                    }
                    break;
            }     
        }while(exit);
    }
}
