package com.asadchattha.mediaplayer.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.asadchattha.mediaplayer.R;


/**
 * Created by Mukesh on 12/20/2015.
 */
public class MediaListRowHolder extends RecyclerView.ViewHolder {
    protected ImageView imageViewThumbnail;
    protected TextView textViewVideoTitle;
    protected TextView textViewVideoWidthHeight;
    protected TextView textViewVideoMemorySize;
    protected TextView textViewVideoDuration;

    public MediaListRowHolder(View view) {
        super(view);
        this.imageViewThumbnail = view.findViewById(R.id.image_view_thumbnail);
        this.textViewVideoTitle = view.findViewById(R.id.title);
        this.textViewVideoWidthHeight = view.findViewById(R.id.text_view_videoWidthHeight);
        this.textViewVideoMemorySize = view.findViewById(R.id.text_view_videoMemorySize);
        this.textViewVideoDuration = view.findViewById(R.id.text_view_videoDuration);
    }
}
