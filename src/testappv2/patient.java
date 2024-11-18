package testappv2;

import java.util.Scanner;

public class patient {

    public void pinformation() {
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
            sc.nextLine(); // Consume newline

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

        String fname = getValidatedInput(sc, "Patient First Name: ");
        String lname = getValidatedInput(sc, "Patient Last Name: ");
        String email = getValidatedInput(sc, "Patient Email: ");
        String address = getValidatedInput(sc, "Patient Address: ");
        String status = getValidatedInput(sc, "Patient Status: ");

        String sql = "INSERT INTO tbl_patients(patient_fname, patient_lname, email, address, status) VALUES (?, ?, ?, ?, ?)";

        conf.addpatient(sql, fname, lname, email, address, status);
        System.out.println("Patient added successfully.");
    }

    public void viewpatient() {
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
        sc.nextLine(); // Consume newline

        while (conf.getSingleValue("SELECT p_id FROM tbl_patients WHERE p_id = ?", pid) == 0) {
            System.out.println("Selected ID doesn't exist. Please enter a valid ID.");
            System.out.print("Enter the ID to Update: ");
            pid = sc.nextInt();
            sc.nextLine(); // Consume newline
        }

        String nfname = getValidatedInput(sc, "New First Name: ");
        String nlname = getValidatedInput(sc, "New Last Name: ");
        String nemail = getValidatedInput(sc, "New Email: ");
        String naddress = getValidatedInput(sc, "New Address: ");
        String nstatus = getValidatedInput(sc, "New Status: ");

        String qry = "UPDATE tbl_patients SET patient_fname = ?, patient_lname = ?, email = ?, address = ?, status = ? WHERE p_id = ?";

        conf.updatepatient(qry, nfname, nlname, nemail, naddress, nstatus, pid);
        System.out.println("Patient updated successfully.");
    }

    private void deletepatient() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Enter the ID to Delete: ");
        int id = sc.nextInt();

        while (conf.getSingleValue("SELECT p_id FROM tbl_patients WHERE p_id = ?", id) == 0) {
            System.out.println("Selected ID doesn't exist. Please enter a valid ID.");
            System.out.print("Enter the ID to Delete: ");
            id = sc.nextInt();
        }

        String qry = "DELETE FROM tbl_patients WHERE p_id = ?";
        conf.deletepatient(qry, id);
        System.out.println("Patient deleted successfully.");
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
