package fsmjdoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * The Menu class represents a collection of menu items for different branches.
 * It maintains a map between a branch and its list of menu items.
 */
public class Menu {
    private Map<Branch, List<MenuItem>> branchMenus;

    /**
     * Constructs a Menu instance and populates branch menus for the given existing branches.
     *
     * @param existingBranches A list of existing branches for which menus need to be populated.
     */
    public Menu(List<Branch> existingBranches) {
        branchMenus = new HashMap<>();
        populateBranchMenus(existingBranches);
    }

    /**
     * Populates menus for each existing branch with predefined menu items.
     *
     * @param existingBranches A list of existing branches to populate menus for.
     */
    private void populateBranchMenus(List<Branch> existingBranches) {
        for (Branch branch : existingBranches) {
            List<MenuItem> menuItems = branch.getBranchItems();
            // Populate menu items based on branch name
            if (branch.getName().equals("NTU")) {
                menuItems.add(new MenuItem("FRIES", 3.2, "side"));
                menuItems.add(new MenuItem("3PC set meal", 9.9, "set meal"));
                menuItems.add(new MenuItem("chicken nugget", 6.6, "side"));
            } else if (branch.getName().equals("JE")) {
                menuItems.add(new MenuItem("CAJUN FISH", 5.6, "burger"));
                menuItems.add(new MenuItem("COLE SLAW", 2.7, "side"));
                menuItems.add(new MenuItem("3PC set meal", 10.4, "set meal"));
                menuItems.add(new MenuItem("pepsi", 2.1, "drink"));
            } else if (branch.getName().equals("JP")) {
                menuItems.add(new MenuItem("CAJUN FISH", 5.6, "burger"));
                menuItems.add(new MenuItem("chicken nugget", 6.9, "side"));
            }
            // Add the menu items to the branchMenus map
            branchMenus.put(branch, menuItems);
        }
    }

    /**
     * Retrieves the list of menu items for a given branch.
     *
     * @param branch The branch for which to retrieve the menu items.
     * @return A list of menu items for the specified branch.
     */
    public List<MenuItem> getMenuItemsForBranch(Branch branch) {
        return branchMenus.get(branch);
    }

    /**
     * Retrieves the menu for a given branch as a formatted string.
     *
     * @param branch The branch for which to retrieve the menu.
     * @return A string representing the menu for the specified branch.
     */
    public String getMenu(Branch branch) {
        List<MenuItem> branchMenu = branch.getBranchItems();
        StringBuilder sb = new StringBuilder();
        if (branchMenu != null) {
            for (MenuItem item : branchMenu) {
                sb.append(item.getName()).append("\t").append(item.getPrice()).append("\t").append(item.getCategory()).append("\n");
            }
        } else {
            sb.append("Menu not available for branch: ").append(branch);
        }
        return sb.toString();
    }

    /**
     * Checks if the provided password matches the manager password.
     *
     * @param password The password to be verified.
     * @return True if the password matches the manager password, false otherwise.
     */
    private boolean isManager(String password) {
        return "password".equals(password);
    }
}
