package com.lexkom.controll;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.lexkom.SheetsGoogle;
import com.lexkom.model.Items;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;

public class ItemsOverviewController {

    @FXML
    private TableView<Items> ItemsTable;
    @FXML
    private AnchorPane storePane;
    @FXML
    private AnchorPane tablePane;
    @FXML
    private AnchorPane descriptionPane;
    @FXML
    private Pane fotoPane;
    @FXML
    private TableColumn<Items, String> articleColumn;
    @FXML
    private TableColumn<Items, String> nameColumn;
    @FXML
    private TableColumn<Items, String> quantityColumn;
    @FXML
    private TableColumn<Items, String> descriptColumn;
    @FXML
    private TableColumn<Items, String> usdColumn;
    @FXML
    private TableColumn<Items, String> uahColumn;
    @FXML
    private TableColumn<Items, String> priceColumn;

    @FXML
    private Label priceLabel;
    @FXML
    private Label quantityLabel;
    @FXML
    private Label availability;
    @FXML
    private Label income;
    @FXML
    private Label efficiency;
    @FXML
    private ImageView imageView;

    private Image image = new Image("com/lexkom/resources/noimage.jpg");

    private final ObservableList<Items> itemsData = FXCollections.observableArrayList();

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ItemsOverviewController() {
    }

    public ObservableList<Items> getItemsData() {
        return itemsData;
    }

    @FXML
    private void handleEditItem() {
        Items selectedItem = ItemsTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            boolean okClicked = showItemEditDialog(selectedItem);
            if (okClicked) {
                showItemsDetails(selectedItem);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(storePane.getScene().getWindow());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Item Selected");
            alert.setContentText("Please select an item in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void saleItem() {
        Items selectedItem = ItemsTable.getSelectionModel().getSelectedItem();
        int index = ItemsTable.getSelectionModel().getSelectedIndex(); // shows index in table
        if (selectedItem != null) {
            boolean okClicked = showSaleDialog(selectedItem);
            if (okClicked) {
                showItemsDetails(selectedItem);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(storePane.getScene().getWindow());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Item Selected");
            alert.setContentText("Please select an item in the table.");

            alert.showAndWait();
        }

    }

    public void setSheetsGoogle() {
        Sheets service1 = null;
        try {
            service1 = SheetsGoogle.getSheetsService();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Prints from article to availability:

        String spreadsheetId = "1dnR2QpvScAP6mSOAMsOM7OmPQwl955NMoxZS0LCUn2w";
        String range = "STOCK!A3:T";  // "STOCK" - all range
        ValueRange response = null;
        try {
            response = service1.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<List<Object>> values = response.getValues();

        for (List row : values) {

            String article = String.valueOf(row.get(0));
            String Name = String.valueOf(row.get(1));
            String quantity = String.valueOf(row.get(3));
            String description = String.valueOf(row.get(4));
            String costUSD = String.valueOf(row.get(5));
            String costUAH = String.valueOf(row.get(6));
            String price = String.valueOf(row.get(10));
            String availability = String.valueOf(row.get(19));
            String income = String.valueOf(row.get(12));
            String efficiency = String.valueOf(row.get(11));
            String imageSource = String.valueOf(row.get(17));

            itemsData.add(new Items(article, Name , quantity, description, costUSD,
                    costUAH, price, income, efficiency, availability, imageSource));
        }
    }


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        System.out.println("initialize");
        // Initialize the items table with the 4 columns.
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        usdColumn.setCellValueFactory(cellData -> cellData.getValue().costUSDProperty());
        uahColumn.setCellValueFactory(cellData -> cellData.getValue().costUAHProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        setSheetsGoogle();
        ItemsTable.setItems(itemsData);


        // Clear person details.
        showItemsDetails(null);

        // Listen for selection changes and show the item details when changed.
        ItemsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showItemsDetails(newValue));
    }

    private void showItemsDetails(Items item) {
        if (item != null) {
            // Fill the labels with info from the item object.
            availability.setText(item.getAvailability());
            priceLabel.setText(item.getPrice());
            quantityLabel.setText(item.getQuantity());
            efficiency.setText(item.getEfficiency());
            income.setText(item.getIncome());
            if (!item.getImageSource().isEmpty()){
                imageView.setImage(new Image(item.getImageSource()));
            } else imageView.setImage(image);
            imageView.fitWidthProperty().bind(fotoPane.widthProperty());
            imageView.fitHeightProperty().bind(fotoPane.heightProperty());

        } else {
            // Item is null, remove all the text.
            availability.setText("");
            priceLabel.setText("");
            quantityLabel.setText("");
            efficiency.setText("");
            income.setText("");
        }
    }

    private boolean showItemEditDialog(Items item) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ItemsOverviewController.class.getResource("../view/ItemEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Item");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(storePane.getScene().getWindow());
            Scene scene = new Scene(page);
            dialogStage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            dialogStage.setScene(scene);

            // Set the items into the controller.
            ItemEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setItem(item);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean showSaleDialog(Items item) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ItemSaleDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Sale Item");

//            dialogStage.initOwner(storePane.getScene().getWindow());
            Scene scene = new Scene(page);

            dialogStage.setScene(scene);
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);

            // Set the items into the controller.
            ItemSaleController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setItem(item);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
