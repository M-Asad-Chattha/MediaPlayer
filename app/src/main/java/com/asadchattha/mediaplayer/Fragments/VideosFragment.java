package com.asadchattha.mediaplayer.Fragments;


import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.asadchattha.mediaplayer.Adapters.MediaRVAdapter;
import com.asadchattha.mediaplayer.Model.MediaFileInfo;
import com.asadchattha.mediaplayer.R;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideosFragment extends Fragment {

    /**
     * Cursor used to access the results from querying for images on the SD card.
     */
    private Cursor cursor;
    /*
     * Column index for the Thumbnails Image IDs.
     */
    private int columnIndex;
    private static final String TAG = "RecyclerViewExample";

    private List<MediaFileInfo> mediaList = new ArrayList<MediaFileInfo>();

    private RecyclerView mRecyclerView;

    private MediaRVAdapter adapter;
    private KProgressHUD progressHUD;

    public VideosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_videos, container, false);

        /* Initialize recyclerview */
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressHUD = KProgressHUD.create(getContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
        new MediaAsyncTask().execute("video");

        return view;
    }

    private void parseAllVideo() {
        try {

            int numberOfVideos;
            String videoName;
            String videoSize;
            int videoDuration;
            String videoWidth;
            String videoHeight;

            String[] thumbColumns = {MediaStore.Video.Thumbnails.DATA,
                    MediaStore.Video.Thumbnails.VIDEO_ID};

            String[] projection = {MediaStore.Video.Media._ID,
                    MediaStore.Video.Media.DATA,
                    MediaStore.Video.Media.DISPLAY_NAME,
                    MediaStore.Video.Media.SIZE,
                    MediaStore.Video.Media.DURATION,
                    MediaStore.Video.Media.WIDTH,
                    MediaStore.Video.Media.HEIGHT};

            Cursor videoCursor = getContext().getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    projection, null, null, null);
            numberOfVideos = videoCursor.getCount();

            Log.i("No of video", "" + numberOfVideos);

            for (int i = 0; i < numberOfVideos; i++) {
                MediaFileInfo mediaFileInfo = new MediaFileInfo();

                int videoNameIndex = videoCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
                int videoSizeIndex = videoCursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);
                int videoDurationIndex = videoCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION);

                int videoWidthIndex = videoCursor.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH);
                int videoHeightIndex = videoCursor.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT);




                videoCursor.moveToPosition(i);

                videoName = videoCursor.getString(videoNameIndex);

                /* Video Storage Size */
                DecimalFormat videoSizeformat = new DecimalFormat("0.00");
                videoSize = videoSizeformat.format((videoCursor.getDouble(videoSizeIndex)) / (1024 * 1024)) + " MB";  //TODO Test New functionality

                /* Video Duration */
                videoDuration = videoCursor.getInt(videoDurationIndex)/1000;  //TODO Nedd to set seconds via
                mediaFileInfo.setVideoDuration(videoDuration);


                /* Video Width & Height */
                videoWidth = Integer.toString(videoCursor.getInt(videoWidthIndex));
                videoHeight = Integer.toString(videoCursor.getInt(videoHeightIndex));
                mediaFileInfo.setVideoWidthHeight(videoWidth+"x"+videoHeight);

                mediaFileInfo.setFileName(videoName);
                mediaFileInfo.setFileSize(videoSize);   //TODO Test New functionality

                int column_index = videoCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                videoCursor.moveToPosition(i);
                String filepath = videoCursor.getString(column_index);

                /* Experiment New feature to get File Parent Folder name*/
                int videPathColumnIndex = videoCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
                String videoPath = videoCursor.getString(videPathColumnIndex);
                File file = new File(videoPath);
                String dir = file.getParent();


                mediaFileInfo.setFilePath(filepath);
                mediaFileInfo.setFileType("video"); //TODO Redundant, No need to specify type
                mediaList.add(mediaFileInfo);


            }

            videoCursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class MediaAsyncTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
            //setProgressBarIndeterminateVisibility(true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            int result;

            try {
                mediaList = new ArrayList<>();
                parseAllVideo();
                result = 1;


            } catch (Exception e) {
                e.printStackTrace();
                result = 0;
            }

            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {

            // setProgressBarIndeterminateVisibility(false); TODO Add ProgressHUD in App
            if(progressHUD.isShowing()){
                progressHUD.dismiss();
            }


            /* Download complete. Lets update UI */
            if (result == 1) {
                adapter = new MediaRVAdapter(getContext(), mediaList);
                mRecyclerView.setAdapter(adapter);
            } else {
                Log.e(TAG, "Failed to fetch data!");
            }
        }
    }

}
