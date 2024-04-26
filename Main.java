package fsmjdoc;


import java.util.List;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import fsmjdoc.Order.OrderStatus;


public class Main {


  public static void trackOrder(int orderId, Branch branch) { 
    Order trackOrder = branch.getOrder(orderId);
    trackOrder.checkForAutoCancellation();
      OrderStatus status = branch.getOrderStatus(orderId); //check for null!!
      switch (status) {
      case NEW :
        System.out.println("Order is being processed");
        break;
      case READY_TO_PICKUP :
        System.out.println("Order is ready to be picked up!");
        break;
      case COMPLETED :
        System.out.println("Order has been picked up!");
        break;
      case CANCELED :
        System.out.println("Order is canceled as it was not picked up in time!");
        break;
      default : //notexisting
        System.out.println("Order not found!");
      }
    }
  public static void collectFood(int orderId, Branch branch) {
    Order trackOrder = branch.getOrder(orderId);
    trackOrder.checkForAutoCancellation();
    OrderStatus status = branch.getOrderStatus(orderId);
      switch (status) {
          case NEW:
              System.out.println("Food is not ready for collection yet");
              break;
          case READY_TO_PICKUP:
              for (Order order : branch.getListOfOrders()) {
                  if (order.getOrderId() == orderId) {
                      order.setStatus(OrderStatus.COMPLETED);
                      System.out.println("Food collected. Order is now completed.");
                      return;
                  }
              }
              break;
          case COMPLETED:
              System.out.println("Food has already been collected");
              break;
          case CANCELED:
              System.out.println("Order has been canceled as it was not picked up in time");
              break;
          default:
              System.out.println("Order does not exist");
      }
      }
  public static void printReceipt(Order order, String payment) {
      Branch branch = order.getSelectedBranch();
      double orderCost = order.getOrderCost();
      StringBuilder receipt = new StringBuilder();
      String takeawayOrDineIn = order.isTakeaway() ? "Takeaway" : "Dine-in";

      // Add receipt header
      receipt.append("+------------------------------------------+\n");
      receipt.append("|               Order Receipt              |\n");
      receipt.append("+------------------------------------------+\n");
      receipt.append(String.format("| Order ID:        %-24d|\n", order.getOrderId()));
      receipt.append(String.format("| Branch:          %-24s|\n", branch.getName()));
      receipt.append(String.format("| Type:            %-24s|\n", takeawayOrDineIn));
      receipt.append("|                                          |\n");
      receipt.append("| Items:                                   |\n");

      // Add purchased items
      List<MenuItem> items = order.getItems();
      for (MenuItem item : items) {
          receipt.append(String.format("|   - %-37s|\n", item.getName() + " - $" + item.getPrice()));
      }

      // Add customization if any
      String customization = order.getCustomization();
      if (customization != null && !customization.isEmpty()) {
          receipt.append(String.format("| Customization:   %-24s|\n", customization));
      }

      // Add total cost and payment method
      receipt.append(String.format("|                                          |\n"));
      receipt.append(String.format("| Total cost:      $%-23.2f|\n", orderCost));
      receipt.append(String.format("| Payment method:  %-24s|\n", payment));

      receipt.append("+------------------------------------------+\n");

      // Print the receipt
      System.out.println(receipt.toString());
  }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Branch> existingBranches = new ArrayList<Branch>();
        existingBranches.add(new Branch("NTU"));
        existingBranches.add(new Branch("JE"));
        existingBranches.add(new Branch("JP"));
        Menu menu = new Menu(existingBranches);

        StaffList stafflist = new StaffList();
        ManagerList managerlist = new ManagerList();
        AdminList adminlist = new AdminList();
        adminlist.addStaff("Boss","boss", 'F', 'A', 62, "JE", existingBranches);

        managerlist.addStaff("Alica Ang","AlicaA", 'F', 'M', 27, "JE", existingBranches);
        managerlist.addStaff("Alexei","Alexei", 'M', 'M', 25, "NTU", existingBranches);
        managerlist.addStaff("Tom Chan ","TomC", 'M', 'M', 56, "JP", existingBranches);

        stafflist.addStaff("kumar Blackmore","kumarB", 'M', 'S', 32, "NTU", existingBranches);
        stafflist.addStaff("Alexei","Alexei", 'M', 'M', 25, "NTU", existingBranches);
        stafflist.addStaff("Tom Chan ","TomC", 'M', 'M', 56, "JP", existingBranches);
        stafflist.addStaff("Alica Ang","AlicaA", 'F', 'M', 27, "JE", existingBranches);
        stafflist.addStaff("Mary lee","MaryL", 'F', 'S', 44, "JE", existingBranches);
        stafflist.addStaff("Justin Loh","JustinL", 'M', 'S', 49, "JP", existingBranches);
        stafflist.addStaff("Boss","boss", 'F', 'A', 62, "JE", existingBranches);
        boolean exitRequested = false;
        while (!exitRequested) {
        System.out.println("Welcome to the Fastfood Ordering and Management System (FOMS)");

        // Choose user role
        System.out.println("Select your role:");
        System.out.println("1. Admin");
        System.out.println("2. Customer");
        System.out.println("3. Manager");
        System.out.println("4. Staff");
        int roleChoice = scanner.nextInt();
        switch (roleChoice) {
        case 1:
          System.out.println("Enter username");
            String admin_username=scanner.next();
            //*create method in stafflist to check if staff exist
            if(adminlist.getStaffByUsername(admin_username)!=null) {
              System.out.println("You are now logged in as a Staff.");
              //get the specific staff info from stafflist
              Admin cur = adminlist.getStaffByUsername(admin_username);
              System.out.println("Enter staff password:");
              String admin_password = scanner.next();
                // check password, use get password method in staff
              if(cur.getPassword().equals(admin_password)) {
                Branch CurrentBranch = cur.getStaffBranch();
                System.out.println("Successfully logged in as Admin");
          System.out.println("What would you like to do?:");
        System.out.println("1. Add a staff");
        System.out.println("2. Remove a staff");
        System.out.println("3. Edit details of a staff");
        System.out.println("4. Promote a staff");
        System.out.println("5. Assign Staff to new branch");
        System.out.println("6. Assign a manager to a branch");
        System.out.println("7. View filtered staff list");
        System.out.println("8. Add a new branch");
        System.out.println("9. Close a branch");
        System.out.println("10. Exit");
        int adminchoice = scanner.nextInt();
        switch (adminchoice) {
        case 1:
          cur.addStaff(stafflist, existingBranches);
          break;
        case 2:
          cur.removeStaff(stafflist);
          break;
        case 3:
          cur.editStaff(stafflist);
          break;
        case 4:
          cur.promoteStaff(existingBranches, stafflist, managerlist);
        break;

        case 5:
          cur.assignStaff(existingBranches, stafflist);
        break;

        case 6:
          cur.assignManager(existingBranches, stafflist);
          break;
        case 7:
          cur.viewFilteredStaff(stafflist);
          break;
        case 8:
          System.out.println("Enter name of new branch");
          scanner.nextLine();
          String branchname = scanner.nextLine();

          cur.openBranch(stafflist, branchname, existingBranches);
          break;
        case 9:
          System.out.println("Enter branch you want to remove");
          scanner.nextLine();
          String branchName = scanner.nextLine();
          cur.closeBranch(stafflist, branchName, existingBranches);
          break;
        case 10:
          break;
        default:
          System.out.println("Invalid choice.");
        }
             }
              else {
                    System.out.println("Password incorrect");
                }
              }
            else {
              System.out.println("Admin with this name does not exist");
            }
              break;

        case 2:
        while (roleChoice == 2) {
        try {
            System.out.println("What would you like to do?:");
            System.out.println("1. Place an order");
            System.out.println("2. Track an order");
            System.out.println("3. Collect an order");
            System.out.println("4. Exit");

            int actionchoice = scanner.nextInt();
            switch(actionchoice) {
                case 1:
                  Customer cust = new Customer();
                cust.selectBranch(existingBranches);
                    Branch selectedBranch = cust.getOrder().getSelectedBranch();
                    cust.browseMenu(selectedBranch);
                    System.out.println(); 

                    int actionChoice;
                    do {
                      System.out.println("What would you like to do?:");
                        System.out.println("1. Add an item to cart");
                        System.out.println("2. Remove an item from cart");
                        System.out.println("3. Add customization");
                        System.out.println("4. Check out");
                        System.out.println("5. Exit");
                        actionChoice = scanner.nextInt();

                        switch (actionChoice) {
                          case 1:
                              System.out.println("Which item from the menu would you like to add? (Enter index of the item)");
                                List<MenuItem> menuItems = selectedBranch.getBranchItems();
                                int itemIndex = scanner.nextInt();
                                if (itemIndex >= 1 && itemIndex <= menuItems.size()) {
                                  MenuItem selectedMenuItem = menuItems.get(itemIndex - 1);
                                  System.out.println( selectedMenuItem.getName() + " added to cart!");
                                  System.out.println();
                                    cust.addToCart(selectedMenuItem);
                                } 
                                else {
                                  System.out.println("Invalid item index.");
                                    }
                                break;
                             case 2:
                              int choice;
                                ArrayList<MenuItem> order = cust.getOrder().getItems();
                                if (cust.getOrder().getItems().isEmpty()) {
                                  System.out.println("Cart is empty! ");
                                  break;
                                  }
                                do {
                                  System.out.println("Which item would you like to remove?(Enter index of the item)");
                                    for (int i = 0; i < order.size(); i++) {
                                      System.out.println((i + 1) + ". " + order.get(i).getName());
                                        }
                                    choice = scanner.nextInt();
                                    if (choice < 1 || choice > order.size()) {
                                      System.out.println("Invalid choice. Please select a valid item.");
                                        }
                                    else {
                                      MenuItem selectedMenuItem = order.get(choice - 1);
                                        cust.getOrder().removeItem(selectedMenuItem);
                                        break;
                                        }
                                    } 
                                while (choice < 1 || choice > order.size());
                                break;

                                case 3:
                                    ArrayList<MenuItem> Order = cust.getOrder().getItems();
                                    if (Order.isEmpty()) {
                                        System.out.println("You have not added any items to cart!");
                                        break; // check
                                    }
                                    System.out.println("Enter your remarks: ");
                                    scanner.nextLine();
                                    String remarks = scanner.nextLine();
                                    cust.getOrder().setCustomization(remarks);
                                    break;
                                case 4:
                                    ArrayList<MenuItem> finalOrder = cust.getOrder().getItems();
                                    if (finalOrder.isEmpty()) {
                                        System.out.println("You have not added any items to cart!");
                                        break; // check
                                    }
                                    int paymentChoice;
                                    System.out.println("Do you want takeaway?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    System.out.print("Enter your choice (1 or 2): ");

                                    int takeaway = scanner.nextInt();
                                    scanner.nextLine(); 

                                    if (takeaway == 1) {
                                        cust.getOrder().setTakeaway(true);
                                    } 
                                    else if (takeaway == 2) {
                                      cust.getOrder().setTakeaway(false);
                                    } 
                                    else {
                                        System.out.println("Invalid choice. Assuming 'No' for takeaway.");
                                        cust.getOrder().setTakeaway(false);
                                    }
                                    // Prompt customer to choose a payment method
                                    int pchoice;
                                    do {
                                      System.out.println("Available Payment Methods:");
                                        System.out.println("1. Card Payment");
                                        System.out.println("2. Cash Payment");
                                        System.out.println("3. Online Payment");

                                        // Prompt customer to choose a payment method
                                        System.out.print("Choose a payment method: ");
                                        pchoice = scanner.nextInt();
                                        if (pchoice >= 1 && pchoice <= 3) {
                                          switch (pchoice) {
                                            case 1:
                                                Payment cardPayment = new CardPayment();
                                                cardPayment.processPayment(cust.getOrder());
                                                String card = cardPayment.getMethodName();
                                                printReceipt(cust.getOrder(), card);
                                                actionChoice = 5; // Exit the loop
                                                break;
                                            case 2:
                                                Payment cashPayment = new CashPayment();
                                                cashPayment.processPayment(cust.getOrder());
                                                String cash = cashPayment.getMethodName();
                                                printReceipt(cust.getOrder(), cash);
                                                actionChoice = 5; // Exit the loop
                                                break;
                                            case 3:
                                                Payment onlinePayment = new OnlinePayment();
                                                onlinePayment.processPayment(cust.getOrder());
                                                String online = onlinePayment.getMethodName();
                                                printReceipt(cust.getOrder(), online);
                                                actionChoice = 5; // Exit the loop
                                                break;
                                            default:
                                                System.out.println("Invalid payment method choice.");
                                                break;
                                        }
                                    } else {
                                        System.out.println("Invalid payment method choice.");
                                    } 
                                    }while (pchoice <= 1 && pchoice >= 3);
                                    break;
                                case 5:
                                    roleChoice = -1; // Exit the loop
                                    break;

                                default:
                                    System.out.println("Invalid choice.");
                            }
                        } while (actionChoice >= 1 && actionChoice <= 4);
                      break;
                case 2:
                  System.out.println("What is the orderId? ");
                    int id = scanner.nextInt();

                    System.out.println("Which branch was it purchased from? ");
                    for (int i = 0; i < existingBranches.size(); i++) {
                          System.out.println((i + 1) + ". " + existingBranches.get(i).getName());
                      }
                    int branchChoice = scanner.nextInt();
                      if (branchChoice >= 1 && branchChoice <= existingBranches.size()) {
                          Branch chosenBranch = existingBranches.get(branchChoice - 1);
                          trackOrder(id, chosenBranch);
                      }
                      else {
                        System.out.println("Order does not exist.");
                      }
                    break;
                case 3:
                  System.out.println("What is the orderId? ");
                    int idd = scanner.nextInt();
                    System.out.println("Which branch was it purchased from? ");
                    for (int i = 0; i < existingBranches.size(); i++) {
                          System.out.println((i + 1) + ". " + existingBranches.get(i).getName());
                      }
                    int branch = scanner.nextInt();
                      if (branch >= 1 && branch <= existingBranches.size()) {
                          Branch collectBranch = existingBranches.get(branch - 1);
                          collectFood(idd, collectBranch);

                      }
                      else {
                        System.out.println("Order does not exist.");
                      }
                    break;
                case 4:
                    roleChoice = -1; // Exit the loop and end the program
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }

            // If the customer chooses to exit, break out of the loop
            if (roleChoice == -1) {
                break;
            }
        } catch (InputMismatchException e) {
              System.out.println("Invalid input. Please enter a valid choice.");
              scanner.next(); // Clear the invalid input
          } catch (Exception e) {
              System.out.println("An error occurred: " + e.getMessage());
          }
        }
        break;

        case 3:
          System.out.println("Enter username");
            String manager_username=scanner.next();
            //*create method in stafflist to check if staff exist
            if(managerlist.getStaffByUsername(manager_username)!=null) {
              System.out.println("You are now logged in as a Staff.");
              //get the specific staff info from stafflist
              Manager current = managerlist.getStaffByUsername(manager_username);
              System.out.println("Enter staff password:");
              String staff_password = scanner.next();
                // check password, use get password method in staff
              if(current.getPassword().equals(staff_password)) {
                Branch CurrentBranch = current.getStaffBranch();
                System.out.println("Successfully logged in as Manager into " + CurrentBranch.getName());
                System.out.println("What action do you want to perform?");
                System.out.println("1. View Menu");
                    System.out.println("2. View All Current Orders");
                    System.out.println("3. View details of an individual Order");
                    System.out.println("4. Set order to ready");
                    System.out.println("5. Change your password");
                    System.out.println("6. Add item to menu");
                    System.out.println("7. Remove item from menu");
                    System.out.println("8. Update price");
                    System.out.println("9. View Stafflist");
                    int action_choice = scanner.nextInt();
                switch (action_choice) {
                case(1):
                  //View Menu of a staff's branch
                  //relies on how menu is structured
                  List<MenuItem> items = current.viewMenu();
                if (items.isEmpty()) {
                    System.out.println("No items available in the menu.");
                } else {
                    System.out.println("Menu Items:");
                    for (MenuItem item : items) {
                        System.out.println(item.getName() + ": $" + item.getPrice());
                    }
                }
                break;
                case (2):
                  //View all orders
                List<Order> orders = current.getStaffBranch().getListOfOrders();
                Scanner Scanner = new Scanner(System.in);

                // Filter orders with NEW status
                List<Order> newOrders = new ArrayList<Order>();
                for (Order order : orders) {
                    if (order.getStatus() == Order.OrderStatus.NEW) {
                        newOrders.add(order);
                    }
                }

                if (newOrders.isEmpty()) {
                    System.out.println("No new orders.");
                }
                break;

                case(3):
                  //View Individual Orders
                  //Display exisiting orders so staff can decide which order to choose
                  current.viewIndOrders();
                break;

                case(4):
                  //set order to ready
                  //view all order
                current.statusToReady();
                break;

                case(5):
                  //set password
                  System.out.println("Enter New Password");
                  scanner.nextLine(); 
                  String new_password = scanner.nextLine();
                  current.setPassword(new_password);

                  //Current.setpassword(new_password);
                  break;
                case (6):
                current.addMenuItem();
                break;
                case (7):
                current.removeMenuItem();
                break;
                case (8):
                current.updateprice();
                break;
                case (9):
                stafflist.printAllStaff();
                break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                }
              }
            else {
                  System.out.println("Password incorrect");
              }
            }
            else {
              System.out.println("Manager with this username does not exist");
            }
            break;
        case 4:
          // Staff
            // Prompt for staff password
              //Enter Staff log in id
              System.out.println("Enter username");
              String staff_username=scanner.next();
              //*create method in stafflist to check if staff exist
              if(stafflist.getStaffByUsername(staff_username)!=null) {
                System.out.println("You are now logged in as a Staff.");
                //get the specific staff info from stafflist
                Staff Current=stafflist.getStaffByUsername(staff_username);
                System.out.println("Enter staff password:");
                String staff_password = scanner.next();
                  // check password, use get password method in staff
                if(Current.getPassword().equals(staff_password)) {
                  Branch CurrentBranch = Current.getStaffBranch();
                  System.out.println("Successfully logged in as Staff into " + CurrentBranch.getName());
                  System.out.println("What action do you want to perform?");
                  System.out.println("1. View Menu");
                      System.out.println("2. View All Current Orders");
                      System.out.println("3. View details of an individual Order");
                      System.out.println("4. Set order to ready");
                      System.out.println("5. Change your password");
                      int action_choice = scanner.nextInt();
                  switch (action_choice) {
                  case(1):
                    //View Menu of a staff's branch
                    //relies on how menu is structured
                    List<MenuItem> items = Current.viewMenu();
                  if (items.isEmpty()) {
                      System.out.println("No items available in the menu.");
                  } else {
                      System.out.println("Menu Items:");
                      for (MenuItem item : items) {
                          System.out.println(item.getName() + ": $" + item.getPrice());
                      }
                  }
                  break;
                  case (2):
                    //View all orders
                  List<Order> orders = Current.getStaffBranch().getListOfOrders();
                  Scanner Scanner = new Scanner(System.in);

                  // Filter orders with NEW status
                  List<Order> newOrders = new ArrayList<Order>();
                  for (Order order : orders) {
                      if (order.getStatus() == Order.OrderStatus.NEW) {
                          newOrders.add(order);
                      }
                  }

                  if (newOrders.isEmpty()) {
                      System.out.println("No new orders.");
                  }
                  else {
                      System.out.println("IDs of current orders:");
                      for (Order order : newOrders) {
                          System.out.println(order.getOrderId());
                      }
                  }
                  break;

                  case(3):
                    //View Individual Orders
                    //Display exisiting orders so staff can decide which order to choose
                    Current.viewIndOrders();
                  break;

                  case(4):
                    //set order to ready
                    //view all order
                  Current.statusToReady();
                  break;

                  case(5):
                    //set password
                    System.out.println("Enter New Password");
                    scanner.nextLine(); 
                    String new_password = scanner.nextLine();
                    Current.setPassword(new_password);

                    //Current.setpassword(new_password);
                    break;
                  default:
                      System.out.println("Invalid choice. Please select a valid option.");
                  }
                }
              else {
                    System.out.println("Password incorrect");
                }
              }
              else {
                System.out.println("Staff with this username does not exist");
              }
              break;

          default:
           System.out.println("Invalid choice. Please select a valid option.");
    }
    }
        System.out.println("Do you want to exit? (yes/no)");
        String exitChoice = scanner.next();
        if (exitChoice.equalsIgnoreCase("yes")) {
            exitRequested = true;
        }
        else if(exitChoice.equalsIgnoreCase("no")) {
          exitRequested = false;
        }
        scanner.close();
    }
}
