package com.asadchattha.mediaplayer.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.asadchattha.mediaplayer.Model.MediaFileInfo;
import com.asadchattha.mediaplayer.R;

import java.io.File;
import java.util.List;


/**
 * Created by Mukesh on 12/20/2015.
 */
public class MediaRVAdapter extends RecyclerView.Adapter<MediaListRowHolder> {


    private List<MediaFileInfo> itemList;

    private Context mContext;

    public MediaRVAdapter(Context context, List<MediaFileInfo> itemList) {
        this.itemList = itemList;
        this.mContext = context;
    }

    @Override
    public MediaListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_list_item, null);
        MediaListRowHolder mh = new MediaListRowHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(MediaListRowHolder mediaListRowHolder, int i) {
        try {
            MediaFileInfo item = itemList.get(i);

            mediaListRowHolder.textViewVideoTitle.setText(Html.fromHtml(item.getFileName()));
            mediaListRowHolder.textViewVideoMemorySize.setText(Html.fromHtml(item.getFileSize()));    //TODO Test New functionality
            mediaListRowHolder.textViewVideoDuration.setText(Html.fromHtml(item.getVideoDuration()));
            mediaListRowHolder.textViewVideoWidthHeight.setText(Html.fromHtml(item.getVideoWidthHeight()));


            Uri uri = Uri.fromFile(new File(item.getFilePath()));
            Bitmap bmThumbnail = ThumbnailUtils.
                    extractThumbnail(ThumbnailUtils.createVideoThumbnail(item.getFilePath(),
                            MediaStore.Video.Thumbnails.MINI_KIND), 150, 95);
            if (bmThumbnail != null) {
                mediaListRowHolder.imageViewThumbnail.setImageBitmap(bmThumbnail);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return (null != itemList ? itemList.size() : 0);
    }
}