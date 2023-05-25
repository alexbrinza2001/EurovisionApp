package com.example.eurovisionapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContestAdapter extends RecyclerView.Adapter<ContestAdapter.ViewHolder> {
    private List<EurovisionContest> itemList;

    private List<Button> buttonList;

    private Context context;

    public List<Button> getButtonList() {
        return buttonList;
    }
    public ContestAdapter(List<EurovisionContest> itemList, Context c) {
        this.itemList = itemList;
        this.context = c;
        buttonList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contest_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EurovisionContest item = itemList.get(position);

        buttonList.add(holder.button);

        holder.city.setText(item.getCity());
        holder.year.setText(Integer.toString(item.getYear()));
        byte[] blobData = item.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(blobData, 0, blobData.length);
        holder.image.setImageBitmap(bitmap);

        holder.city.setTextColor(ContextCompat.getColor(context, R.color.white_opacity));
        holder.year.setTextColor(ContextCompat.getColor(context, R.color.white_opacity));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView city;
        public TextView year;

        public Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.contestImage);
            city = itemView.findViewById(R.id.contestCity);
            year = itemView.findViewById(R.id.contestYear);
            button = itemView.findViewById(R.id.contestButton);
        }
    }
}

