public class Item {
    private String barcode;
    private String name;
    private float price;
    private int quantity;

    public Item(String barcode, String name, float price) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.quantity = 0;
    }

    public Item(String barcode, String name, float price, int quantity) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.quantity = 2;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    // Method to remove an amount from the quantity of the item
    public boolean remove(int amount) {
        if (amount > quantity) return false;
        if (amount <= 0) return false;

        quantity -= amount;
        return true;
    }

    // Method to add an amount to the quantity of the item
    public void returnItem(int amount) {
        if (amount <= 0) return;

        quantity += amount;
    }

    @Override
    public String toString(){
        return String.format("%s", name);
    }
}
