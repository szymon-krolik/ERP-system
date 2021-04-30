package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.dto.EmployeeDto;
import sample.rest.EmployeeRestClient;
import sample.table.EmployeeTableModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EmployeeController  implements Initializable {

    private final EmployeeRestClient employeeRestClient;

    public EmployeeController() {
        employeeRestClient = new EmployeeRestClient();
    }

    @FXML
    private TableView<EmployeeTableModel> employeeTableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Add columns to table
        TableColumn firstNameColumn = new TableColumn("First name");
        firstNameColumn.setMinWidth(100);
        //dodanie do tabeli. "EmployeeTableModel" => nadrzedny model,typ i nazwa pola z EmployeeTableModel
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeTableModel,String>("firstName"));

        TableColumn lastNameColumn = new TableColumn("Last name");
        lastNameColumn.setMinWidth(100);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeTableModel,String>("lastName"));

        TableColumn salaryColumn = new TableColumn("Salary");
        salaryColumn.setCellValueFactory(new PropertyValueFactory<EmployeeTableModel,String>("salary"));
        salaryColumn.setMinWidth(100);

        //show tables on scene
        employeeTableView.getColumns().addAll(firstNameColumn, lastNameColumn, salaryColumn);

        ObservableList<EmployeeTableModel> employeeData = FXCollections.observableArrayList();
        loadEmployeeData(employeeData);
        employeeTableView.setItems(employeeData);

    }

    private void loadEmployeeData(ObservableList<EmployeeTableModel> employeeData) {
        Thread thread = new Thread( () -> {
            List<EmployeeDto> employees = employeeRestClient.getEmployees();
            employeeData.addAll( employees.stream().map(EmployeeTableModel::of).collect(Collectors.toList()));
        });
        thread.start();
    }

}
