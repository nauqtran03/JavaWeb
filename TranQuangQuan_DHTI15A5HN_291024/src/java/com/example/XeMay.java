/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author quant
 */
package com.example;
public class XeMay {
    private String ma;
    private String ten;
    private String hangSX;
    private String mau;

    // Constructor
    public XeMay(String ma, String ten, String hangSX, String mau) {
        this.ma = ma;
        this.ten = ten;
        this.hangSX = hangSX;
        this.mau = mau;
    }

    // Getter và Setter
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHangSX() {
        return hangSX;
    }

    public void setHangSX(String hangSX) {
        this.hangSX = hangSX;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }
}
