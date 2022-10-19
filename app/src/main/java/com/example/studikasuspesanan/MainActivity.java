package com.example.studikasuspesanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ArrayList<Makanan> listMakanan;
    private RecyclerView recPesanan;
    MakananAdapter adapter;

    EditText namaMenu, hargaMenu, jumlahMenu;
    Button btnTambah, btnReset;
    TextView biayaTot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listMakanan = new ArrayList<>();
        initComponents();

        btnTambah.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                tambahMakanan();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetMakanan();
            }
        });
    }

    public void initComponents(){
        recPesanan = findViewById(R.id.listPesan);
        adapter = new MakananAdapter(listMakanan);
        recPesanan.setAdapter(adapter);
        recPesanan.setLayoutManager(new LinearLayoutManager(this));

        namaMenu = findViewById(R.id.namaMenu);
        hargaMenu = findViewById(R.id.hargaMenu);
        jumlahMenu = findViewById(R.id.jumlahMenu);

        btnTambah = findViewById(R.id.btnTambah);
        btnReset = findViewById(R.id.btnReset);

        biayaTot = findViewById(R.id.totalBiaya);
        biayaTot.setText("0");
    }

    public void resetInput(){
        namaMenu.setText("");
        hargaMenu.setText("");
        jumlahMenu.setText("");
    }

    public void resetMakanan(){
        resetInput();
        listMakanan.clear();
        adapter.notifyDataSetChanged();
        biayaTot.setText("0");
    }

    public String getTotBiaya(){
        int total = 0;
        for (Makanan makanan : listMakanan){
            total += makanan.harga * makanan.jumlah;
        }
        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(0);
        return format.format(total);
    }

    public void tambahMakanan(){
        Makanan makanan = new Makanan(namaMenu.getText().toString(), Integer.parseInt(hargaMenu.getText().toString()),
                Integer.parseInt(jumlahMenu.getText().toString()));

        listMakanan.add(makanan);
        adapter.notifyDataSetChanged();
        resetInput();

        biayaTot.setText(getTotBiaya());
    }
}