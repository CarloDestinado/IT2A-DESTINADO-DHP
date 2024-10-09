/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testappv2;

import java.util.Scanner;


    public class main {

    public static void main(String[] args) {
        TestAppV2 app = new TestAppV2();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Welcome to the Patient Management System");
            System.out.println("1. Add Patient Record");
            System.out.println("2. View Patients Record");
            System.out.println("3. Update Patient Record");
            System.out.println("4. Delete Record");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    app.addPatients();
                    break;
                case 2:
                    app.viewPatients();
                    break;
                case 3:
                    app.viewPatients();
                    app.updatePatients();
                       break;
                case 4: 
                    app.viewPatients();
                    app.deleteRecord();
                    break;
                case 5:  
                System.out.println("Exiting...");
                    scanner.close();
                    return; // Exit the loop and terminate the program
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
