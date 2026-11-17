package com.example.frozenfood;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        Search = findViewById(R.id.etSearch);

        findViewById(R.id.btnSearch).setOnClickListener(v -> {
            String keyword = Search.getText().toString().trim();

            if (keyword.isEmpty()) {
                Search.setError("Masukkan nama produk");
            } else {
                Toast.makeText(
                        this,
                        "Mencari produk: " + keyword,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        findViewById(R.id.btnAddNugget).setOnClickListener(v -> {
            KeranjangHelper.tambahProduk(this, "nugget",
                    "Nugget Ayam", 35000, R.drawable.nugget, 1);

            Toast.makeText(this, "Nugget Ayam ditambahkan",
                    Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.btnAddSosis).setOnClickListener(v -> {
            KeranjangHelper.tambahProduk(this, "sosis",
                    "Sosis Sapi", 28000, R.drawable.nugget, 1);

            Toast.makeText(this, "Sosis Sapi ditambahkan",
                    Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.btnAddKentang).setOnClickListener(v -> {
            KeranjangHelper.tambahProduk(this, "kentang",
                    "Kentang Goreng", 32000, R.drawable.nugget, 1);

            Toast.makeText(this, "Kentang Goreng ditambahkan",
                    Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.btnAddDimsum).setOnClickListener(v -> {
            KeranjangHelper.tambahProduk(this, "dimsum",
                    "Dimsum Ayam", 25000, R.drawable.nugget, 1);

            Toast.makeText(this, "Dimsum Ayam ditambahkan",
                    Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.navHome).setOnClickListener(v ->
                Toast.makeText(this, "Kamu sedang di halaman Home", Toast.LENGTH_SHORT).show()
        );

        findViewById(R.id.navKategori).setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    Kategori.class
            );

            startActivity(intent);
        });

        findViewById(R.id.navKeranjang).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,
                    Keranjang.class));
        });

        findViewById(R.id.navPesanan).setOnClickListener(v ->
                Toast.makeText(this, "Halaman Pesanan belum dibuat", Toast.LENGTH_SHORT).show()
        );

        findViewById(R.id.navProfil).setOnClickListener(v ->
                Toast.makeText(this, "Halaman Profil belum dibuat", Toast.LENGTH_SHORT).show()
        );

        findViewById(R.id.itemNugget).setOnClickListener(v -> {
            bukaDetail("Nugget Ayam", 35000, R.drawable.nugget,
                    "Frozen Food",
                    "Nugget ayam dengan rasa gurih. Cocok untuk lauk makan maupun camilan.");
        });

        findViewById(R.id.itemSosis).setOnClickListener(v -> {
            bukaDetail("Sosis Sapi", 28000, R.drawable.nugget,
                    "Frozen Food",
                    "Sosis sapi dengan rasa gurih yang cocok untuk berbagai hidangan.");
        });

        findViewById(R.id.itemKentang).setOnClickListener(v -> {
            bukaDetail("Kentang Goreng", 32000, R.drawable.nugget,
                    "Frozen Food",
                    "Kentang goreng siap masak yang praktis disajikan sebagai camilan.");
        });

        findViewById(R.id.itemDimsum).setOnClickListener(v -> {
            bukaDetail("Dimsum Ayam", 25000, R.drawable.nugget,
                    "Frozen Food",
                    "Dimsum ayam dengan rasa lezat yang mudah disajikan di rumah.");
        });
    }

    private void showCategory(String category) {
        Toast.makeText(
                this,
                "Kategori " + category + " dipilih",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void addToCart(String product) {
        Toast.makeText(
                this,
                product + " ditambahkan ke keranjang",
                Toast.LENGTH_SHORT
        ).show();
    }
    private void bukaDetail(String nama, int harga, int gambar,
                            String kategori, String deskripsi) {

        Intent intent = new Intent(MainActivity.this, DetailProduk.class);

        intent.putExtra("nama", nama);
        intent.putExtra("harga", harga);
        intent.putExtra("gambar", gambar);
        intent.putExtra("kategori", kategori);
        intent.putExtra("deskripsi", deskripsi);
        intent.putExtra("stok", "Tersedia");

        startActivity(intent);
    }
}