/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
/**
 *
 * @author quant
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        Connection ketnoi = KetNoi1.KNCSDL();
        if(ketnoi != null){
            System.out.println("Kết nối thành công ");
        }else{
            System.out.println("Kết nối thất bại");
        }
    }
}
