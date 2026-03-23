package com.example.nhs_form2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.sql.*;

public class HoursController {
    @FXML
    private Button viewRecords;
    @FXML
    private Button submitButton;
    @FXML
    private TextField memberName;
    @FXML
    private TextField memberGrade;
    @FXML
    private TextField orgaName;
    @FXML
    private TextField service;
    @FXML
    private TextField orgaContact;
    @FXML
    private TextField hourService;
    @FXML
    private TextField dateService;
    @FXML
    private TextField supervisorName;
    @FXML
    private TextField contactInfo;
    @FXML
    private TextField electronicSignature;
    @FXML
    private TextField sigDate;
    @FXML
    private Label welcomeText;
    @FXML
    private void submitButtonClick(ActionEvent event) throws SQLException {
        Window owner = submitButton.getScene().getWindow();

        System.out.println("Name: " + memberName.getText());
        System.out.println("Grade: " + memberGrade.getText());
        System.out.println("Organization Name: " + orgaName.getText());
        System.out.println("Organization Contact Information: " + orgaContact.getText());
        System.out.println("Description of Service: " + service.getText());
        System.out.println("Total Hours for Service: " + hourService.getText());
        System.out.println("Date of the Service: " + dateService.getText());
        System.out.println("Supervisor Name: " + supervisorName.getText());
        System.out.println("Supervisor Contact Information: " + contactInfo.getText());
        System.out.println("Electronic Signature: " + electronicSignature.getText());
        System.out.println("Signature Date: " + sigDate.getText());

        if (memberName.getText().isEmpty()) {
           showAlert(Alert.AlertType.ERROR, owner,"Form Error", "Please enter your name");
           return;
        }
        if (memberGrade.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner,"Form Error", "Please enter your grade");
            return;
        }
        if (orgaName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner,"Form Error", "Please enter the name of your organization");
            return;
        }
        if (service.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner,"Form Error", "Please enter the service completed");
            return;
        }
        if (orgaContact.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Please enter the organization contact information");
            return;
        }
        if (hourService.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner,"Form Error", "Please enter how many of service you did");
            return;
        }
        if (dateService.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Please enter the date of service ");
            return;
        }
        if (supervisorName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error",  "Please enter the name of the supervisor");
            return;
        }
        if (contactInfo.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Please enter the supervisor's contact information");
            return;
        }
        if (electronicSignature.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner,"Form Error", "Please make sure you have a signature");
            return;
        }
        if (sigDate.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Please enter the date your supervisor signed");
            return;
        }
        String name = (memberName.getText());
        String grade = (memberGrade.getText());
        String organizationName = (orgaName.getText());
        String organizationContact = (orgaContact.getText());
        String hours = (hourService.getText());
        String date = (dateService.getText());
        String suName = (supervisorName.getText());
        String suContact = (contactInfo.getText());
        String eleSig = (electronicSignature.getText());


        com.example.nhs_form2.JdbcDao jdbcDao = new com.example.nhs_form2.JdbcDao();
        jdbcDao.insertRecordMember(name, grade);
        jdbcDao.insertRecordSupervisor(suName, suContact, eleSig);
        jdbcDao.insertRecordHours(hours, date);
        jdbcDao.insertRecordOrganization(organizationName, organizationContact);
    }
    @FXML
    public void records(ActionEvent event) throws SQLException {
        com.example.nhs_form2.JdbcDao jdbcDao = new com.example.nhs_form2.JdbcDao();
        jdbcDao.viewRecords();
    }
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}

