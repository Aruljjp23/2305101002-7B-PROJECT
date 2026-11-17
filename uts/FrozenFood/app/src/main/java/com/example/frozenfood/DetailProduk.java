package com.example.frozenfood;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class DetailProduk extends AppCompatActivity {

    TextView tvJumlah;
    int jumlah = 1;
    String namaProduk;
    int hargaProduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        ImageView imgProduk = findViewById(R.id.imgDetailProduk);
        TextView tvNama = findViewById(R.id.tvNamaProduk);
        TextView tvHarga = findViewById(R.id.tvHargaProduk);
        TextView tvKategori = findViewById(R.id.tvKategoriProduk);
        TextView tvDeskripsi = findViewById(R.id.tvDeskripsiProduk);
        TextView tvStok = findViewById(R.id.tvStokProduk);

        TextView btnKembali = findViewById(R.id.btnKembaliDetail);
        TextView btnKurang = findViewById(R.id.btnKurang);
        TextView btnTambah = findViewById(R.id.btnTambah);
        tvJumlah = findViewById(R.id.tvJumlah);

        Button btnKeranjang = findViewById(R.id.btnTambahKeranjang);
        Button btnBeli = findViewById(R.id.btnBeliSekarang);

        namaProduk = getIntent().getStringExtra("nama");
        hargaProduk = getIntent().getIntExtra("harga", 0);

        int gambar = getIntent().getIntExtra(
                "gambar", R.drawable.nugget
        );

        String kategori = getIntent().getStringExtra("kategori");
        String deskripsi = getIntent().getStringExtra("deskripsi");
        String stok = getIntent().getStringExtra("stok");

        imgProduk.setImageResource(gambar);
        tvNama.setText(namaProduk);
        tvHarga.setText("Rp" + String.format("%,d", hargaProduk).replace(',', '.'));
        tvKategori.setText(kategori);
        tvDeskripsi.setText(deskripsi);
        tvStok.setText(stok);

        btnKembali.setOnClickListener(v -> finish());

        btnKurang.setOnClickListener(v -> {
            if (jumlah > 1) {
                jumlah--;
                tvJumlah.setText(String.valueOf(jumlah));
            }
        });

        btnTambah.setOnClickListener(v -> {
            jumlah++;
            tvJumlah.setText(String.valueOf(jumlah));
        });


        btnKeranjang.setOnClickListener(v -> {

            String idProduk = "nugget";

            if (namaProduk.equals("Sosis Sapi")) {
                idProduk = "sosis";
            } else if (namaProduk.equals("Kentang Goreng")) {
                idProduk = "kentang";
            } else if (namaProduk.equals("Dimsum Ayam")) {
                idProduk = "dimsum";
            }

            KeranjangHelper.tambahProduk(
                    this,
                    idProduk,
                    namaProduk,
                    hargaProduk,
                    gambar,
                    jumlah
            );

            Toast.makeText(this,
                    jumlah + " " + namaProduk + " ditambahkan ke keranjang",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, Keranjang.class);
            startActivity(intent);
        });

        btnBeli.setOnClickListener(v -> {

            String idProduk = "nugget";

            if (namaProduk.equals("Sosis Sapi")) {
                idProduk = "sosis";
            } else if (namaProduk.equals("Kentang Goreng")) {
                idProduk = "kentang";
            } else if (namaProduk.equals("Dimsum Ayam")) {
                idProduk = "dimsum";
            }

            KeranjangHelper.tambahProduk(
                    this, idProduk, namaProduk,
                    hargaProduk, gambar, jumlah
            );

            startActivity(new Intent(this, Keranjang.class));
        });
    }
}
