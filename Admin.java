package fsmjdoc;

import java.util.List;
import java.util.Scanner;

/**
 * Represents an admin who extends the Staff class and has additional methods for managing staff and branches.
 */
public class Admin extends Staff {

    /**
     * Constructs an Admin object with the specified parameters.
     *
     * @param Name The name of the admin.
     * @param userName The username of the admin.
     * @param gender The gender of the admin.
     * @param designation The designation of the admin.
     * @param age The age of the admin.
     * @param branchName The name of the branch.
     * @param existingBranches The list of existing branches.
     */
    public Admin(String Name, String userName, char gender, char designation, int age, String branchName, List<Branch> existingBranches) {
        super(Name, userName, gender, designation, age, branchName, existingBranches);
    }

    /**
     * Adds a new staff member to the staff list and assigns them to existing branches.
     *
     * @param staffList The list of staff members.
     * @param existingBranches The list of existing branches.
     */
    void addStaff(StaffList staffList, List<Branch> existingBranches) {
        staffList.addStaff(existingBranches);
    }

    /**
     * Removes a staff member from the staff list.
     *
     * @param staffList The list of staff members.
     */
    void removeStaff(StaffList staffList) {
        staffList.removeStaff();
    }

    /**
     * Edits a staff member's details in the staff list.
     *
     * @param staffList The list of staff members.
     */
    void editStaff(StaffList staffList) {
        staffList.editStaff();
    }

    /**
     * Assigns staff members to existing branches.
     *
     * @param existingBranches The list of existing branches.
     * @param staffList The list of staff members.
     */
    void assignStaff(List<Branch> existingBranches, StaffList staffList) {
        staffList.assignStaff(existingBranches);
    }

    /**
     * Assigns a manager to existing branches.
     *
     * @param existingBranches The list of existing branches.
     * @param staffList The list of staff members.
     */
    void assignManager(List<Branch> existingBranches, StaffList staffList) {
        staffList.assignManager(existingBranches);
    }

    /**
     * Promotes staff members and assigns them to manager positions.
     *
     * @param existingBranches The list of existing branches.
     * @param staffList The list of staff members.
     * @param managerList The list of managers.
     */
    void promoteStaff(List<Branch> existingBranches, StaffList staffList, ManagerList managerList) {
        staffList.promoteStaff(existingBranches, managerList);
    }

    /**
     * Opens a new branch and allows the admin to add a menu item to the branch.
     *
     * @param staffList The list of staff members.
     * @param branchName The name of the new branch.
     * @param existingBranches The list of existing branches.
     */
    public void openBranch(StaffList staffList, String branchName, List<Branch> existingBranches) {
        Scanner scanner = new Scanner(System.in);
        Branch branch = new Branch(branchName);
        existingBranches.add(branch);
        System.out.println("New branch opened: " + branch.getName());
        System.out.println("Enter the name of the item you want to add:");
        String item = scanner.nextLine();
        System.out.println("Enter the price of the item you want to add:");
        double itemCost = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character
        System.out.println("Enter the category of the item you want to add:");
        String category = scanner.nextLine();
        MenuItem newItem = new MenuItem(item, itemCost, category);
        branch.addMenuItem(newItem);
        System.out.println("Item added successfully.");
    }

    /**
     * Closes a branch, removes all staff associated with the branch, and removes the branch from the branch list.
     *
     * @param staffList The list of staff members.
     * @param branchName The name of the branch to be closed.
     * @param existingBranches The list of existing branches.
     */
    public void closeBranch(StaffList staffList, String branchName, List<Branch> existingBranches) {
        // Scanner not needed as we are not using user input here
        // Remove all staff associated with the branch
        staffList.removeStaffByBranch(branchName);

        // Remove the branch from the branch list
        Branch branchToRemove = null;
        for (Branch branch : existingBranches) {
            if (branch.getName().equals(branchName)) {
                branchToRemove = branch;
                break;
            }
        }

        if (branchToRemove == null) {
            System.out.println("Branch not found.");
            return;
        }

        boolean branchRemoved = existingBranches.remove(branchToRemove);

        if (branchRemoved) {
            System.out.println("Branch closed and all associated staff removed successfully.");
        } else {
            System.out.println("Branch not found.");
        }
    }

    /**
     * Filters and views staff members based on the chosen filter criteria.
     *
     * @param staffList The list of staff members.
     */
    void viewFilteredStaff(StaffList staffList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How would you like to filter the staff?");
        System.out.println("1. Branch");
        System.out.println("2. Age");
        System.out.println("3. Role");
        System.out.println("4. Gender");
        int choice = scanner.nextInt(); // Read the choice as an integer

        switch (choice) {
            case 1:
                System.out.println("Enter the branch:");
                scanner.nextLine(); // Consume the newline character
                String branch = scanner.nextLine();
                staffList.printStaffByBranch(branch);
                break;
            case 2:
                System.out.println("Enter the age:");
                int age = scanner.nextInt();
                staffList.printStaffByAge(age);
                break;
            case 3:
                System.out.println("Enter the role:");
                scanner.nextLine(); // Consume the newline character
                char role = scanner.next().charAt(0);
                staffList.printStaffByDesignation(role);
                break;
            case 4:
                System.out.println("Enter the gender:");
                scanner.nextLine(); // Consume the newline character
                char gender = scanner.next().charAt(0);
                staffList.printStaffByGender(gender);
                break;
            default:
                System.out.println("Invalid option, please select a valid number (1-4).");
                break;
        }
    }
}
