package com.example.smartplot.dto;

public class UploadResponse {

    private String imageUrl;

    public UploadResponse() {
    }

    public UploadResponse(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
