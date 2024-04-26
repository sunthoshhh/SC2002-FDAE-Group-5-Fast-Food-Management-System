package fsmjdoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The ManagerList class represents a list of Manager members.
 * It provides methods to add new managers and retrieve or display existing managers.
 */
public class ManagerList {
    private List<Manager> managerMembers;

    /**
     * Constructs a new ManagerList instance with an empty list of managers.
     */
    public ManagerList() {
        this.managerMembers = new ArrayList<>();
    }

    /**
     * Adds a new Manager to the list.
     *
     * @param Name The name of the manager.
     * @param userName The username of the manager.
     * @param gender The gender of the manager.
     * @param designation The designation of the manager.
     * @param age The age of the manager.
     * @param branchName The name of the branch the manager is assigned to.
     * @param existingBranches A list of existing branches.
     */
    public void addStaff(String Name, String userName, char gender, char designation, int age, String branchName, List<Branch> existingBranches) {
        Manager manager = new Manager(Name, userName, gender, designation, age, branchName, existingBranches);
        managerMembers.add(manager);
    }

    /**
     * Retrieves a Manager from the list by their username.
     *
     * @param username The username of the manager to be retrieved.
     * @return The Manager with the specified username, or null if not found.
     */
    public Manager getStaffByUsername(String username) {
        for (Manager manager: managerMembers) {
            if (manager.getUserName().equalsIgnoreCase(username)) {
                return manager;
            }
        }
        return null; // Return null if no matching manager is found
    }

    /**
     * Prints a list of all registered staff members.
     * Displays the username and designation of each manager.
     */
    public void printAllStaff() {
        if (managerMembers.isEmpty()) {
            System.out.println("No staff members are currently registered.");
        } else {
            System.out.println("List of all staff members:");
            for (Manager manager : managerMembers) {
                System.out.println(manager.getUserName() + " - " + manager.getDesignation());
            }
        }
    }
}
