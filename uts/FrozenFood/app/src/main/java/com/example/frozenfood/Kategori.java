package com.example.frozenfood;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Kategori extends AppCompatActivity {

    private EditText Search;
    private View itemNugget, itemSosis, itemKentang, itemDimsum;

    private String kategoriDipilih = "Semua";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        getWindow().setStatusBarColor(
                android.graphics.Color.rgb(247, 248, 245)
        );

        getWindow().setNavigationBarColor(
                android.graphics.Color.rgb(247, 248, 245)
        );

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                        | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        );

        Search = findViewById(R.id.etSearchKategori);

        itemNugget = findViewById(R.id.itemProdukNugget);
        itemSosis = findViewById(R.id.itemProdukSosis);
        itemKentang = findViewById(R.id.itemProdukKentang);
        itemDimsum = findViewById(R.id.itemProdukDimsum);

        Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                tampilkanProduk();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        findViewById(R.id.btnCariKategori).setOnClickListener(v -> {
            String keyword = Search.getText().toString().trim();

            if (keyword.isEmpty()) {
                Search.setError("Masukkan nama produk");
            } else {
                tampilkanProduk();
            }
        });

        findViewById(R.id.kategoriNugget).setOnClickListener(v ->
                pilihKategori("Nugget"));

        findViewById(R.id.kategoriSosis).setOnClickListener(v ->
                pilihKategori("Sosis"));

        findViewById(R.id.kategoriKentang).setOnClickListener(v ->
                pilihKategori("Kentang"));

        findViewById(R.id.kategoriDimsum).setOnClickListener(v ->
                pilihKategori("Dimsum"));

        itemNugget.setOnClickListener(v ->
                bukaDetail(
                        "nugget",
                        "Nugget Ayam",
                        35000,
                        R.drawable.nugget,
                        "Nugget",
                        "Nugget ayam dengan rasa gurih. Cocok untuk lauk makan maupun camilan."
                ));

        itemSosis.setOnClickListener(v ->
                bukaDetail(
                        "sosis",
                        "Sosis Sapi",
                        28000,
                        R.drawable.nugget,
                        "Sosis",
                        "Sosis sapi dengan rasa gurih yang cocok untuk berbagai hidangan."
                ));

        itemKentang.setOnClickListener(v ->
                bukaDetail(
                        "kentang",
                        "Kentang Goreng",
                        32000,
                        R.drawable.nugget,
                        "Kentang",
                        "Kentang goreng siap masak yang praktis disajikan sebagai camilan."
                ));

        itemDimsum.setOnClickListener(v ->
                bukaDetail(
                        "dimsum",
                        "Dimsum Ayam",
                        25000,
                        R.drawable.nugget,
                        "Dimsum",
                        "Dimsum ayam dengan rasa lezat yang mudah disajikan di rumah."
                ));

        findViewById(R.id.btnKategoriHome).setOnClickListener(v -> {
            Intent intent = new Intent(Kategori.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btnKategoriMenu).setOnClickListener(v ->
                Toast.makeText(this,
                        "Kamu sedang di halaman Kategori",
                        Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnKategoriKeranjang).setOnClickListener(v ->
                startActivity(new Intent(Kategori.this, Keranjang.class)));

        findViewById(R.id.btnKategoriPesanan).setOnClickListener(v ->
                Toast.makeText(this,
                        "Halaman Pesanan belum dibuat",
                        Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnKategoriProfil).setOnClickListener(v ->
                Toast.makeText(this,
                        "Halaman Profil belum dibuat",
                        Toast.LENGTH_SHORT).show());
    }

    private void pilihKategori(String kategori) {
        kategoriDipilih = kategori;
        tampilkanProduk();
    }

    private void tampilkanProduk() {
        String keyword = Search.getText().toString().trim().toLowerCase();

        aturTampilanProduk(
                itemNugget,
                "Nugget Ayam",
                "Nugget"
        );

        aturTampilanProduk(
                itemSosis,
                "Sosis Sapi",
                "Sosis"
        );

        aturTampilanProduk(
                itemKentang,
                "Kentang Goreng",
                "Kentang"
        );

        aturTampilanProduk(
                itemDimsum,
                "Dimsum Ayam",
                "Dimsum"
        );

        boolean adaProduk =
                itemNugget.getVisibility() == View.VISIBLE
                        || itemSosis.getVisibility() == View.VISIBLE
                        || itemKentang.getVisibility() == View.VISIBLE
                        || itemDimsum.getVisibility() == View.VISIBLE;

        if (!adaProduk) {
            Toast.makeText(this,
                    "Produk tidak ditemukan",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void aturTampilanProduk(
            View item, String nama, String kategori) {

        String keyword = Search.getText().toString().trim().toLowerCase();

        boolean sesuaiKategori =
                kategoriDipilih.equals("Semua")
                        || kategoriDipilih.equals(kategori);

        boolean sesuaiPencarian =
                keyword.isEmpty()
                        || nama.toLowerCase().contains(keyword)
                        || kategori.toLowerCase().contains(keyword);

        if (sesuaiKategori && sesuaiPencarian) {
            item.setVisibility(View.VISIBLE);
        } else {
            item.setVisibility(View.GONE);
        }
    }

    private void bukaDetail(
            String id, String nama, int harga, int gambar,
            String kategori, String deskripsi) {

        Intent intent = new Intent(Kategori.this, DetailProduk.class);

        intent.putExtra("idProduk", id);
        intent.putExtra("nama", nama);
        intent.putExtra("harga", harga);
        intent.putExtra("gambar", gambar);
        intent.putExtra("kategori", kategori);
        intent.putExtra("deskripsi", deskripsi);
        intent.putExtra("stok", "Tersedia");

        startActivity(intent);
    }
}