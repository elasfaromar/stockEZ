import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SellingSheet {
    private InventorySystem model;
    private List<Item> items;
    private List<Integer> quantities;
    private List<Float> prices;
    private int totalQuantity;
    private float totalPrice;
    private Date saveDate;

    public SellingSheet(InventorySystem m) {
        model = m;
        items = new LinkedList<>();
        quantities = new LinkedList<>();
        prices = new LinkedList<>();
    }

    @Override
    public String toString(){
        return String.format("%s %02d/%02d/%s", saveDate.toString().substring(11,19), saveDate.getDate(), saveDate.getMonth()+1, saveDate.toString().substring(24,28));
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public List<Float> getPrices() {
        return prices;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    public void sellItem(Item i, int quantity){
        float price = quantity * i.getPrice();
        totalQuantity += quantity;
        totalPrice += price;

        items.add(0, i);
        quantities.add(0, quantity);
        prices.add(0, price);
    }

    public void returnItem(Item i, int index){
        int quantity = quantities.get(index);
        totalQuantity -= quantity;
        totalPrice -= quantity*i.getPrice();

        model.returnItem(i, quantity);
        items.remove(i);
        quantities.remove(index);
        prices.remove(index);
    }
}
