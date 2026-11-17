package com.example.frozenfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.Iterator;

public class Keranjang extends AppCompatActivity {

    LinearLayout layoutDaftar;
    TextView tvJumlahItem;
    TextView tvSubtotal;
    TextView tvTotal;
    TextView tvKosong;
    LinearLayout layoutRingkasan;
    Button btnCheckout;

    int subtotal = 0;
    int totalProduk = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);

        layoutDaftar = findViewById(R.id.layoutDaftarKeranjang);
        tvJumlahItem = findViewById(R.id.tvJumlahItemKeranjang);
        tvSubtotal = findViewById(R.id.tvSubtotal);
        tvTotal = findViewById(R.id.tvTotal);
        tvKosong = findViewById(R.id.tvKeranjangKosong);
        layoutRingkasan = findViewById(R.id.layoutRingkasan);
        btnCheckout = findViewById(R.id.btnCheckout);

        tampilkanKeranjang();

        findViewById(R.id.btnCheckout).setOnClickListener(v -> {
            startActivity(new Intent(Keranjang.this, Checkout.class));
        });

        findViewById(R.id.navHomeKeranjang).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.navKategoriKeranjang).setOnClickListener(v -> {
            startActivity(new Intent(this, Kategori.class));
        });

        findViewById(R.id.navKeranjangAktif).setOnClickListener(v -> {
            tampilkanKeranjang();
        });

        findViewById(R.id.navPesananKeranjang).setOnClickListener(v -> {
            Toast.makeText(this, "Halaman Pesanan belum tersedia",
                    Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.navProfilKeranjang).setOnClickListener(v -> {
            Toast.makeText(this, "Halaman Profil belum tersedia",
                    Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilkanKeranjang();
    }

    private void tampilkanKeranjang() {
        layoutDaftar.removeAllViews();

        subtotal = 0;
        totalProduk = 0;

        JSONObject keranjang = KeranjangHelper.ambilKeranjang(this);
        Iterator<String> keys = keranjang.keys();

        while (keys.hasNext()) {
            String id = keys.next();

            try {
                JSONObject produk = keranjang.getJSONObject(id);

                String nama = produk.getString("nama");
                int harga = produk.getInt("harga");
                int gambar = produk.getInt("gambar");
                int jumlah = produk.getInt("jumlah");

                subtotal += harga * jumlah;
                totalProduk += jumlah;

                View item = LayoutInflater.from(this).inflate(
                        R.layout.activity_item_keranjang, layoutDaftar, false
                );

                ImageView img = item.findViewById(R.id.imgKeranjang);
                TextView tvNama = item.findViewById(R.id.tvNamaKeranjang);
                TextView tvHarga = item.findViewById(R.id.tvHargaKeranjang);
                TextView tvJumlah = item.findViewById(R.id.tvJumlahItem);

                TextView btnKurang = item.findViewById(R.id.btnKurangItem);
                TextView btnTambah = item.findViewById(R.id.btnTambahItem);
                TextView btnHapus = item.findViewById(R.id.btnHapusItem);

                img.setImageResource(gambar);
                tvNama.setText(nama);
                tvHarga.setText("Rp" + formatRupiah(harga));
                tvJumlah.setText(String.valueOf(jumlah));

                btnKurang.setOnClickListener(v -> {
                    KeranjangHelper.ubahJumlah(
                            this, id, jumlah - 1
                    );
                    tampilkanKeranjang();
                });

                btnTambah.setOnClickListener(v -> {
                    KeranjangHelper.ubahJumlah(
                            this, id, jumlah + 1
                    );
                    tampilkanKeranjang();
                });

                btnHapus.setOnClickListener(v -> {
                    KeranjangHelper.hapusProduk(this, id);
                    tampilkanKeranjang();
                });

                layoutDaftar.addView(item);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        tvJumlahItem.setText(totalProduk + " produk dipilih");
        tvSubtotal.setText("Rp" + formatRupiah(subtotal));
        tvTotal.setText("Rp" + formatRupiah(subtotal));

        if (totalProduk == 0) {
            tvKosong.setVisibility(View.VISIBLE);
            layoutRingkasan.setVisibility(View.GONE);
            btnCheckout.setEnabled(false);
            btnCheckout.setAlpha(0.5f);
        } else {
            tvKosong.setVisibility(View.GONE);
            layoutRingkasan.setVisibility(View.VISIBLE);
            btnCheckout.setEnabled(true);
            btnCheckout.setAlpha(1f);
        }
    }

    private String formatRupiah(int angka) {
        return String.format("%,d", angka).replace(',', '.');
    }
}