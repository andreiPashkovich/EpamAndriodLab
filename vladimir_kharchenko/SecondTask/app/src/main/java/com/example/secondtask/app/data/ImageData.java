package com.example.secondtask.app.data;

import android.net.Uri;
import android.provider.ContactsContract;

import java.io.Serializable;

/**
 * Created by vladimir_kharchenko on 22.02.14.
 */
public class ImageData implements Serializable{

    private Uri imageUri;

    private String message;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}
