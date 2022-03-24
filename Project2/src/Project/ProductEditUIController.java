/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Data.CategoryDAO;
import Project.Data.CategoryDAOImpl;
import Project.Data.Product;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author DELL
 */
public class ProductEditUIController {

    private CategoryDAO ca = new CategoryDAOImpl();
    private Product editProduct = null;

    @FXML
    private JFXButton btnImages;

    //@FXML
    //private TextField txtImages;

    @FXML
    private ImageView imgView;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private Label txtMsg;

    @FXML
    private JFXComboBox<String> txtCB;

    @FXML
    private JFXTextField txtProperties;

    @FXML
    private JFXButton btnIndex;

    @FXML
    private JFXButton btnSubmit;

    @FXML
    private JFXButton btnReset;
    
        @FXML
    private Text txtImg;

    ObservableList<String> list = ca.selectName();

        String files = "";
    @FXML
    void btnImagesClick(ActionEvent event) throws URISyntaxException, MalformedURLException {
        
//        if (txtImg != null) {
//            txtImg.setText("");
//        }
        FileChooser fc = new FileChooser();

        fc.setTitle("My File");
        fc.getExtensionFilters().addAll(new ExtensionFilter("Img File", "*.png", "*.jpeg", "*.jpg"));
        File file = fc.showOpenDialog(null);
        if (file != null) {
             files = "/img/" + file.getName();
            txtImg.setText("/img/" + file.getName());
            imgView.setImage(new Image(  file.toURI().toString()));

        } else {
            txtMsg.setText("Img Null");
        }
    }

    @FXML
    void btnIndexClick(ActionEvent event) throws IOException {
        Nagatice.getInstance().goToIndexProduct();
    }

    @FXML
    void btnResetClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you want to reset ?");
        alert.setTitle("Resetting");
        Optional<ButtonType> confirmationResponse
                = alert.showAndWait();
        if (confirmationResponse.get() == ButtonType.OK) {
            resetTextFields();
        }
    }

    @FXML
    void btnSubmitClick(ActionEvent event) {
        if (validation()) {
            try {
                if (editProduct == null) {
                    Product insertProduct = extractProductFromFields();
                    insertProduct = Product.insert(insertProduct);
                    String msg = "New Module Manager inserted with id : " + insertProduct.getID();
                    txtMsg.setText(msg);
                } else {
                    Product updateProduct = extractProductFromFields();
                    updateProduct.setId(this.editProduct.getID());
                    boolean result = Product.update(updateProduct);
                    if (result) {
                        txtMsg.setText("Update Successful");
                    } else {
                        txtMsg.setText("Update Unsuccessful");
                    }
                }
            } catch (Exception e) {
                txtMsg.setText(e.getMessage());

            }
        }
    }

    @FXML
    void btnCB(ActionEvent event) {

    }

    void resetTextFields() {
        txtName.setText("");
        txtPrice.setText("");
        txtProperties.setText("");
//        txtImages.setText("");
        imgView.setImage(null);
        txtCB.setValue("Drink");
    }

    public void initialize(Product editProduct) {
        txtCB.setItems(list);
        txtCB.setValue("Drink");
        this.editProduct = editProduct;
        String msg = "";
        if (this.editProduct == null) {
            msg = "Create a new Product";
        } else {
            msg = "Update an exitsting Product";
//            if (!editProduct.getImg().contains("/img/")){
//                txtImages.setText("/img/" + new File(editProduct.getImg()).getName());
//            } else {
//                txtImages.setText(editProduct.getImg());
//            }

            txtName.setText(editProduct.getName());
            txtPrice.setText(Integer.toString(editProduct.getPrice()));
            txtProperties.setText(editProduct.getProperties());
            txtImg.setText(editProduct.getImg());
            txtCB.setValue(editProduct.getLevel());
            imgView.setImage(new Image(editProduct.getImg()));

        }
        txtMsg.setText(msg);
    }

    private Product extractProductFromFields() {
        Product product = new Product();
        product.setName(txtName.getText());
        product.setPrice(Integer.parseInt(txtPrice.getText()));
        product.setProperties(txtProperties.getText());
        //if(file != null) { }
        product.setImg(files); 
        product.setLevel(txtCB.getValue());
        return product;
    }

    private boolean validation() {
        String msg = "";
        int filter = 0;
        if (txtName.getText().isEmpty()) {
            msg = "Name is not empty !";
            txtMsg.setText(msg);
            return false;
        }
        if (txtPrice.getText().isEmpty()) {
            msg = "Price is not empty ";
            txtMsg.setText(msg);
            return false;
        } else {
            try {
                filter = Integer.parseInt(txtPrice.getText());
            } catch (NumberFormatException e) {
                msg = "Price is not character !";
                txtMsg.setText(msg);
                return false;
            }     
        }
        if(filter <= 0){
             msg = "Price must be > 0 ";
            txtMsg.setText(msg);
            return false;
        }

        if (txtProperties.getText().isEmpty()) {
            msg = "Properties is not empty";
            txtMsg.setText(msg);
            return false;
        }
        if (txtImg.getText().isEmpty()) {
            msg = "Images is not empty";
            txtMsg.setText(msg);
            return false;
        }

        return true;
    }
}
