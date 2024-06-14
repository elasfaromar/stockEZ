import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.text.Font;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class InventorySystemController extends Application {
    private InventorySystem model;

    public InventorySystemController() {
        model = new InventorySystem();
        model.getUsers().put((short)1, new User());
    }

    public void start(Stage primaryStage) {
        Pane aPane = new Pane();

        // Create the view
        InventorySystemView view = new InventorySystemView();

        aPane.getChildren().add(view);

        primaryStage.setTitle("Inventory System");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane, 1920, 1080));
        primaryStage.show();
        view.update(model,0);
        primaryStage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource(String.format("%sstyle.css", File.separatorChar))).toExternalForm());

        /*
          Actions that are done when confirm button is pressed:
          1. Add item to Selling Sheet (if possible)
          2. Reset multiplier
          3. Select Text Input after confirming
          4. Update Error Text as needed
        */
        view.getConfirmButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                TextField itemInput = view.getItemInput();
                TextField multiplierInput = view.getMultiplierInput();
                Label errorLabel = view.getErrorLabel();

                String barcode = itemInput.getText();
                int result = model.sellItem(barcode, Integer.parseInt(multiplierInput.getText()));

                if (result == 1) {
                    view.update(model, 0);
                    view.getChildren().remove(errorLabel);
                }
                else {
                    if (result == -1) {
                        errorLabel.setText("Stock is not available");
                    } else if (result == 0) {
                        errorLabel.setText("Item not found");
                    }
                    view.getChildren().remove(errorLabel);
                    view.getChildren().add(errorLabel);
                }

                multiplierInput.setText("1");

                itemInput.requestFocus();
                itemInput.selectAll();
            }
        });

        /*
          Actions that are done when remove button is pressed:
          1. Confirm action
          2. Perform correct action based on confirmation
          3. Remove item and update system and item profiles
        */
        view.getSellingSheet().getRemoveItemButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (confirmation().get()) {
                    System.out.println("User clicked Confirm");

                    SellingSheet currSheet = model.getCurrentSellingSheet();

                    int i = view.getSellingSheet().getItemList().getSelectionModel().getSelectedIndex();
                    Item item = currSheet.getItems().get(i);

                    model.getCurrentSellingSheet().returnItem(item, i);
                    view.update(model,0);
                }
            }
        });

        /*
          Actions that are done when delete sheet button is pressed:
          1. Confirm action
          2. Remove all items of selling sheet and update system and item profiles
          3. Update disability of delete sheet button
        */
        view.getSellingSheet().getDeleteSheetButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList<Item> list = view.getSellingSheet().getItemList().getItems();
                if (confirmation().get()) {
                    for (int i=list.size()-1; i>=0; i--) {
                        model.getCurrentSellingSheet().returnItem(list.get(i), i);
                    }

                    view.update(model,-1);
                }
            }
        });

        /*
          Actions that are done when delete sheet button is pressed:
          1. Confirm action
          2. Add current selling sheet to past selling sheet
          3. Create new selling sheet
        */
        view.getSellingSheet().getSaveSheetButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (confirmation().get()) {
                    model.addSellingSheet();
                    view.update(model, -1);
                }
            }
        });

        view.getCatalogButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        /* List Selection Methods */
        view.getSellingSheet().getItemList().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.update(model, view.getSellingSheet().getItemList().getSelectionModel().getSelectedIndex());
            }
        });
        view.getSellingSheet().getQuantityList().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.update(model, view.getSellingSheet().getQuantityList().getSelectionModel().getSelectedIndex());
            }
        });
        view.getSellingSheet().getPriceList().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.update(model, view.getSellingSheet().getPriceList().getSelectionModel().getSelectedIndex());
            }
        });
    }

    private AtomicBoolean confirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirm Action");
        AtomicBoolean bol = new AtomicBoolean(false);

        // Add buttons to the alert dialog
        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                bol.set(true);
            }
        });
        return bol;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
