/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;
import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author quant
 */
public class KetNoi {
    public static Connection KNCSDL() throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bai2","root","");
            return con;
        }catch(ClassNotFoundException ex)
        {
           Logger.getLogger(KetNoi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
