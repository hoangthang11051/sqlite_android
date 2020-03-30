package com.hoangthang11051.sqlite_nguyenhoangthang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<SinhVien> svs = new ArrayList<>();
    ListView listView;
    Adapter adapter;
    EditText ten, msv, dtb;
    Button add, option1, option2, option3;
    Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ten = findViewById(R.id.et_ten);
        msv = findViewById(R.id.et_msv);
        dtb = findViewById(R.id.et_dtb);
        add = findViewById(R.id.btn_add);
        option1 = findViewById(R.id.btn_option1);
        option2 = findViewById(R.id.btn_option2);
        option3 = findViewById(R.id.btn_option3);
        listView = findViewById(R.id.list_sv);

        database = new Database(this);

        adapter = new Adapter(this, R.layout.item_list_sinhvien, svs);
        listView.setAdapter(adapter);

        updateList();

        if(svs.isEmpty()){
            database.insertSV(new SinhVien("thang","dtc16hd","1"));
            database.insertSV(new SinhVien("minh","dtc001","3.5"));
            database.insertSV(new SinhVien("Son","dtc002","8"));
            database.insertSV(new SinhVien("tuyen","dtc001","4.1"));
            database.insertSV(new SinhVien("cong","dtc123","3.99"));
            database.insertSV(new SinhVien("tung","dtc789","6"));
            updateList();
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.insertSV(new SinhVien(ten.getText().toString(), msv.getText().toString(), dtb.getText().toString()));
                updateList();
            }
        });
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.removeWhere("dtc001");
                updateList();
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svs.clear();
                svs.addAll(database.getSVOption2(4));
                adapter.notifyDataSetChanged();
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.updateByMsv("dtc010",new SinhVien("new ","new","10"));
                updateList();
            }
        });
    }

    void updateList(){
        svs.clear();
        svs.addAll(database.getAll());
        adapter.notifyDataSetChanged();
    }
}
