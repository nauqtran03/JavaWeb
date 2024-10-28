/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
/**
 *
 * @author quant
 */
public class KetNoi1 {
    public static Connection KNCSDL() throws SQLException{
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlsv","root","");
        return cn;
    } 
}
