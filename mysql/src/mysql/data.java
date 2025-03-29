/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mysql;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Jassen
 */
public class data {
      void SaveData() {

        try {
            DB_connection.init();

            Connection c = DB_connection.getConnection();
            PreparedStatement ps = c.prepareStatement("Insert into student_information (idStudent_information, F_name, M_name, L_name, Contact, Address, Email) values('" + 0 + "','" + F_name.getText() + "','" + M_name.getText() + "',"
                    + "'" + L_name.getText() + "','" + Contact.getText() + "','" + Address.getText() + "','" + Email.getText() + "')");

            ps.execute();
            JOptionPane.showMessageDialog(this, "Data Successfully Saved");

        } catch (SQLException ex) {
            Logger.getLogger(studentsa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
      
void PrintData(DefaultTableModel model) {
        DB_connection.init();

        Connection c = DB_connection.getConnection();
        model.setRowCount(0);
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM student_information");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[]{rs.getString("idStudent_information"), rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});

            }

        } catch (SQLException ex) {
            Logger.getLogger(studentsa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}
