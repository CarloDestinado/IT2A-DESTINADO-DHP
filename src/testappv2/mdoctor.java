package testappv2;

import java.util.Scanner;

public class mdoctor {
    public void mdInformation() {
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
            sc.nextLine(); // Consume newline

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
                    md.viewdoctor();
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

        String name = getValidatedInput(sc, "Doctor Full Name: ");
        String specialty = getValidatedInput(sc, "Doctor Specialty: ");
        String email = getValidatedInput(sc, "Doctor Email: ");
        String phone = getValidatedInput(sc, "Doctor Phone No.: ");

        String sql = "INSERT INTO tbl_medical_doctor(md_name, md_specialty, md_email, md_phone) VALUES (?, ?, ?, ?)";

        conf.addpatient(sql, name, specialty, email, phone);
        System.out.println("Doctor added successfully.");
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

        while (conf.getSingleValue("SELECT md_id FROM tbl_medical_doctor WHERE md_id = ?", pid) == 0) {
            System.out.println("Selected ID doesn't exist. Please enter a valid ID.");
            System.out.print("Enter the ID to Update: ");
            pid = sc.nextInt();
        }
        sc.nextLine(); // Consume newline

        String nname = getValidatedInput(sc, "New Name (Full Name): ");
        String nspecialty = getValidatedInput(sc, "New Specialty: ");
        String nemail = getValidatedInput(sc, "New Email: ");
        String nphone = getValidatedInput(sc, "New Phone No.: ");

        String qry = "UPDATE tbl_medical_doctor SET md_name = ?, md_specialty = ?, md_email = ?, md_phone = ? WHERE md_id = ?";

        conf.updatepatient(qry, nname, nspecialty, nemail, nphone, pid);
        System.out.println("Doctor updated successfully.");
    }

    private void deletedoctor() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Enter the ID to Delete: ");
        int id = sc.nextInt();

        while (conf.getSingleValue("SELECT md_id FROM tbl_medical_doctor WHERE md_id = ?", id) == 0) {
            System.out.println("Selected ID doesn't exist. Please enter a valid ID.");
            System.out.print("Enter the ID to Delete: ");
            id = sc.nextInt();
        }

        String qry = "DELETE FROM tbl_medical_doctor WHERE md_id = ?";
        conf.deletepatient(qry, id);
        System.out.println("Doctor deleted successfully.");
    }

    private String getValidatedInput(Scanner sc, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("This field cannot be empty. Please enter a valid value.");
            }
        } while (input.isEmpty());
        return input;
    }
}
