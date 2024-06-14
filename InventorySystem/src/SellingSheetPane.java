import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class SellingSheetPane extends Pane {
    private ListView<Item> itemList;
    private ListView<Integer> quantityList;
    private ListView<Float> priceList;
    private Label priceLabel, quantityLabel;
    private Button saveSheetButton, deleteSheetButton, removeItemButton;

    public SellingSheetPane() {
        Pane innerPane = new Pane();
        itemList = new ListView<>();
        itemList.relocate(0,0);
        itemList.setPrefSize(1334,668);

        quantityList = new ListView<>();
        quantityList.relocate(1356,0);
        quantityList.setPrefSize(163,668);

        priceList = new ListView<>();
        priceList.relocate(1541,0);
        priceList.setPrefSize(163,668);

        quantityLabel = new Label("Quantity");
        quantityLabel.relocate(1356,-19);
        quantityLabel.setPrefSize(163,15);

        priceLabel = new Label("Price");
        priceLabel.relocate(1541,-19);
        priceLabel.setPrefSize(163,15);

        saveSheetButton = new Button("Save Sheet");
        saveSheetButton.relocate(0, 707);
        saveSheetButton.setPrefSize(200,48);
        saveSheetButton.setId("orig");

        deleteSheetButton = new Button("Delete Sheet");
        deleteSheetButton.relocate(231,707);
        deleteSheetButton.setPrefSize(200,48);
        deleteSheetButton.setId("orig");

        removeItemButton = new Button("Remove Item");
        removeItemButton.relocate(461,707);
        removeItemButton.setPrefSize(200,48);
        removeItemButton.setId("orig");

        innerPane.getChildren().addAll(itemList, quantityList, priceList, priceLabel, quantityLabel, saveSheetButton, deleteSheetButton, removeItemButton);

        getChildren().addAll(innerPane);
    }

    public ListView<Item> getItemList() {
        return itemList;
    }

    public ListView<Integer> getQuantityList() {
        return quantityList;
    }

    public ListView<Float> getPriceList() {
        return priceList;
    }

    public Button getRemoveItemButton() {
        return removeItemButton;
    }

    public Button getSaveSheetButton() {
        return saveSheetButton;
    }

    public Button getDeleteSheetButton() {
        return deleteSheetButton;
    }

    public void update(InventorySystem model, int index){
        itemList.setItems(FXCollections.observableList(model.getCurrentSellingSheet().getItems()));
        itemList.getSelectionModel().select(index);
        quantityList.setItems(FXCollections.observableList(model.getCurrentSellingSheet().getQuantities()));
        quantityList.getSelectionModel().select(index);
        priceList.setItems(FXCollections.observableList(model.getCurrentSellingSheet().getPrices()));
        priceList.getSelectionModel().select(index);

        removeItemButton.setDisable(itemList.getSelectionModel().getSelectedIndex() == -1);
        deleteSheetButton.setDisable(itemList.getItems().isEmpty());
        saveSheetButton.setDisable(itemList.getItems().isEmpty());

        priceLabel.setText(String.format("Price ($%.2f)", model.getCurrentSellingSheet().getTotalPrice()));
        quantityLabel.setText(String.format("Quantity (%d)", model.getCurrentSellingSheet().getTotalQuantity()));
    }
}
