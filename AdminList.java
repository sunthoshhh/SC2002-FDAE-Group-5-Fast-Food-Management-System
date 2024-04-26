package fsmjdoc;

import java.util.ArrayList;
import java.util.List;

/**
 * The AdminList class manages a list of Admin members, allowing the addition of new staff, retrieval of staff members by username,
 * and printing a list of all staff members.
 */
public class AdminList {
    private List<Admin> adminMembers;

    /**
     * Constructs an AdminList instance with an empty list of Admin members.
     */
    public AdminList() {
        this.adminMembers = new ArrayList<Admin>();
    }

    /**
     * Adds a new staff member to the list.
     *
     * @param Name           The name of the staff member.
     * @param userName       The username of the staff member.
     * @param gender         The gender of the staff member.
     * @param designation    The designation of the staff member.
     * @param age            The age of the staff member.
     * @param branchName     The branch name of the staff member.
     * @param existingBranches A list of existing Branches associated with the staff member.
     */
    public void addStaff(String Name, String userName, char gender, char designation, int age, String branchName, List<Branch> existingBranches) {
        Admin admin = new Admin(Name, userName, gender, designation, age, branchName, existingBranches);
        adminMembers.add(admin);
    }

    /**
     * Retrieves an Admin staff member by their username.
     *
     * @param username The username of the staff member to be retrieved.
     * @return The Admin staff member with the specified username, or null if not found.
     */
    public Admin getStaffByUsername(String username) {
        for (Admin admin: adminMembers) {
            if (admin.getUserName().equalsIgnoreCase(username)) {
                return admin;
            }
        }
        return null;  // Return null if no matching staff member is found
    }

    /**
     * Prints a list of all staff members with their usernames and designations.
     */
    public void printAllStaff() {
        if (adminMembers.isEmpty()) {
            System.out.println("No staff members are currently registered.");
        } else {
            System.out.println("List of all staff members:");
            for (Admin admin : adminMembers) {
                System.out.println(admin.getUserName() + " - " + admin.getDesignation());
            }
        }
    }
}
