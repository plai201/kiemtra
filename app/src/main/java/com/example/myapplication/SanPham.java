package com.example.myapplication;

import androidx.annotation.NonNull;

public class SanPham {
    private int id;
    private String ten;
    private double gia;

    public SanPham() {
    }

    public SanPham(int id, String ten, double gia) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
    }

    @NonNull
    @Override
    public String toString() {
        return ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
}
