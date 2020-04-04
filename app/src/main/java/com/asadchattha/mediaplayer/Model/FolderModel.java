package com.asadchattha.mediaplayer.Model;

import java.util.ArrayList;

/**
 * Created by deepshikha on 3/3/17.
 */

public class FolderModel {
    String folderName;
    ArrayList<String> folderVideosPathsList;


    public FolderModel() {
    }

    public FolderModel(String folderName, ArrayList<String> folderVideosPathsList) {
        this.folderName = folderName;
        this.folderVideosPathsList = folderVideosPathsList;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public ArrayList<String> getFolderVideosPathsList() {
        return folderVideosPathsList;
    }

    public void setFolderVideosPathsList(ArrayList<String> folderVideosPathsList) {
        this.folderVideosPathsList = folderVideosPathsList;
    }
}
