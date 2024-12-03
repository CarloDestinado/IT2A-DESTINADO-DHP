package testappv2;

import java.util.Scanner;

public class mainapp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = true;

        do {
            System.out.println("---WELCOME TO DIAGNOSIS HEALTH PROFILE---");
            System.out.println("-----------------------------------------");
            System.out.println("\n1. PATIENT INFORMATION");
            System.out.println("2. MEDICAL DOCTOR");
            System.out.println("3. PATIENT DIAGNOSIS");
            System.out.println("4. PATIENT PRESCRIPTION");
            System.out.println("5. EXIT");

            // Input validation for action selection
            int action = 0;
            boolean validAction = false;

            while (!validAction) {
                System.out.print("\nEnter Action: ");
                if (sc.hasNextInt()) {
                    action = sc.nextInt();
                    if (action >= 1 && action <= 5) {
                        validAction = true; // Valid action chosen
                    } else {
                        System.out.println("Invalid option. Please choose a number between 1 and 5.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    sc.next(); // Clear the invalid input
                }
            }

            switch (action) {
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
                    prescription pr = new prescription();
                    pr.pInformation();
                    break;
                case 5:
                    System.out.println("Exiting...... type 'yes' to stop the program: ");
                    String resp = sc.next();
                    if (resp.equalsIgnoreCase("yes")) {
                        exit = false;
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (exit);

        System.out.println("Thank you for using the Diagnosis Health Profile System.");
    }
}
