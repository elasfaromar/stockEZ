import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class InventorySystemView extends Pane {
    private TextField itemInput, multiplierInput;
    private SellingSheetPane sellingSheet;
    private Button confirmButton, modeButton, catalogButton;
    private Label errorLabel;

    public InventorySystemView(){
        Image image = new Image("stockEZ.png");
        ImageView imageView = new ImageView(image);
        imageView.relocate(65,10);
        imageView.setFitHeight(136);
        imageView.setFitWidth(273);

        itemInput = new TextField();
        itemInput.relocate(108,148);
        itemInput.setPrefSize(1334,40);

        multiplierInput = new TextField();
        multiplierInput.relocate(1464,148);
        multiplierInput.setPrefSize(163,40);
        multiplierInput.setText("1");

        confirmButton = new Button("Sell Item");
        confirmButton.relocate(1649,148);
        confirmButton.setPrefSize(163,40);
        confirmButton.setId("orig");

        sellingSheet = new SellingSheetPane();
        sellingSheet.relocate(108,222);

        Label scannerLabel = new Label("Scan Barcode");
        scannerLabel.relocate(108,129);
        scannerLabel.setPrefSize(1334,15);

        Label multiplierLabel = new Label("Multiplier");
        multiplierLabel.relocate(1464,129);
        multiplierLabel.setPrefSize(163,15);

        errorLabel = new Label();
        errorLabel.relocate(108,189);
        errorLabel.setPrefSize(163,15);
        errorLabel.setId("error");

        modeButton = new Button("Mode");
        modeButton.relocate(1464,924);
        modeButton.setPrefSize(163,48);
        modeButton.setId("alt");

        catalogButton = new Button("Catalog");
        catalogButton.relocate(1649,924);
        catalogButton.setPrefSize(163,48);
        catalogButton.setId("alt");

        getChildren().addAll(imageView, itemInput, multiplierInput, confirmButton, sellingSheet, scannerLabel, multiplierLabel, modeButton, catalogButton);
        setPrefSize(1920,1080);
    }

    public Button getConfirmButton() {
        return confirmButton;
    }

    public SellingSheetPane getSellingSheet() {
        return sellingSheet;
    }

    public TextField getItemInput() {
        return itemInput;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public TextField getMultiplierInput() {
        return multiplierInput;
    }

    public Button getModeButton() {
        return modeButton;
    }

    public Button getCatalogButton() {
        return catalogButton;
    }

    public void update(InventorySystem model, int i) {
        sellingSheet.update(model, i);
        getChildren().remove(errorLabel);
    }
}
