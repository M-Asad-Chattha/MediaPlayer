package com.asadchattha.mediaplayer.Adapters;

/**
 * Created by deepshikha on 3/3/17.
 */

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asadchattha.mediaplayer.Model.FolderModel;
import com.asadchattha.mediaplayer.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;


public class AdapterFolders extends RecyclerView.Adapter<AdapterFolders.ViewHolder> {

    private Context context;
    private ArrayList<FolderModel> foldersList;
    private OnFolderListener onFolderListener;

    public AdapterFolders(Context context, ArrayList<FolderModel> foldersList, OnFolderListener onFolderListener) {
        this.foldersList = foldersList;
        this.context = context;
        this.onFolderListener = onFolderListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.folder_list_item, parent, false);
        return new ViewHolder(view, onFolderListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String folderItemsNumber;

        if (foldersList.get(position).getFolderVideosPathsList().size() == 1) {
            folderItemsNumber = foldersList.get(position).getFolderVideosPathsList().size() + " video";
        } else {
            folderItemsNumber = foldersList.get(position).getFolderVideosPathsList().size() + " videos";
        }

        holder.textViewFolderName.setText(foldersList.get(position).getFolderName());
        holder.textViewFolderItemsNumber.setText(folderItemsNumber);
    }

    @Override
    public int getItemCount() {
        return foldersList.size();
    }


        /*Glide.with(context).load("file://" + foldersList.get(position).getFolderVideosPathsList().get(0))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(viewHolder.iv_image);*/



    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textViewFolderName, textViewFolderItemsNumber;
        private OnFolderListener onFolderListener;

        public ViewHolder(@NonNull View itemView, OnFolderListener onFolderListener) {
            super(itemView);
            textViewFolderName = itemView.findViewById(R.id.text_view_folder_name);
            textViewFolderItemsNumber = itemView.findViewById(R.id.text_view_folder_items_number);
            this.onFolderListener = onFolderListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onFolderListener.onFolderClick(getAdapterPosition());
        }
    }


    public interface OnFolderListener {
        void onFolderClick(int position);
    }

}
