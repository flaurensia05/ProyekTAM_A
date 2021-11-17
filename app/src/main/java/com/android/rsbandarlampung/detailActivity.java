package com.android.rsbandarlampung;

import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.facebook.shimmer.ShimmerFrameLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.rsbandarlampung.model.detail_Covid;

import java.util.ArrayList;
import java.util.List;
import com.android.rsbandarlampung.adapter.detailAdapter;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class detailActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView name, address, phone, ket, jenis, kelas, medis, perawat;
    GoogleMap googleMaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupToolbar();

        //show maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        // item
        Intent in = getIntent();
        name = findViewById(R.id.tvName);
        address = findViewById(R.id.tvAddress);
        phone = findViewById(R.id.tvPhone);
        ket = findViewById(R.id.tvTimeUpdate);
        jenis = findViewById(R.id.tvJenis);
        kelas = findViewById(R.id.tvKelas);
        medis = findViewById(R.id.tvMedis);
        perawat = findViewById(R.id.tvPerawat);
        name.setText(in.getStringExtra("nama"));
        address.setText(in.getStringExtra("alamat"));
        phone.setText(in.getStringExtra("telp"));
        ket.setText("Kelas: " + in.getStringExtra("kelas"));
        jenis.setText(in.getStringExtra("jenis"));
        kelas.setText(in.getStringExtra("kelas"));
        medis.setText(in.getStringExtra("tenagamedis"));
        perawat.setText(in.getStringExtra("perawat"));

        ((FloatingActionButton)findViewById(R.id.fabPhone)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_DIAL);
                    i.setData(Uri.parse("tel:"+in.getStringExtra("telp")));
                    startActivity(i);
                }
            });
    }

    private void setupToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Intent in = getIntent();
        double lat = Double.parseDouble(in.getStringExtra("lat"));
        double lon = Double.parseDouble(in.getStringExtra("lon"));

        LatLng rsLocation = new LatLng(lat,lon);
        googleMap.addMarker(new MarkerOptions().position(rsLocation)
                .title(in.getStringExtra("nama")));
        CameraUpdate center = CameraUpdateFactory.newLatLng(rsLocation);
        CameraUpdate zoom = CameraUpdateFactory.newLatLngZoom(rsLocation, 15);
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);
    }

}