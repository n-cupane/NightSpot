package com.nighter.nightspot.models;

public class UploadImageRequest {

    private String imageData;

    private String fileName;

    private String mimeTipe;

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeTipe() {
        return mimeTipe;
    }

    public void setMimeTipe(String mimeTipe) {
        this.mimeTipe = mimeTipe;
    }
}
