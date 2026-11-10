package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class itemController  implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnView;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableView<Item> tblItem;

    @FXML
    private JFXTextField txtCode;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtSize;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String code=txtCode.getText();
        String description=txtDescription.getText();
        String size=txtSize.getText();
        Double price=Double.parseDouble(txtPrice.getText());
        Integer qty=Integer.parseInt(txtQty.getText());

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakadefx","root","1234");
            PreparedStatement stm=conn.prepareStatement("INSERT INTO item VALUES (?,?,?,?,?)");
            stm.setString(1, code);
            stm.setString(2, description);
            stm.setString(3, size);
            stm.setDouble(4, price);
            stm.setInt(5, qty);
            int rst = stm.executeUpdate();
            if (rst > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.showAndWait();
            }

        } catch (SQLException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String code=txtCode.getText();
        Connection conn= null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakadefx","root","1234");
            PreparedStatement stm = conn.prepareStatement("DELETE FROM item WHERE itemCode=?");
            stm.setString(1, code);
            int rst = stm.executeUpdate();
            if (rst > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.showAndWait();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String code=txtCode.getText();
        String description=txtDescription.getText();
        String size=txtSize.getText();
        Double price=Double.parseDouble(txtPrice.getText());
        Integer qty=Integer.parseInt(txtQty.getText());

        try {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakadefx","root","1234");
            PreparedStatement stm = conn.prepareStatement("UPDATE item SET description=?,packSize=?,unitPrice=?,qtyOnHand=? WHERE itemCode=?");
            stm.setString(1, description);
            stm.setString(2, size);
            stm.setDouble(3, price);
            stm.setInt(4, qty);
            stm.setString(5, code);
            int rst = stm.executeUpdate();
            if (rst > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }
    }

    @FXML
    void btnViewOnAction(ActionEvent event) {


        try {
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakadefx","root","1234");
            PreparedStatement stm=conn.prepareStatement("select * from item");

            ResultSet rs=stm.executeQuery();

            ObservableList<Item> list= FXCollections.observableArrayList();

            while(rs.next()){
                Item item=new Item(
                        rs.getString("itemCode"),
                        rs.getString("description"),
                        rs.getString("packSize"),
                        rs.getDouble("unitPrice"),
                        rs.getInt("qtyOnHand")
                );

                list.add(item);
            }

            tblItem.setItems(list);



        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    void txtCodeOnAction(ActionEvent event) {
        String code=txtCode.getText();
        Connection conn= null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakadefx","root","1234");
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM item WHERE itemCode = ?");
            stm.setString(1,code);
            ResultSet rs=stm.executeQuery();

            rs.next();
            txtCode.setText(rs.getString("itemCode"));
            txtDescription.setText(rs.getString("description"));
            txtSize.setText(rs.getString("packSize"));
            txtPrice.setText(rs.getString("unitPrice"));
            txtQty.setText(rs.getString("qtyOnHand"));


        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();


        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCode.setCellValueFactory(
                new PropertyValueFactory<>("itemCode")
        );

        colDescription.setCellValueFactory(
                new PropertyValueFactory<>("description")
        );

        colPackSize.setCellValueFactory(
                new PropertyValueFactory<>("packSize")
        );

        colPrice.setCellValueFactory(
                new PropertyValueFactory<>("unitPrice")
        );
        colQty.setCellValueFactory(
                new PropertyValueFactory<>("qtyOnHand")
        );
    }
}
