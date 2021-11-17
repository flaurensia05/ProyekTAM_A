package com.android.rsbandarlampung.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.android.rsbandarlampung.R;
import com.android.rsbandarlampung.detailActivity;
import com.android.rsbandarlampung.model.hospital;
import com.android.rsbandarlampung.model.rumahsakit;

public class bedAdapter extends RecyclerView.Adapter<bedAdapter.ViewHolder> {
    private List<rumahsakit> data;
    private Context c, ca;

    public bedAdapter(Context c, List data, Context ca) {
        this.c = c;
        this.data = data;
        this.ca = ca;
    }

    @NonNull
    @Override
    public bedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.list_item_hospitals, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull bedAdapter.ViewHolder holder, int position) {
        holder.name.setText(String.valueOf(position + 1) + ". " + data.get(position).getNama());
        holder.address.setText(data.get(position).getAlamat());
        holder.phone.setText(data.get(position).getTelp());
        holder.ket.setText("Kelas: " + data.get(position).getKelas());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ca, detailActivity.class);
                i.putExtra("id", data.get(position).getId());
                i.putExtra("nama", data.get(position).getNama());
                i.putExtra("alamat", data.get(position).getAlamat());
                i.putExtra("lat", data.get(position).getLat());
                i.putExtra("lon", data.get(position).getLon());
                i.putExtra("jenis", data.get(position).getJenis());
                i.putExtra("kelas", data.get(position).getKelas());
                i.putExtra("tenagamedis", data.get(position).getTenaga_medis());
                i.putExtra("perawat", data.get(position).getTenaga_perawat());
                i.putExtra("telp", data.get(position).getTelp());
                i.putExtra("kec", data.get(position).getId_kec());
                ca.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Button call, map, detail;
        TextView name, address, phone, ket, queue, bed_availability, status, update;
        LinearLayout lin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            address = itemView.findViewById(R.id.tvAddress);
            phone = itemView.findViewById(R.id.tvPhone);
            ket = itemView.findViewById(R.id.tvTimeUpdate);

        }
    }
}
