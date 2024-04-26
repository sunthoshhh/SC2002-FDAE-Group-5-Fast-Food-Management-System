package fsmjdoc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * The StaffList class represents a list of staff members.
 */
public class StaffList {

    private List<Staff> staffMembers;

    /**
     * Constructs a new StaffList object with an empty list of staff members.
     */
    public StaffList() {
        this.staffMembers = new ArrayList<Staff>();
    }

    /**
     * Prompts the user to enter staff member details and adds the new staff member
     * to the staffMembers list.
     *
     * @param existingBranches A list of existing branches for the staff member.
     */
    public void addstaff(List<Branch> existingBranches) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter staff member details:");
        System.out.println("Enter Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Staff Username:");
        String username = scanner.nextLine();
        System.out.println("Gender (M/F):");
        char gender = scanner.next().charAt(0);
        System.out.println("Enter Designation (S/M/A):");
        char designation = scanner.next().charAt(0);
        scanner.nextLine(); // Consume the leftover newline character
        System.out.println("Enter Age:");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Branch Name:");
        String branch = scanner.nextLine();

        // Add the newly created staff object to the staffMembers list
        addStaff(name, username, gender, designation, age, branch, existingBranches);
        System.out.println("Staff member added successfully!");
        System.out.println();
    }

    /**
     * Creates a new Staff object and adds it to the staffMembers list.
     *
     * @param Name            The name of the staff member.
     * @param userName        The username of the staff member.
     * @param gender          The gender of the staff member.
     * @param designation     The designation of the staff member.
     * @param age             The age of the staff member.
     * @param branchName      The name of the branch the staff member belongs to.
     * @param existingBranches A list of existing branches.
     */
    public void addStaff(String Name, String userName, char gender, char designation, int age, String branchName, List<Branch> existingBranches) {
        Staff staff = new Staff(Name, userName, gender, designation, age, branchName, existingBranches);
        staffMembers.add(staff);
    }

    /**
     * Removes a staff member from the staffMembers list based on the provided name.
     */
    public void removeStaff() {
        // Display staff list
        printAllStaff();

        // Setup Scanner for user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the staff member to remove:");
        String name = scanner.nextLine(); // Read the username input

        // Attempt to remove the staff member
        boolean removed = false;
        for (int i = 0; i < staffMembers.size(); i++) {
            if (staffMembers.get(i).getName().equals(name)) {
                staffMembers.remove(i);
                removed = true;
                break;
            }
        }

        if (removed) {
            System.out.println("Staff member removed successfully.");
            System.out.println();
        } else {
            System.out.println("Staff member with name '" + name + "' not found.");
        }
    }

    /**
     * Allows the user to edit the details of a staff member.
     */
    public void editStaff() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of staff you want to edit details of");
        String Name = scanner.nextLine();
        Staff editstaff = getStaffByUsername(Name);
        System.out.println("Enter which field you want to edit");
        System.out.println("1.Name");
        System.out.println("2.Username");
        System.out.println("3.Gender");
        System.out.println("4.Age");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter updated name");
                scanner.nextLine(); // Consume newline left-over
                String name = scanner.nextLine();
                editstaff.setName(name);
                System.out.println("Staff's name has been updated");
                break;
            case 2:
                System.out.println("Enter updated username");
                scanner.nextLine(); // Consume newline left-over
                String username = scanner.nextLine();
                editstaff.setUserName(username);
                System.out.println("Staff's username has been updated");
                break;
            case 3:
                System.out.println("Enter updated gender");
                scanner.nextLine(); // Consume newline left-over
                char gender = scanner.next().charAt(0);
                editstaff.setGender(gender);
                System.out.println("Staff's gender has been updated");
                break;
            case 4:
                System.out.println("Enter updated age");
                scanner.nextLine(); // Consume newline left-over
                int age = scanner.nextInt();
                editstaff.setAge(age);
                System.out.println("Staff's age has been updated");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    // Other methods like printAllStaff(), getStaffByUsername(), etc.
}