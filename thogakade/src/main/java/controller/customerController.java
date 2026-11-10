package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class customerController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnView;

    @FXML
    private Button btnItem;

    @FXML
    private JFXComboBox<String> cmbTitle;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private DatePicker dateDob;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPostalCode;

    @FXML
    private JFXTextField txtProvince;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id=txtId.getText();
        String title = cmbTitle.getValue();
        String name=txtName.getText();
        LocalDate dob = dateDob.getValue();
        Double salary=Double.parseDouble(txtSalary.getText());
        String address=txtAddress.getText();
        String city=txtCity.getText();
        String province=txtProvince.getText();
        String postalCode=txtPostalCode.getText();


        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakadefx","root","1234");
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?)");

            preparedStatement.setString(1,id);
            preparedStatement.setString(2,title);
            preparedStatement.setString(3,name);
            preparedStatement.setDate(4,java.sql.Date.valueOf(dob));
            preparedStatement.setDouble(5, salary);
            preparedStatement.setString(6, address);
            preparedStatement.setString(7, city);
            preparedStatement.setString(8, province);
            preparedStatement.setString(9, postalCode);

            int res = preparedStatement.executeUpdate();

            if(res>0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Added");
                alert.setHeaderText(null);
                alert.setContentText("Customer added successfully!");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            new Alert(
                    Alert.AlertType.ERROR,
                    e.getMessage()
            ).showAndWait();
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id=txtId.getText();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakadefx","root","1234");
            PreparedStatement stm = conn.prepareStatement("DELETE FROM customer WHERE custId=?");
            stm.setString(1,id);
            int res = stm.executeUpdate();

            if(res>0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Deleted");
                alert.setHeaderText(null);
                alert.setContentText("Customer deleted successfully!");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id=txtId.getText();
        String title = cmbTitle.getValue();
        String name=txtName.getText();
        LocalDate dob = dateDob.getValue();
        Double salary=Double.parseDouble(txtSalary.getText());
        String address=txtAddress.getText();
        String city=txtCity.getText();
        String province=txtProvince.getText();
        String postalCode=txtPostalCode.getText();


        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakadefx","root","1234");
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE customer SET title = ?, name = ?, dob = ?,salary = ?, address = ?, city = ?,province = ?, postalcode = ? WHERE custID = ?");
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,name);
            preparedStatement.setDate(3,java.sql.Date.valueOf(dob));
            preparedStatement.setDouble(4,salary);
            preparedStatement.setString(5,address);
            preparedStatement.setString(6,city);
            preparedStatement.setString(7,province);
            preparedStatement.setString(8,postalCode);
            preparedStatement.setString(9,id);
            int rst = preparedStatement.executeUpdate();
            if(rst>0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Updated");
                alert.setHeaderText(null);
                alert.setContentText("Customer updated successfully!");
                alert.showAndWait();

            }


        } catch (SQLException e) {
            new Alert(
                    Alert.AlertType.ERROR,
                    e.getMessage()
            ).showAndWait();
        }


    }

    @FXML
    void btnViewOnAction(ActionEvent event) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakadefx","root","1234");
            PreparedStatement stm = conn.prepareStatement("select * from customer");
            ResultSet rs = stm.executeQuery();
            ObservableList<Customer> list = FXCollections.observableArrayList();
            while(rs.next()) {


                        Customer cust = new Customer(rs.getString("custID"),
                                rs.getString("title"),
                                rs.getString("name"),
                                rs.getDate("dob").toLocalDate(),
                                rs.getDouble("salary"),
                                rs.getString("address"),
                                rs.getString("city"),
                                rs.getString("province"),
                                rs.getString("postalCode"));

                        list.add(cust);

            }

            tblCustomer.setItems(list);



        } catch (SQLException e) {
            new Alert(
                    Alert.AlertType.ERROR,
                    e.getMessage()
            ).showAndWait();
        }

    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        try {
            String id = txtId.getText();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakadefx","root","1234");
            PreparedStatement stm = conn.prepareStatement("select * from customer where  custID=?");
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();

            if(rs.next()) {
                cmbTitle.setValue(rs.getString("title"));
                txtName.setText(rs.getString("name"));
                Date date = rs.getDate("dob");
                dateDob.setValue(date.toLocalDate());
                txtSalary.setText(String.valueOf(rs.getDouble("salary")));
                txtAddress.setText(rs.getString("address"));
                txtCity.setText(rs.getString("city"));
                txtProvince.setText(rs.getString("province"));
                txtPostalCode.setText(rs.getString("postalcode"));

            }else{

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("no customer found!");
                alert.showAndWait();
            }



        } catch (SQLException e) {
            new Alert(
                    Alert.AlertType.ERROR,
                    e.getMessage()
            ).showAndWait();
        }
    }

    @FXML
    void btnItemOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/itemManagement.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> titles = FXCollections.observableArrayList(
                "Mr","Mrs","Ms"
        );
        cmbTitle.setItems(titles);


        colId.setCellValueFactory(
                new PropertyValueFactory<>("cusId")
        );

        colTitle.setCellValueFactory(
            new PropertyValueFactory<>("title")
        );

        colName.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        colDob.setCellValueFactory(
                new PropertyValueFactory<>("dob")
        );

        colSalary.setCellValueFactory(
                new PropertyValueFactory<>("salary")
        );

        colAddress.setCellValueFactory(
                new PropertyValueFactory<>("address")
        );

        colCity.setCellValueFactory(
                new PropertyValueFactory<>("city")
        );

        colProvince.setCellValueFactory(
                new PropertyValueFactory<>("province")
        );
        colPostalCode.setCellValueFactory(
                new PropertyValueFactory<>("postalCode")
        );



    }
}
