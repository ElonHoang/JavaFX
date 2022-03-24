package Project;

import Project.Data.DateDAO;
import Project.Data.DateDAOImpl;
import Project.Data.DateProduct;
import Project.Data.Dates;
import Project.Data.Order;
import Project.Data.OrderDetail;
import Project.Data.Product;
import Project.Data.ProjectSignUp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DELL
 */
public class DateUIController {

    DateDAO dd = new DateDAOImpl();
    Product p;
    OrderDetail d;
    Order ord;
    ProjectSignUp s;
    @FXML
    private TableView<Dates> tvDate;

    @FXML
    private TableColumn<Dates, Integer> tcID;

    @FXML
    private TableColumn<Dates, Date> tcDate;

    @FXML
    private TableView<DateProduct> tvSum;

    @FXML
    private TableColumn<DateProduct, String> tcName;

    @FXML
    private TableColumn<DateProduct, Integer> tcQuantity;

    @FXML
    private TableColumn<DateProduct, Float> tcSum;
    
    @FXML
    private Label labelTotal;

    public void initialize(ProjectSignUp s) throws ParseException {
        this.s = s;
        //if ( s != null) {
            tvDate.setItems(dd.selectAll());
            tcID.setCellValueFactory((p) -> {
                return p.getValue().getIDProperty();
            });
               
            tcDate.setCellValueFactory((p) -> {
                return p.getValue().getDateProperty();
            });
          
            tvDate.getSelectionModel().selectedItemProperty().addListener((obs, oldS, newS) -> {
                if(newS != null){
                    java.sql.Date sqlDate = new java.sql.Date(newS.getDate().getTime());
                    System.out.println(sqlDate);
                    tvSum.setItems(dd.SelectByDate(newS.getDate()));
                    
                    float totalSum = 0;
                    for (DateProduct dp : tvSum.getItems()){
                        totalSum += dp.getSum();
                    }
                    
                    labelTotal.setText("Total price: " + totalSum);
                }
            });
            tcName.setCellValueFactory((p) -> {
                return p.getValue().getNameProperty();
            });
            tcQuantity.setCellValueFactory((p) -> {
                return p.getValue().getQuantityProperty();
            });
            tcSum.setCellValueFactory((p) -> {
                return p.getValue().getSumProperty();
            });
        //}
        
    }
}
