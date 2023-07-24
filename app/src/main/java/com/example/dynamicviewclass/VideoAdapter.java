package com.example.dynamicviewclass;

import static com.bumptech.glide.load.resource.bitmap.VideoDecoder.FRAME_OPTION;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.example.myview.R;

import java.security.MessageDigest;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    public Context context;
    public List<String> videoList;
    public int videoNum;
    public View.OnClickListener listener;
    public View.OnClickListener clickListener;
    public View.OnClickListener deleteClick;
    public boolean netVideo;


    public VideoAdapter(Context context, List<String> videoList, int videoNum, View.OnClickListener listener, View.OnClickListener clickListener, View.OnClickListener deleteClick, boolean netVideo) {
        this.context = context;
        this.videoList = videoList;
        this.videoNum = videoNum;
        this.listener = listener;
        this.clickListener = clickListener;
        this.deleteClick = deleteClick;
        this.netVideo = netVideo;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_video_picker, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        if (position == videoList.size()) {
            holder.videoPic.setVisibility(View.GONE);
            holder.delete.setVisibility(View.GONE);
            holder.imageView.setImageResource(R.drawable.add_video);
            holder.imageView.setOnClickListener(listener);
        } else {
            holder.videoPic.setVisibility(View.VISIBLE);
            holder.item.setTag(videoList.get(position));
            holder.item.setOnClickListener(clickListener);
            if (deleteClick == null) {
                holder.delete.setVisibility(View.GONE);
            } else {
                holder.delete.setVisibility(View.VISIBLE);
            }
//            Glide.with(context)
//                    .load(Uri.fromFile(new File(videoList.get(position))))
//                    .into(holder.imageView);
            loadVideoScreenshot(context, videoList.get(position), holder.imageView, 0);
            holder.delete.setTag(videoList.get(position));
            holder.delete.setOnClickListener(deleteClick);
        }
    }

    /**
     * 使用Glide方式获取视频某一帧
     * @param context 上下文
     * @param uri 视频地址
     * @param imageView 设置image
     * @param frameTimeMicros 获取某一时间帧.
     */
    public static void loadVideoScreenshot(final Context context, String uri, ImageView imageView, long frameTimeMicros) {
        RequestOptions requestOptions = RequestOptions.frameOf(frameTimeMicros);
        requestOptions.set(FRAME_OPTION, MediaMetadataRetriever.OPTION_CLOSEST);
        requestOptions.transform(new BitmapTransformation() {
            @Override
            protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
                return toTransform;
            }
            @Override
            public void updateDiskCacheKey(MessageDigest messageDigest) {
                try {
                    messageDigest.update((context.getPackageName() + "RotateTransform").getBytes("utf-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Glide.with(context).load(uri).apply(requestOptions).into(imageView);
    }

    @Override
    public int getItemCount() {
        int num = videoList == null ? 0 : videoList.size();
        if (listener == null) {
            return num;
        }
        if (videoNum > num) {
            return num + 1;
        } else {
            return num;
        }
    }

    public void upData(List<String> videoList) {
        this.videoList = videoList;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private FrameLayout item;
        private ImageView imageView, videoPic, delete;

        private MyViewHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            imageView = itemView.findViewById(R.id.imageView);
            videoPic = itemView.findViewById(R.id.video_picture);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}