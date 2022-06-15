package com.thinhlh.mi_recipe.data;

import android.net.Uri;

import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class FirebaseStorageHelper {
    private static final StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    private FirebaseStorageHelper() {
    }

    public static UploadTask upload(String path, Uri file) {
        return storageReference.child(path).putFile(file);
    }

    public static Task<Uri> getDownloadLink(String path) {
        return storageReference.child(path).getDownloadUrl();
    }
}
