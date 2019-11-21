/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2ndyrGroupA
 */
public class DB_JAVA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // TODO code application logic here
            String dbURL = "jdbc:mysql://localhost:3306/exercise";
            Connection connection = DriverManager
                    .getConnection(dbURL, "root", "");
            if (connection != null) {
                System.out.println("Connection established");
//                read(connection);
//                    create(connection,"No one", 20);
//                    update(connection,4,"Maldita");
                    delete(connection,5);
//                     create(connection, 2, "Maldita", 19);
////                    delete(connection, 3);
////                read(connection);
////                create(connection, 3, "No one", 20);
////                create(connection, 4, "Maldita", 19);
//            
//                update(connection,1,"maldita");
//                connection.close();

            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Cannot load Driver");;
        } catch (SQLException ex) {
            System.out.println(">>"+ex.getMessage());;
        }
    }

    private static void read(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM student");

        while (rs.next()) {
            int id = rs.getInt("Id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            System.out.println("Id: " + id + "\nName: " + name + "\nAge: " + age);

        }
    }

    private static void create(Connection connection,String name, int age) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO student (name,age) VALUES(?,?)");
//        statement.setInt(1, id);
        statement.setString(1, name);
        statement.setInt(2, age);

        int number = statement.executeUpdate();
        if (number > 0) {
            System.out.println("Inserted " + number + " rows");
        } else {
            System.out.println("No update");
        }
    }

    private static void delete(Connection connection, int id) throws SQLException {
        Statement st = connection.createStatement();

        int nbrOfRows = st.executeUpdate("DELETE FROM student WHERE id =" + id);
        if (nbrOfRows > 0) {
            System.out.println("Deleted Successfully " + nbrOfRows + " rows");
        } else {
            System.out.println("Unable to delete");
        }

    }

    private static void update(Connection connection,int id,String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE student SET name = ? WHERE id = ?");
        statement.setString(1,name);
        statement.setInt(2, id);
        

        int number = statement.executeUpdate();
        if (number > 0) {
            System.out.println("Update successfully" + number + " rows" );
        } else {
            System.out.println("Failed to update data" + id);
        }

    }

}
