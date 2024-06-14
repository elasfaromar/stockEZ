import java.util.*;

public class InventorySystem {
    private Set<Item> items;
    private Map<Short, User> users;
    private User user;
    private List<SellingSheet> pastSellingSheets;
    private SellingSheet currentSellingSheet;

    public InventorySystem() {
        items = new HashSet<>();
        items.add(new Item("101", "Omar Samsung", 17f, 2));
        items.add(new Item("102", "Omar iPhone", 17.54f, 2));
        users = new HashMap<>();
        pastSellingSheets = new LinkedList<>();
        currentSellingSheet = new SellingSheet(this);
        user = null;
    }

    public Map<Short, User> getUsers() {
        return users;
    }

    public List<SellingSheet> getPastSellingSheets() {
        return pastSellingSheets;
    }

    public SellingSheet getCurrentSellingSheet() {
        return currentSellingSheet;
    }

    public boolean logIn(short pin) {
        if (users.containsKey(pin)) {
            user = users.get(pin);
            return true;
        }
        return false;
    }

    public Item getItemByBarcode(String barcode) {
        for (Item i:items){
            if (i.getBarcode().equals(barcode))
                return i;
        }
        return null;
    }

    public int sellItem(String barcode, int quantity) {
        Item i = getItemByBarcode(barcode);

        if (i == null || !items.contains(i))
            return 0; // represents no item found

        if (i.remove(quantity)){
            currentSellingSheet.sellItem(i,quantity);
            return 1;
        }
        else
            return -1; // represents stock is not enough to complete sail
    }

    public void addSellingSheet() {
        pastSellingSheets.addFirst(currentSellingSheet);
        currentSellingSheet.setSaveDate(new Date());
        currentSellingSheet = new SellingSheet(this);
    }

    public void returnItem(Item i, int quantity) {
        i.returnItem(quantity);
    }
}
