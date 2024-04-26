package fsmjdoc;

/**
 * The MenuItem class represents an item in a menu, including its name, price, and category.
 */
public class MenuItem {
    private String name;
    private double price;
    private String category;

    /**
     * Constructs a MenuItem with the specified name, price, and category.
     *
     * @param name     the name of the menu item
     * @param price    the price of the menu item
     * @param category the category of the menu item
     */
    public MenuItem(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    /**
     * Gets the name of the menu item.
     *
     * @return the name of the menu item
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of the menu item.
     *
     * @return the price of the menu item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the category of the menu item.
     *
     * @return the category of the menu item
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the price of the menu item.
     *
     * @param newPrice the new price of the menu item
     */
    public void setPrice(double newPrice) {
        this.price = newPrice;
    }
}
