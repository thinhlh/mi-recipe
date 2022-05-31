package com.thinhlh.utils.helper;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import androidx.annotation.NonNull;

import java.util.Locale;

/**
 * Created by thinhlh on 3/1/2022.
 * Copyright 2022 (c)
 */
public abstract class FileHelper {

    public static @NonNull
    String getMimeType(Context context, Uri uri) {
        String mimeType = null;

        try {
            if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
                final ContentResolver contentResolver = context.getContentResolver();

                mimeType = contentResolver.getType(uri);

            } else {
                final String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString());

                mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension.toLowerCase(Locale.ROOT));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return mimeType == null ? "" : mimeType;
    }
}
