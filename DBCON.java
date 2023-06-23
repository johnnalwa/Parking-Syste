/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkingsystem.ant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author johnn
 */
public class DBCON {
        public static Connection getConnection() {
          Connection connection = null;
        try {
            if (connection == null) {
                // Load the MySQL driver class

                Class.forName("com.mysql.cj.jdbc.Driver");

                // Create a new connection to the database
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/packingsystem", "root", "");
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return connection;
    }
    
    public DefaultTableModel getTableDetails(String name){
        Connection con=getConnection();
        try {
           
             PreparedStatement stmt=con.prepareStatement("SELECT * FROM reservation " +name+";");
             ResultSet rs=stmt.executeQuery();
             ResultSetMetaData rsmd=rs.getMetaData();
              int col=rsmd.getColumnCount();
              ArrayList<String >columns=new ArrayList<>();
              for (int i = 0;i<col;i++){
                  columns.add(rsmd.getColumnName(i+1));
              }
              
              ArrayList<ArrayList<String>>rows=new ArrayList<>();
              while(rs.next()){
                  ArrayList<String>row=new ArrayList<>();
                  for (int i=0;i<col;i++){
                    row.add(rs.getString(i+1));
                  }
                  rows.add(row);
              }
              //String [][] stringArray=rows.stream().map(u -> u.toArray(String[]::new)).toArray(String[][] ::new);
             // return new DefaultTableModel(stringArray,columns.toArray());

//            // Close database connection
//            System.out.println("done");
           

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
