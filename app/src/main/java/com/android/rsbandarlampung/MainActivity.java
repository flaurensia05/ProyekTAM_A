package com.android.rsbandarlampung;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.facebook.shimmer.ShimmerFrameLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.rsbandarlampung.model.*;
import com.android.rsbandarlampung.adapter.*;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tapp;
    RecyclerView recyclerProp;
    NestedScrollView scroll;
    ConstraintLayout cons;
    ShimmerFrameLayout shimmer;
    Button prov;

    List<kecamatan> dataKecamatan;
    kecamatanAdapter kecad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cons = findViewById(R.id.cons);
        tapp = findViewById(R.id.tapp);
        shimmer = findViewById(R.id.shimmer1);
        prov = findViewById(R.id.text_Provinsi);
        scroll = findViewById(R.id.scroll);
        recyclerProp = findViewById(R.id.recycler_Provinsi);
        tapp.setPaintFlags(tapp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        LinearLayoutManager lm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerProp.setLayoutManager(lm);

        tapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,aboutActivity.class);
                startActivity(i);
            }
        });
        getKecamatan();
    }
    public void getKecamatan() {
        dataKecamatan = new ArrayList<>();
        csllDataKecamatan();
        recyclerProp.setVisibility(View.VISIBLE);
        kecad = new kecamatanAdapter(getApplicationContext(),dataKecamatan);
        recyclerProp.setVisibility(View.VISIBLE);
        recyclerProp.setAdapter(kecad);
        kecad.setClick(new kecamatanAdapter.click() {
            @Override
            public void onItemClick(View v, int Position) {
                Intent i = new Intent(MainActivity.this,bedActivity.class);
                i.putExtra("kecId",dataKecamatan.get(Position).getId_kec());
                i.putExtra("kecNama",dataKecamatan.get(Position).getNama_kec());
                startActivity(i);
              }
        });
    }

    public void csllDataKecamatan(){
        dataKecamatan.add(new kecamatan("1", "Bumi Waras"));
        dataKecamatan.add(new kecamatan("2", "Langkapura"));
        dataKecamatan.add(new kecamatan("3", "Tanjung Karang Pusat"));
        dataKecamatan.add(new kecamatan("4", "Enggal"));
        dataKecamatan.add(new kecamatan("5", "Panjang"));
        dataKecamatan.add(new kecamatan("6", "Tanjung Karang Timur"));
        dataKecamatan.add(new kecamatan("7", "Kedamaian"));
        dataKecamatan.add(new kecamatan("8", "Rajabasa"));
        dataKecamatan.add(new kecamatan("9", "Tanjung Senang"));
        dataKecamatan.add(new kecamatan("10", "Kedaton"));
        dataKecamatan.add(new kecamatan("11", "Sukabumi"));
        dataKecamatan.add(new kecamatan("12", "Teluk betung barat"));
        dataKecamatan.add(new kecamatan("13", "Kemiling"));
        dataKecamatan.add(new kecamatan("14", "Sukarame"));
        dataKecamatan.add(new kecamatan("15", "Teluk betung selatan"));
        dataKecamatan.add(new kecamatan("16", "Teluk betung utara"));
        dataKecamatan.add(new kecamatan("17", "Way Halim"));
        dataKecamatan.add(new kecamatan("18", "Labuhan Ratu"));
        dataKecamatan.add(new kecamatan("19", "Tanjung karang barat"));
        dataKecamatan.add(new kecamatan("20", "Teluk betung timur"));
    }
}