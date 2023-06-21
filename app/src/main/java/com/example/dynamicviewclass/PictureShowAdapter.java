package com.example.dynamicviewclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myview.R;

import java.util.List;

public class PictureShowAdapter extends RecyclerView.Adapter<PictureShowAdapter.MyHolder> {

    public Context context;
    public List<String> pictureList;
    public int pictureNum;
    public View.OnClickListener listener;
    public View.OnClickListener deleteClick;
    public boolean netPicture;

    public PictureShowAdapter(Context context, List<String> pictureList, int pictureNum, View.OnClickListener listener, View.OnClickListener deleteClick, boolean netPicture) {
        this.context = context;
        this.pictureList = pictureList;
        this.pictureNum = pictureNum;
        this.listener = listener;
        this.deleteClick = deleteClick;
        this.netPicture = netPicture;
    }

    public void update(List<String> pictureList) {
        this.pictureList = pictureList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_show_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        if (position == pictureList.size()) {
            holder.delete.setVisibility(View.GONE);
            holder.picture.setImageResource(R.drawable.icon_image_add_new);
            holder.picture.setOnClickListener(listener);
        } else {
            if (deleteClick == null) {
                holder.picture.setOnClickListener(null);
                holder.delete.setVisibility(View.GONE);
                Glide.with(context)
                        .load(pictureList.get(position))
                        .into(holder.picture);
                holder.delete.setTag(pictureList.get(position));
                holder.delete.setOnClickListener(deleteClick);
            } else {
                holder.picture.setOnClickListener(null);
                holder.delete.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(pictureList.get(position))
                        .into(holder.picture);
                holder.delete.setTag(pictureList.get(position));
                holder.delete.setOnClickListener(deleteClick);
            }
        }
    }

    @Override
    public int getItemCount() {
        int num = pictureList == null ? 0 : pictureList.size();
        if (listener == null) {
            return num;
        }
        if (pictureNum > num) {
            return num + 1;
        } else {
            return num;
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {

        public ImageView picture, delete;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.picture);
            delete = itemView.findViewById(R.id.delete);
        }
    }

}