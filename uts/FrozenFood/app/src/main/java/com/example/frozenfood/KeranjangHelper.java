package com.example.frozenfood;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

public class KeranjangHelper {

    private static final String PREF_NAME = "keranjang_frozenbox";

    public static JSONObject ambilKeranjang(Context context) {
        SharedPreferences pref = context.getSharedPreferences(
                PREF_NAME, Context.MODE_PRIVATE
        );

        String data = pref.getString("produk", "{}");

        try {
            return new JSONObject(data);
        } catch (Exception e) {
            return new JSONObject();
        }
    }

    public static void tambahProduk(Context context, String id,
                                    String nama, int harga,
                                    int gambar, int jumlah) {

        JSONObject keranjang = ambilKeranjang(context);

        try {
            JSONObject produk = keranjang.optJSONObject(id);

            if (produk == null) {
                produk = new JSONObject();
                produk.put("nama", nama);
                produk.put("harga", harga);
                produk.put("gambar", gambar);
                produk.put("jumlah", jumlah);
            } else {
                int jumlahLama = produk.getInt("jumlah");
                produk.put("jumlah", jumlahLama + jumlah);
            }

            keranjang.put(id, produk);

            simpanKeranjang(context, keranjang);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ubahJumlah(Context context, String id, int jumlah) {
        JSONObject keranjang = ambilKeranjang(context);

        if (jumlah <= 0) {
            keranjang.remove(id);
        } else {
            try {
                JSONObject produk = keranjang.getJSONObject(id);
                produk.put("jumlah", jumlah);
                keranjang.put(id, produk);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        simpanKeranjang(context, keranjang);
    }

    public static void hapusProduk(Context context, String id) {
        JSONObject keranjang = ambilKeranjang(context);
        keranjang.remove(id);
        simpanKeranjang(context, keranjang);
    }

    private static void simpanKeranjang(Context context, JSONObject data) {
        SharedPreferences pref = context.getSharedPreferences(
                PREF_NAME, Context.MODE_PRIVATE
        );

        pref.edit().putString("produk", data.toString()).apply();
    }
}