package com.hoangthang11051.sqlite_nguyenhoangthang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE = "QLSV";
    public static final String TABLE = "SV";
    public static final String NAME = "TEN";
    public static final String CODE = "MASV";
    public static final String MARK = "DIEMTB";

    private SQLiteDatabase db;

    public Database(Context context) {
        super(context, DATABASE, null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table SV (TEN nvarchar(50), MASV char(25), DIEMTB char(5))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists SV";
        this.db.execSQL(sql);
    }

    public long insertSV(SinhVien sv) {
        ContentValues cv = new ContentValues();
        cv.put(NAME, sv.ten);
        cv.put(CODE, sv.msv);
        cv.put(MARK, sv.dtb);
        return db.insert(TABLE, null, cv);
    }

    public List<SinhVien> getAll() {
        String sql = "select * from " + TABLE;
        Cursor cs = db.rawQuery(sql, null);
        List<SinhVien> svs = new ArrayList<>();
        while ((cs.moveToNext())) {
            SinhVien sv = new SinhVien(cs.getString(0), cs.getString(1), cs.getString(2));
            svs.add(sv);
        }
        return svs;
    }

    public void removeWhere(String msv) {
        db.delete(TABLE, CODE + "=?", new String[]{msv});
    }

    public List<SinhVien> getSVOption2(int dtb) {
        List<SinhVien> svs = getAll();
        List<SinhVien> svs2 = new ArrayList<>();
        for (SinhVien sv : svs) {
            if (sv.dtb < dtb) {
                svs2.add(sv);
            }
        }
        return svs2;
    }

    public void updateByMsv(String msv, SinhVien newSV) {
        ContentValues cv = new ContentValues();
        cv.put(NAME, newSV.ten);
        cv.put(CODE, newSV.msv);
        cv.put(MARK, newSV.dtb);
        db.update(TABLE, cv, CODE + "=?", new String[]{msv});
    }
}
