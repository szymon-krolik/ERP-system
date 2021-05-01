package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.dto.EmployeeDto;
import sample.factory.PopupFactory;
import sample.rest.EmployeeRestClient;
import sample.table.EmployeeTableModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EmployeeController  implements Initializable {
    private static final String ADD_EMPLOYEE_FXML = "/fxml/add-employee.fxml";
    private static final String VIEW_EMPLOYEEFXML = "/fxml/view-employee.fxml";

    private final EmployeeRestClient employeeRestClient;
    private final PopupFactory popupFactory;

    private  ObservableList<EmployeeTableModel> employeeData;


    @FXML
    private Button addButton;

    @FXML
    private Button viewButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button refreshButton;

    @FXML
    private TableView<EmployeeTableModel> employeeTableView;

    public EmployeeController() {
        employeeRestClient = new EmployeeRestClient();
        employeeData = FXCollections.observableArrayList();
        popupFactory = new PopupFactory();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeAddEmployeeButton();
        initializeRefreshButton();
        initializeTableView();
        initializeViewEmployeeButton();

    }

    private void initializeViewEmployeeButton() {
        viewButton.setOnAction( x -> {
            EmployeeTableModel employee = employeeTableView.getSelectionModel().getSelectedItem();
            if ( employee == null ) {
                return;
            } else {
                try {
                    Stage waitingPopup = popupFactory.createWaitingPopup("Loading employee data");
                    waitingPopup.show();
                    Stage viewEmployeeStage = new Stage();
                    viewEmployeeStage.initStyle(StageStyle.UNDECORATED);
                    viewEmployeeStage.initModality(Modality.APPLICATION_MODAL);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEW_EMPLOYEEFXML));
                    Scene scene = new Scene((BorderPane) loader.load(), 500, 400);
                    viewEmployeeStage.setScene(scene);
                    ViewEmployeeController controller = loader.<ViewEmployeeController>getController();
                    controller.loadEmployeeData(employee.getIdEmployee(), () -> {
                        waitingPopup.close();
                        viewEmployeeStage.show();
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initializeRefreshButton() {
        refreshButton.setOnAction( (x) -> {
            loadEmployeeData();
        });
    }

    private void initializeAddEmployeeButton() {
        addButton.setOnAction( (x) -> {
            Stage addEmployeeStage = new Stage();
            addEmployeeStage.initStyle(StageStyle.UNDECORATED);
            addEmployeeStage.initModality(Modality.APPLICATION_MODAL);
            try {
                Parent addEmployeeParent = FXMLLoader.load(getClass().getResource(ADD_EMPLOYEE_FXML));
                Scene scene = new Scene(addEmployeeParent, 500, 400);
                addEmployeeStage.setScene(scene);
                addEmployeeStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initializeTableView() {
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
        loadEmployeeData();
        employeeTableView.setItems(employeeData);
    }

    private void loadEmployeeData() {
        Thread thread = new Thread( () -> {
            List<EmployeeDto> employees = employeeRestClient.getEmployees();
            employeeData.clear();
            employeeData.addAll( employees.stream().map(EmployeeTableModel::of).collect(Collectors.toList()));
        });
        thread.start();
    }

}
