import javax.swing.*;
import java.awt.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DataBase {
    private InputStreamReader isr;
    private BufferedReader br;
    private Statement st;
    private ResultSet rs;
    private String sqlQuery;
    private Connection connection;

    public DataBase() {

        isr = new InputStreamReader(System.in);
        br = new BufferedReader(isr);

        System.out.println("-------- PostgreSQL "
                + "JDBC Connection Testing ------------");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return;

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/phone_app_db", "postgres",
                    "D4t4b4s3");
            st = connection.createStatement();
        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("Connection established!");
        } else {
            System.out.println("Failed to make connection!");
        }


    }

    public Connection getConnection() {
        return connection;
    }

    public void newcontact(String name, String lastName, String phoneNumber, String email) {
        String sql = "INSERT INTO contact (contactname,contactlastname,contactphonenumber, contactmail) VALUES ('" + name + "','" + lastName + "','" + phoneNumber + "','" + email + "');";
        try {
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "New contact created.");
            System.out.print("New contact added.");
        } catch (Exception e) {
            System.out.println("Error when inserting a new contact");
            JOptionPane.showMessageDialog(null, "Contact exists in the DB", null, JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public String checkIfContactExists(String name, String lastName, String phoneNumber, String email) {
        String sql = "select contactname from contact where contactname = '" + name + "' and contactlastname = '" + lastName + "' " +
                "and contactphonenumber = '" + phoneNumber + "' and contactmail = '" + email + "'";
        String resultQuery = "";
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                resultQuery = rs.getString("contactname");

            }

        } catch (Exception e) {
            System.out.println("Error when checking if the contact already exists in the DB before inserting.");
        }
        return resultQuery;
    }

    public void removeContact(String name, String lastName) {
        String sql2 = "select count(*) as countContacts from contact";
        String sql = "delete from contact where contactname='" + name + "' and contactlastname='" + lastName + "'";
        String sql3 = "select contactname from contact where contactname = '" + name + "' and contactlastname = '" + lastName + "'";
        int countContacts_BEFORE = 0, countContacts_AFTER = 0;
        String contactName = "";

        try {
            //0, check if the contact already exists.
            rs = st.executeQuery(sql3);
            while (rs.next()) {
                contactName = rs.getString("contactname");
            }

            if (contactName == "") {
                JOptionPane.showMessageDialog(null, "Contact '" + name + " " + lastName + "' does not exist.", null, JOptionPane.ERROR_MESSAGE);
            } else {
                //1st, save the number of contacts BEFORE removing it.
                rs = st.executeQuery(sql2);
                while (rs.next()) {
                    countContacts_BEFORE = rs.getInt("countContacts");
                }
                System.out.println("countContacts_BEFORE = " + countContacts_BEFORE);

                //2nd, remove the contact (if it exists).
                st.executeUpdate(sql);

                //3th, save the number of contacts AFTER removing it
                rs = st.executeQuery(sql2);
                while (rs.next()) {
                    countContacts_AFTER = rs.getInt("countContacts");
                }
                System.out.println("countContacts_AFTER = " + countContacts_AFTER);

                //4, Check if the number of contacts has been reduced or not.
                if (countContacts_AFTER < countContacts_BEFORE) {
                    JOptionPane.showMessageDialog(null, "Contact removed.");
                } else {
                    JOptionPane.showMessageDialog(null, "Contact not removed.", null, JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            System.out.println("Error when removing contact.");
        }
    }

    public String showAllContacts() {
        String s = "", name = "", lastname = "", phone = "", mail = "", contact = "";
        String sql = "SELECT * FROM contact";
        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                name = rs.getString("contactname");
                lastname = rs.getString("contactlastname");
                phone = rs.getString("contactphonenumber");
                mail = rs.getString("contactmail");

                contact = "Name: " + name + ", Lastname: " + lastname + "\nPhone: " + phone + ", Mail: " + mail + "\n\n";
                s = s + contact;
            }
        } catch (Exception e) {
            System.out.println("Error when trying to show all contacts.");
        }
        return s;
    }

}
