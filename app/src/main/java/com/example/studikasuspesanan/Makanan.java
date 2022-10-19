package com.example.studikasuspesanan;

import java.text.NumberFormat;
import java.util.Locale;

public class Makanan {
    public String nama;
    public int harga, jumlah;

    public Makanan(String nama, int harga, int jumlah) {
        this.nama = nama;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    @Override
    public String toString() {
        return nama + " ( " + jumlah + " X " + harga + " ) ";
    }

    public String getJumlahHarga() {
        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(0);
        return format.format(harga*jumlah);
    }
}
