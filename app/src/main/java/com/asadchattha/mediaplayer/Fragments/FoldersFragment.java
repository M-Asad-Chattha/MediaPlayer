package com.asadchattha.mediaplayer.Fragments;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.asadchattha.mediaplayer.Adapters.AdapterFolders;
import com.asadchattha.mediaplayer.Model.FolderModel;
import com.asadchattha.mediaplayer.R;
import com.asadchattha.mediaplayer.VideosActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoldersFragment extends Fragment implements AdapterFolders.OnFolderListener {

    public static ArrayList<FolderModel> foldersList = new ArrayList<>();
    boolean boolean_folder;

    private AdapterFolders adapterFolders;
    private RecyclerView foldersRecyclerView;
    private RecyclerView.LayoutManager layoutManager;

    public FoldersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_folders, container, false);

        foldersRecyclerView = view.findViewById(R.id.recycler_view_folders);

        loadVideosFoldersData();

        return view;
    }


    public void loadVideosFoldersData() {
        foldersList.clear();

        int int_position = 0;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

        String absolutePathOfVideo = null;
        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME};

        final String orderBy = MediaStore.Video.Media.DATE_TAKEN;
        cursor = getContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfVideo = cursor.getString(column_index_data);
            Log.e("Column", absolutePathOfVideo);
            Log.e("Folder", cursor.getString(column_index_folder_name));

            for (int i = 0; i < foldersList.size(); i++) {
                if (foldersList.get(i).getFolderName().equals(cursor.getString(column_index_folder_name))) {
                    boolean_folder = true;
                    int_position = i;
                    break;
                } else {
                    boolean_folder = false;
                }
            }


            if (boolean_folder) {
                ArrayList<String> folderVideosPathList = new ArrayList<>();
                folderVideosPathList.addAll(foldersList.get(int_position).getFolderVideosPathsList());
                folderVideosPathList.add(absolutePathOfVideo);
                foldersList.get(int_position).setFolderVideosPathsList(folderVideosPathList);

            } else {
                ArrayList<String> al_path = new ArrayList<>();
                al_path.add(absolutePathOfVideo);
                FolderModel obj_model = new FolderModel();
                obj_model.setFolderName(cursor.getString(column_index_folder_name));
                obj_model.setFolderVideosPathsList(al_path);

                foldersList.add(obj_model);
            }

        }

        for (int i = 0; i < foldersList.size(); i++) {
            Log.e("FOLDER", foldersList.get(i).getFolderName());
            for (int j = 0; j < foldersList.get(i).getFolderVideosPathsList().size(); j++) {
                Log.e("FILE", foldersList.get(i).getFolderVideosPathsList().get(j));
            }
        }

        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        adapterFolders = new AdapterFolders(getContext(), foldersList, this);
        foldersRecyclerView.setLayoutManager(layoutManager);
        foldersRecyclerView.setAdapter(adapterFolders);
    }


    @Override
    public void onFolderClick(int position) {
        Intent intent = new Intent(getContext(), VideosActivity.class);
        startActivity(intent);

        Toast.makeText(getContext(), foldersList.get(position).getFolderName(), Toast.LENGTH_SHORT).show();
    }
}
