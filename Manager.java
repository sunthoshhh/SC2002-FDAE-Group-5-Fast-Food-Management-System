package fsmjdoc;



import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;

public class Manager extends Staff{
  public Manager(String Name, String userName, char gender, char designation, int age, String branchName, List<Branch> existingBranches) {
    super(Name, userName, gender, designation, age, branchName, existingBranches);
  }

    public void addMenuItem() {
      Scanner scanner = new Scanner(System.in);
        System.out.println("Enter item name:");
        String name = scanner.nextLine();

        double price = 0.0;
        try {
          System.out.println("Enter item price:");
          price = scanner.nextDouble();
          scanner.nextLine(); // Consume newline character
        } catch (InputMismatchException e) {
          System.out.println("Invalid input. Please enter a numerical price.");
          scanner.nextLine(); // Clear the invalid input
          return; // Exit the method if price is invalid
        }

        System.out.println("Enter item category:");
        String category = scanner.nextLine();

        addItem(this.getStaffBranch(), name, price, category);
      }
    public void addItem(Branch branch, String name, double price, String category) {
            List<MenuItem> branchMenu = branch.getBranchItems();

            // Check if an item with the same name already exists in the branch's menu
            boolean itemExists = false;
            for (MenuItem item : branchMenu) {
                if (item.getName().equalsIgnoreCase(name)) {
                    itemExists = true;
                    break;
                }
            }

            if (itemExists) {
                System.out.println("An item with the same name already exists in the menu. Please choose a different name.");
            } else {
                // If no item with the same name exists, add the new item to the menu
                branchMenu.add(new MenuItem(name, price, category));
                System.out.println("Item added successfully.");
            }

    }

    public void updateprice() {
      Scanner scanner = new Scanner(System.in);
        System.out.println("Enter item name:");
        String name = scanner.nextLine();

        double newPrice = 0.0;
        try {
          System.out.println("Enter new item price:");
          newPrice = scanner.nextDouble();
          scanner.nextLine(); // Consume newline character
        } catch (InputMismatchException e) {
          System.out.println("Invalid input. Please enter a numerical price.");
          scanner.nextLine(); // Clear the invalid input
          return; // Exit the method if price is invalid
        }


        updatePrice(staffBranch, name, newPrice);
      }

    public void updatePrice(Branch branch, String name, double newPrice) {
            List<MenuItem> branchMenu = branch.getBranchItems();
            if (branchMenu != null) {
                for (MenuItem item : branchMenu) {
                    if (item.getName().equals(name)) {
                        item.setPrice(newPrice);
                        System.out.println("Price updated successfully.");
                        return;
                    }
                }
                System.out.println("Item not found in menu.");
            } else {
                System.out.println("Branch not found.");
            }

    }

    public void removeMenuItem() {
      Scanner scanner = new Scanner(System.in);
        System.out.println("Enter item name:");
        String name = scanner.nextLine();

        removeItem(staffBranch, name);
      }
    public void removeItem(Branch branch, String name) {

            List<MenuItem> branchMenu = branch.getBranchItems();

            if (branchMenu != null) {
                boolean removed = false;
                // Iterate through the branch's menu to find the item with the specified name
                Iterator<MenuItem> iterator = branchMenu.iterator();
                while (iterator.hasNext()) {
                    MenuItem item = iterator.next();
                    if (item.getName().equals(name)) {
                        iterator.remove(); // Remove the item from the menu
                        removed = true;
                        break;
                    }
                }

                if (removed) {
                    System.out.println("Item removed successfully.");
                } else {
                    System.out.println("Item not found in menu.");
                }
            } else {
                System.out.println("Branch not found.");
            }

    }

}
