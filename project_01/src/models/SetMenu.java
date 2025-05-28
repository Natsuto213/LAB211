package models;

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
        return String.format(
                "Code       :%s\n"
                + "Name       :%s\n"
                + "Price      :%,d Vnd\n"
                + "Ingredients:\n%s"
                + "\n--------------------------------------",
                menuId, name, (int) price, ingredients = ingredients.replace("#", "\n"));
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
