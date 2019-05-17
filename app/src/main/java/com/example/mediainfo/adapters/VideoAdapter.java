package com.example.mediainfo.adapters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mediainfo.R;
import com.example.mediainfo.models.Video;
import com.example.mediainfo.utils.URLUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter  extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    List<Video> videos;
    CustomClickListner mClickListner;

    public interface CustomClickListner {
        void onClick(Video video);
    }

    public VideoAdapter(List<Video> videos, CustomClickListner mClickListner){
        this.videos = videos;
        this.mClickListner = mClickListner;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VideoViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.video_list_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder videoViewHolder, int i) {
        videoViewHolder.bind(videos.get(i));
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mImageView;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_video_list_item);
            mImageView.setOnClickListener(this);
        }

        public void bind(Video video){
            Picasso.get().load(getYoutubeImageUrl(video.getKey()))
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(mImageView);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mClickListner.onClick(videos.get(position));
        }
    }

    public static String getYoutubeImageUrl(String key) {
        return "http://img.youtube.com/vi/"+key+"/mqdefault.jpg";
    }

    public static Uri getYoutubeVideoUrl(String key) {
        return Uri.parse("https://www.youtube.com/watch?v="+key);
    }
}