package com.nawasoft.oneapp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImagesUtil {

    private static String PICTURES_FOLDER = "images";


    /**
      This function just will load picture from url
    **/

    public static void loadPicture(ImageView imageView, String url) {
        if (url.isEmpty())
            return;
        Picasso.get()
                .load(url)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .fit()
                .into(imageView);
    }


    /**
       This function just will load picture from url and cache it
     **/
    public static void loadPictureAndCache(ImageView imageView, String url) {
        if (url.isEmpty())
            return;
        Picasso.get()
                .load(url)
                .fit()
                .into(imageView);
    }



    /**
        @param url : is remote url

        This function will load the picture from network :
            if success : will save the picture in local storage
            if failed : will try to load picture from local storage
     **/
    public static void loadPictureAndStore(Context context, ImageView imageView, String url) {
        if (url.isEmpty())
            return;
        Picasso.get()
                .load(url)
                .fit()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        String savedPath = getPicturesFolder(context) + "/" + getFileNameFromUrl(url);
                        savePictureOnStorage(imageView, savedPath);
                    }

                    @Override
                    public void onError(Exception e) {
                        String picturePath = getPicturesFolder(context) + "/" + getFileNameFromUrl(url);
                        loadPictureFromFile(imageView, new File(picturePath));
                    }
                });
    }

    public static void savePictureOnStorage(ImageView imageView, String fullPath) {
        new Thread() {
            @Override
            public void run() {
                if (isInterrupted()) {
                    return;
                }
                File file = new File(fullPath);
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(imageViewToByteArray(imageView));
                    fileOutputStream.close();
                    System.out.println("file saved in " + fullPath);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("error when save the file in = " + fullPath);
                }


            }
        }.start();
    }

    public static void loadPictureFromFile(ImageView imageView, File file) {
        Picasso.get()
                .load(file)
                .fit()
                .into(imageView);
        System.out.println("loaded from file");
    }


    public static String getPicturesFolder(Context context) {
        File file = new File(context.getFilesDir().getAbsolutePath() + "/" + PICTURES_FOLDER);
        file.mkdirs();
        return file.getAbsolutePath();
    }

    public static String getFileNameFromUrl(String url) {
        String[] array = url.split("/");
        return array[array.length - 1];

    }

    public static byte[] imageViewToByteArray(ImageView imageView) {
        Bitmap bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public static byte[] bitmapToByteStream(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return Base64.encode(stream.toByteArray(), Base64.NO_WRAP);
    }

    public static Uri getImageUriFromBitmap(Context inContext, Bitmap inImage) {
        Bitmap OutImage = Bitmap.createScaledBitmap(inImage, 1000, 1000, true);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), OutImage, "Title", null);
        return Uri.parse(path);
    }
}
