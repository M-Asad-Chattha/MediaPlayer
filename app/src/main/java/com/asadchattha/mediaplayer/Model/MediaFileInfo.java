package com.asadchattha.mediaplayer.Model;

/**
 * Created by Mukesh on 12/20/2015.
 */
public class MediaFileInfo {
    private String fileName, filePath, fileType, fileSize, videoDuration, videoWidthHeight;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(int videoDurationInSeconds) {
        int seconds, minutes, hours;
        /*if (videoDurationInSeconds<=60){
            minutes = (videoDurationInSeconds % 3600) / 60;
            seconds = videoDurationInSeconds % 60;
            this.videoDuration = String.format("%02d:%02d", minutes, seconds);
        }*/

        hours = videoDurationInSeconds / 3600;
        minutes = (videoDurationInSeconds % 3600) / 60;
        seconds = videoDurationInSeconds % 60;

        this.videoDuration = String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public String getVideoWidthHeight() {
        return videoWidthHeight;
    }

    public void setVideoWidthHeight(String videoWidthHeight) {
        this.videoWidthHeight = videoWidthHeight;
    }
}
