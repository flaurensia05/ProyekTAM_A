package com.android.rsbandarlampung.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.rsbandarlampung.R;
import com.android.rsbandarlampung.model.kecamatan;;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class kecamatanAdapter extends RecyclerView.Adapter<kecamatanAdapter.ViewHolder> {
    private Context c;
    private List<kecamatan> data;
    private int lastPosition = -1;
    private click cl;
    public kecamatanAdapter(Context c, List data){
        this.c=c;
        this.data=data;
    }
    @NonNull
    @Override
    public kecamatanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.item_prov,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull kecamatanAdapter.ViewHolder holder, int position) {
        kecamatan kec = data.get(position);
        holder.kecamatan.setText(String.valueOf(position+1)+". "+kec.getNama_kec());

        if(position %2 !=0){
            holder.card.setCardBackgroundColor(Color.parseColor("#ffe1dd"));
        }
    }
    private void setAnimation(View vt, int pos){
        if(pos > lastPosition){
            Animation anim = AnimationUtils.loadAnimation(c, android.R.anim.slide_in_left);
            vt.startAnimation(anim);
            lastPosition = pos;
        }
        else{
            Animation anim = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
            vt.startAnimation(anim);
        }
    }
    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView kecamatan;
        private MaterialCardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kecamatan = itemView.findViewById(R.id.textProvinsi);
            card = itemView.findViewById(R.id.mycard);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(cl != null) cl.onItemClick(view,getAdapterPosition());
                }
            });
        }
    }
    public kecamatan getItem(int id){
        return data.get(id);
    }
    public void setClick(click cl){
        this.cl=cl;
    }
    public interface click{
        void onItemClick(View v, int Position);
    }
}
