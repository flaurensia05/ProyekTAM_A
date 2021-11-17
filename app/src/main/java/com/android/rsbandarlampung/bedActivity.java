package com.android.rsbandarlampung;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.google.android.material.tabs.TabLayout;
import com.android.rsbandarlampung.adapter.bedAdapter;
import com.android.rsbandarlampung.model.*;
import java.util.ArrayList;
import java.util.List;


public class bedActivity extends AppCompatActivity {
    bedAdapter umumAdapter, rsiaAdapter;

    TabLayout tb;
    TextView nama;
    String kecId;
    List<rumahsakit> dataAll;
    List<rumahsakit> dataTemp;
    List<rumahsakit> data_umum;
    List<rumahsakit> data_rsia;
    RecyclerView recycler;
    ImageView bedend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed);
        tb = findViewById(R.id.tablayout);
        recycler = findViewById(R.id.recyclerBed);
        Intent in = getIntent();
        kecId = in.getStringExtra("kecId");
        nama = findViewById(R.id.bednama);
        bedend = findViewById(R.id.bednend);
        nama.setText(in.getStringExtra("kecNama"));
        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(lm);
        dataTemp = new ArrayList<>();
        dataAll = new ArrayList<>();
        data_umum = new ArrayList<>();
        data_rsia = new ArrayList<>();

        getdataRumahSakitByKecamatan(in.getStringExtra("kecNama"));
        getDataUmum();



        bedend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        getDataUmum();
                        recycler.setAdapter(umumAdapter);
                        break;
                    case 1:
                        getDataRSIA();
                        recycler.setAdapter(rsiaAdapter);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    public void  getdataRumahSakitByKecamatan(String namaKecamatan){
        createDataMaster();
        dataTemp.clear();
        for (int i = 0; i < dataAll.size(); i++) {
            if (dataAll.get(i).getId_kec().equalsIgnoreCase(namaKecamatan)){
             dataTemp.add(dataAll.get(i));
            }
        }
    }

    public void getDataUmum() {
        data_umum.clear();
        for (int i = 0; i < dataTemp.size(); i++) {
            if (dataTemp.get(i).getJenis().equalsIgnoreCase("Umum")){
                data_umum.add(dataTemp.get(i));
            }
        }

        umumAdapter = new bedAdapter(getApplicationContext(), data_umum, bedActivity.this);
        recycler.setVisibility(View.VISIBLE);
        if (data_umum.size() > 0) {
            recycler.setAdapter(umumAdapter);
        } else {
            new SweetAlertDialog(bedActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Maaf data RS Umum tidak ditemukan :(")
                    .setContentText("Silahkan cari lokasi lain")
                    .show();
        }
    }

    public void getDataRSIA() {
        data_rsia.clear();

        for (int i = 0; i < dataTemp.size(); i++) {
            if (dataTemp.get(i).getJenis().equalsIgnoreCase("RSIA")){
                data_rsia.add(dataTemp.get(i));
            }
        }
        rsiaAdapter = new bedAdapter(getApplicationContext(), data_rsia, bedActivity.this);
        recycler.setVisibility(View.VISIBLE);
        if (data_rsia.size() > 0) {
            recycler.setAdapter(rsiaAdapter);
        } else {
            new SweetAlertDialog(bedActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Maaf data RSIA tidak ditemukan :(")
                    .setContentText("Silahkan cari lokasi lain")
                    .show();
        }
    }

    public void createDataMaster() {
        dataAll.clear();
        dataAll.add(new rumahsakit("1", "RSUD Abdul Moeloek",
                "Jl. Dr. Rivai No.6", "-5.4030677", "105.2564366", "Umum", "B", "387", "1311", "0721-703312",
                "Tanjung Karang Pusat"));
        dataAll.add(new rumahsakit("2", "RSUD A Dadi Tjokrodipo", "Jl. Basuki Rahmat No.73",
                "-5.4362697", "105.2510163", "Umum", "C", "112", "117", "0721-470177", "Teluk Betung Utara"));
        dataAll.add(new rumahsakit("3", "RS Umum Bumi Waras", "Jl. Wolter Monginsidi No.235", "-5.4252177", "105.2489175", "Umum", "C", "51", "195", "0721-257926", "Bumi Waras"));
        dataAll.add(new rumahsakit("4", "RS Umum Advent Bandar Lampung", "Jl. Teuku Umar No.48, Kedaton", "-5.3920376", "105.2599564", "Umum", "C", "47", "82", "0721-703459", "Kedaton"));
        dataAll.add(new rumahsakit("5", "RS Umum Imanuel Way Halim", "Jl. Soekarno Hatta No.1,", " -5.3859958", " 105.285567", "Umum", "B", "48", "196", "0721-704900", "Sukarame"));
        dataAll.add(new rumahsakit("6", "RS Umum Pertamina Bintang Amin", "Jl. Pramuka No.27", "-5.3789077", "105.2174542", "Umum", "C", "39", "0", "0721-  273601", "Kemiling"));
        dataAll.add(new rumahsakit("6", "RS Bhayangkara Polda Lampung", "Jl. Pramuka No.88", "-5.3743377", "105.2220461", "Umum", "C", "19", "2", "0721- 706402", "Rajabasa"));
        dataAll.add(new rumahsakit("7", "RS Umum Urip Sumoharjo", "Jl. Urip Sumoharjo No.200, Sukarame", " -5.3912809", "105.274264", "Umum", "B", "50", "11", "0721-771321", "Way Halim"));
        dataAll.add(new rumahsakit("7", "RS DKT Lampung", "Jl. dr. Ahmad Rivai No.7,  Penengahan", "-5.4015242", "105.2562127", "Umum", "C", "11", "108", "82177052096", "Tanjung Karang Pusat"));
        dataAll.add(new rumahsakit("8", "RS Umum Graha Husada", "Jl. Gajah Mada No.6 GH, Tj. Agung Raya, Kedamaian", "-5.4132856", "105.2655824", "Umum", "C", "42", "78", "0721- 240000", "Kedamaian"));
        dataAll.add(new rumahsakit("9", "RSIA Restu Bunda Lampung", "Jl. KH. Hasyim Ashari No.73 Gedung Pakuon Teluk Belitung Selatan", "-5.4476368", "105.2539997", "RSIA", "C", "12", "9", "484185", "Teluk Belitung Selatan"));
        dataAll.add(new rumahsakit("10", "RSIA Bunda Assyifa", "Jl. Dr. Susilo No.54 Pahoman", "-5.4299838", "105.2689436", "RSIA", "C", "10", "0", "0721-259259", "Enggal"));
        dataAll.add(new rumahsakit("11", "RSIA Puri Betik Hati", "Jl. Pajajaran No.109, Jagabaya II, Way Halim", "-5.3941389", "105.2638773", "RSIA", "C", "12", "16", "0721-787799", "Way Halim"));
        dataAll.add(new rumahsakit("12", "RSIA Mutiara PUTRI", "Jl. Hos Cokroaminoto No.96, Tanjung Karang", "-5.4229323", "105.260269", "RSIA", "C", "19", "0", "0721- 252519", "Enggal"));
        dataAll.add(new rumahsakit("13", "RSIA Santa Anna", "Jl. Hasanuddin No.27 Kupang Kota, Teluk  Betung Utara", "-5.4424726", "105.2653213", "RSIA", "C", "14", "0", "0721-482424", "Teluk Betung Utara"));
        dataAll.add(new rumahsakit("14", "RS Bersalin Kosasih", "Jl. Tanjung Pura  No.754, Pidada, Panjang", "-5.4711579", "105.320622", "RSIA", "C", "9", "3", "+6272131754", "Panjang"));
        dataAll.add(new rumahsakit("15", "RSIA Anugerah Medika",  "Jl. Tulang Bawang No.21 - 23, Enggal", "-5.417667", "105.2586857", "RSIA", "C", "16", "19", "0721 - 240488", "Enggal"));
        dataAll.add(new rumahsakit("16", "RSIA Sinta", "Jl. Imam Bonjol No.512,  Langkapura", "-5.3923727", "105.2242693", "RSIA", "C", "16", "0", "0721 - 266115", "Langkapura"));
        dataAll.add(new rumahsakit("17", "RS Ibu dan Anak Belleza", "Jl. Sultan H., Labuhan Ratu, Kec. Kedaton", "-5.3806833", "105.2562621", "RSIA", "C", "0", "0", "0721 - 773333", "Kedaton"));
    }
}