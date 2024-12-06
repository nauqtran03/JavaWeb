/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quant
 */
public class KetNoi {
    public static Connection KNCSDL() throws SQLException{
        try {
            //Nạp trình điều khiển
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Thực hiện kết nối 
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlsinhvien1","root","");
            return cn;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KetNoi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
    }
}
