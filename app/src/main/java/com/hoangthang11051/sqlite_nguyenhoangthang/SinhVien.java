package com.hoangthang11051.sqlite_nguyenhoangthang;

public class SinhVien {
    public String ten,msv;
    public double dtb;

    public SinhVien(String ten, String msv, String dtb) {
        this.ten = ten;
        this.msv = msv;
        this.dtb = Double.parseDouble(dtb);
    }
}
