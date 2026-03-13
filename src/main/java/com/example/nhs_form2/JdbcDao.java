package com.example.nhs_form2;
import javafx.scene.control.Alert;
import java.sql.*;
public class JdbcDao {
    private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/nhs_form?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";

    private static final String INSERT_QUERY_Member  = "INSERT INTO member (member_name,member_grade) VALUES (?,?)";
    private static final String INSERT_QUERY_Hours = "INSERT INTO hours (total_hours, hour_date) VALUES (?,?)";
    private static final String INSERT_QUERY_Organization = "INSERT INTO organization (orga_name, orga_contact) VALUES (?,?) ";
    private static final String INSERT_QUERY_Supervisor = "INSERT INTO supervisor (super_name, super_contact, super_signature) VALUES (?,?)";

    public void insertRecordMember(String member_name, String member_grade) throws SQLException {
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME,DATABASE_USERNAME);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY_Member)) {
            preparedStatement.setString(1,member_name);
            preparedStatement.setString(2,member_grade);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public void insertRecordHours(String hour_date, String total_hours) throws SQLException {
        try(Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY_Hours)) {
            preparedStatement.setString(5,total_hours);
            preparedStatement.setString(6,hour_date);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }

    }
    public void insertRecordOrganization(String orga_name, String orga_contact) throws SQLException {
        try(Connection connection = DriverManager
                .getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY_Organization)) {
            preparedStatement.setString(3,orga_name);
            preparedStatement.setString(4,orga_contact);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public void insertRecordSupervisor(String super_name, String super_contact, String super_signature) throws SQLException {
        try(Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY_Supervisor)) {
            preparedStatement.setString(7,super_name);
            preparedStatement.setString(8,super_contact);
            preparedStatement.setString(9,super_signature);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public void viewRecords() throws SQLException {
        String SELECT_QUERY = ("SELECT * FROM member");
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_QUERY)) {

            StringBuilder allRecords = new StringBuilder();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("member_name");
                String grade = resultSet.getString("member_grade");
                String orgaName = resultSet.getString("orga_name");
                String orgaContact = resultSet.getString("orga_contact");
                String hours = resultSet.getString("total_hours");
                String date = resultSet.getString("hour_date");
                String superName = resultSet.getString("super_name");
                String superContact = resultSet.getString("super_contact");
                String superSignature = resultSet.getString("super_signature");


                allRecords.append("Name: ").append(name)
                        .append("|Grade: ").append(grade)
                        .append("|Organization: ").append(orgaName)
                        .append("|Contact: ").append(orgaContact)
                        .append("|Completed-Hours: ").append(hours)
                        .append("|Date: ").append(date)
                        .append("|Supervisor-Name: ").append(superName)
                        .append("|Supervisor-Contact: ").append(superContact)
                        .append("|Supervisor-Signature: ").append(superSignature)
                        .append("\n");
            }
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Records");
            infoAlert.setHeaderText("Showing all Records");

            infoAlert.setContentText(allRecords.toString());
            infoAlert.showAndWait();


        }
    }
    private void printSQLException(SQLException ex) {
        for(Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while(t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }



}
