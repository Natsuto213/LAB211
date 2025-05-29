package models;

import java.util.Date;

public class SetMenu implements Comparable<SetMenu> {

    private String menuId;
    private String name;
    private double price;
    private String ingredients;

    public SetMenu() {
    }

    public SetMenu(String menuId, String name, double price, String ingredients) {
        this.menuId = menuId;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return String.format("%-11s:%s\n"
                + "%-11s:%s\n"
                + "%-11s:%,d Vnd\n"
                + "Ingredients:\n%s"
                + "\n------------------------------",
                "Code", this.getMenuId(), "Name", this.getName(), "Price", (int) this.getPrice(), ingredients = ingredients.replace("#", "\n"));
    }

    public void display(Date date, int quantity) {
        System.out.format("%-16s: %s\n", "Code of Set Menu", this.getMenuId());
        System.out.format("%-16s: %s\n", "Set menu name", this.getName());
        System.out.format("%-16s: %s\n", "Event date", date);
        System.out.format("%-16s: %s\n", "Number of tables", quantity);
        System.out.format("%-16s: %,d Vnd\n", "Price", (int) this.getPrice());
        System.out.format("Ingredients:\n%s\n", ingredients = ingredients.replace("#", "\n"));
    }

    @Override
    public int compareTo(SetMenu that) {
        if (this.price < that.price) {
            return -1;
        } else if (this.price > that.price) {
            return 1;
        } else {
            return 0;
        }
    }

}
