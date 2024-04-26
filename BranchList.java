package fsmjdoc;

import java.util.ArrayList;
import java.util.List;

/**
 * The BranchList class is a singleton that maintains a list of Branch objects.
 * It provides methods for adding, retrieving, and removing branches from the list.
 */
public class BranchList {
    private static BranchList instance; // Singleton instance
    private List<Branch> branches;

    /**
     * Private constructor to prevent external instantiation.
     * Initializes the list of branches.
     */
    private BranchList() {
        branches = new ArrayList<Branch>();
    }

    /**
     * Retrieves the singleton instance of BranchList.
     * Uses double-checked locking for thread-safe instantiation.
     *
     * @return The singleton instance of BranchList.
     */
    public static BranchList getInstance() {
        if (instance == null) {
            synchronized (BranchList.class) { // Thread-safe instantiation
                if (instance == null) {
                    instance = new BranchList();
                }
            }
        }
        return instance;
    }

    /**
     * Adds a new branch to the list of branches.
     *
     * @param branch The Branch object to be added.
     */
    public void addBranch(Branch branch) {
        branches.add(branch);
    }

    /**
     * Retrieves a branch by its name from the list of branches.
     *
     * @param name The name of the branch to be retrieved.
     * @return The Branch object with the specified name, or null if not found.
     */
    public Branch getBranchByName(String name) {
        for (Branch branch : branches) {
            if (branch.getBranchName().equals(name)) {
                return branch;
            }
        }
        return null; // Returns null if no branch is found
    }

    /**
     * Removes a branch by its name from the list of branches.
     *
     * @param name The name of the branch to be removed.
     * @return True if the branch was successfully removed, false otherwise.
     */
    public boolean removeBranch(String name) {
        for (Branch branch : branches) {
            if (branch.getBranchName().equals(name)) {
                branches.remove(branch);
                return true; // Return true if the branch was successfully removed
            }
        }
        return false; // Return false if no branch with the given name was found
    }

    // Additional methods for managing branches can be added here
}
