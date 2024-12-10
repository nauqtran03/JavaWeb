/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;
import java.sql.*;
/**
 *
 * @author quant
 */
public class KetNoi {
    public static Connection KNCSDL() throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bai5","root","");
            return con;
        }catch(ClassNotFoundException e){
            e.getMessage();
        }
        return null;
    }
}
