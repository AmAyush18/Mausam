package com.example.android.mausam;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MausamRVAdapter extends RecyclerView.Adapter<MausamRVAdapter.ViewHolder> {

    private Context context;
    private ArrayList<MausamRVModal> weatherRVModalArrayList;

    public MausamRVAdapter(Context context, ArrayList<MausamRVModal> weatherRVModalArrayList) {
        this.context = context;
        this.weatherRVModalArrayList = weatherRVModalArrayList;
    }

    @NonNull
    @Override
    public MausamRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.mausam_rv_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MausamRVAdapter.ViewHolder holder, int position) {

        MausamRVModal modal = weatherRVModalArrayList.get(position);
        holder.temperatureTV.setText(modal.getTemperature().concat("°C"));

        Picasso.get().load("http:".concat(modal.getIcon())).into(holder.conditionIV);
        holder.windTV.setText(modal.getWindSpeed().concat("KM/hr"));

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");

        try{
            Date t = input.parse(modal.getTime());
            holder.timeTV.setText(output.format(t));
        }catch(ParseException e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return weatherRVModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView windTV, temperatureTV, timeTV;
        private ImageView conditionIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            windTV = itemView.findViewById(R.id.idWindSpeed);
            temperatureTV = itemView.findViewById(R.id.idTemperature);
            timeTV = itemView.findViewById(R.id.idTvTime);
            conditionIV = itemView.findViewById(R.id.idIVCondition);
        }
    }
}
