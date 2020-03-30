package com.hoangthang11051.sqlite_nguyenhoangthang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter extends ArrayAdapter<SinhVien> {
    Context context;
    List<SinhVien> svs;

    public Adapter(@NonNull Context context, int resource, @NonNull List<SinhVien> objects) {
        super(context, resource, objects);
        this.context = context;
        this.svs = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list_sinhvien,parent,false);

        TextView ten,msv,dtb;
        ten = v.findViewById(R.id.tv_ten);
        msv = v.findViewById(R.id.tv_msv);
        dtb = v.findViewById(R.id.tv_dtb);

        SinhVien sv = getItem(position);

        ten.setText(sv.ten);
        msv.setText(sv.msv);
        dtb.setText(sv.dtb+"");
        return v;

    }
}
