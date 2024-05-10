package com.nighter.nightspot.models;

import lombok.Data;

@Data
public class UploadImageRequest {

    private byte[] imageData;

    private String fileName;

    private String mimeTipe;
}
