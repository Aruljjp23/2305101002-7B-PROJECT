package com.example.frozenfood;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.Iterator;

public class Checkout extends AppCompatActivity {

    private EditText etNamaCustomer;
    private EditText etNomorTelepon;
    private EditText etAlamat;

    private TextView tvCheckoutSubtotal;
    private TextView tvCheckoutTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        getWindow().setStatusBarColor(
                android.graphics.Color.rgb(247, 248, 245)
        );

        getWindow().setNavigationBarColor(
                android.graphics.Color.rgb(247, 248, 245)
        );

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR |
                        View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        );

        etNamaCustomer = findViewById(R.id.etNamaCustomer);
        etNomorTelepon = findViewById(R.id.etNomorTelepon);
        etAlamat = findViewById(R.id.etAlamat);

        tvCheckoutSubtotal = findViewById(R.id.tvCheckoutSubtotal);
        tvCheckoutTotal = findViewById(R.id.tvCheckoutTotal);

        tampilkanRingkasan();

        findViewById(R.id.btnKembaliCheckout).setOnClickListener(v ->
                finish()
        );

        findViewById(R.id.btnLanjutPembayaran).setOnClickListener(v -> {

            String nama = etNamaCustomer.getText().toString().trim();
            String telepon = etNomorTelepon.getText().toString().trim();
            String alamat = etAlamat.getText().toString().trim();

            if (nama.isEmpty()) {
                etNamaCustomer.setError("Nama penerima harus diisi");
                etNamaCustomer.requestFocus();
                return;
            }

            if (telepon.isEmpty()) {
                etNomorTelepon.setError("Nomor telepon harus diisi");
                etNomorTelepon.requestFocus();
                return;
            }

            if (alamat.isEmpty()) {
                etAlamat.setError("Alamat harus diisi");
                etAlamat.requestFocus();
                return;
            }

            Toast.makeText(this,
                    "Informasi penerima berhasil diisi",
                    Toast.LENGTH_SHORT).show();
        });
    }

    private void tampilkanRingkasan() {

        JSONObject keranjang = KeranjangHelper.ambilKeranjang(this);

        int total = 0;
        Iterator<String> keys = keranjang.keys();

        while (keys.hasNext()) {
            String id = keys.next();
            JSONObject produk = keranjang.optJSONObject(id);

            if (produk != null) {
                int harga = produk.optInt("harga", 0);
                int jumlah = produk.optInt("jumlah", 0);

                total += harga * jumlah;
            }
        }

        tvCheckoutSubtotal.setText(formatRupiah(total));
        tvCheckoutTotal.setText(formatRupiah(total));
    }

    private String formatRupiah(int angka) {
        return "Rp" + String.format("%,d", angka).replace(',', '.');
    }
}