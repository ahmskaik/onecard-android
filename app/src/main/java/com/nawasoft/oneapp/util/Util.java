package com.nawasoft.oneapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Locale;

public class Util {

    public static final Integer SELECT_LANGUAGE_CODE = 12;

    public static final Integer PICK_PHOTO_CODE = 151;

    public static final Integer TAKE_PHOTO_CODE = 152;


    public static void loadPictureAndCache(ImageView imageView, String path) {
        ImagesUtil.loadPictureAndCache(imageView, path);
    }

    public static void goToActivity(Context context, Class<?> target, Bundle bundle) {
        Intent intent = new Intent(context, target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void goToActivityForResult(Context context, Class<?> target, int requestCode, Bundle bundle) {
        Intent intent = new Intent(context, target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        ((Activity) context).startActivityForResult(intent, requestCode, bundle);
    }


    public static void addFragment(FragmentManager fragmentManager, int containerId, Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .add(containerId, fragment)
                .commit();
    }

    public static void addFragmentToBackStack(FragmentManager fragmentManager, int containerId, Fragment fragment, String tag) {
        fragmentManager
                .beginTransaction()
                .add(containerId, fragment)
                .addToBackStack(tag)
                .commit();
    }

    public static void replaceFragment(FragmentManager fragmentManager, int containerId, Fragment fragment, String tag) {
        fragmentManager
                .beginTransaction()
                .replace(containerId, fragment, tag)
                .commit();
    }

    public static void changeAppLanguage(Context context, String language) {
        System.out.println(language);
        Resources res = context.getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(language.toLowerCase())); // API 17+ only.
        // Use conf.locale = new Locale(...) if targeting lower versions
        res.updateConfiguration(conf, dm);
    }

    public static void pickPhoto(Activity activity) {
        Intent intent = new Intent();

        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                PICK_PHOTO_CODE
        );
    }

    public static void takePhoto(Activity activity) {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, TAKE_PHOTO_CODE);
    }

    public static String getLanguageFromCode(String code) {
        code = code.toLowerCase();
        if (code.equalsIgnoreCase("ar")) {
            code = "Arabic";
        } else if (code.equalsIgnoreCase("en")) {
            code = "English";
        } else if (code.equalsIgnoreCase("tr")) {
            code = "Turcky";
        }

        return code;
    }

    public static void openBrowser(Activity activity, String url) {
        if (url == null || url.isEmpty())
            return;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
    }

    public static void callNumber(Activity activity, String number) {
        if (number == null || number.isEmpty())
            return;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("tel:" + number));
        activity.startActivity(intent);
    }

    public static void sendEmail(Activity activity, String email) {
        if (email == null || email.isEmpty())
            return;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        try {
            activity.startActivity(Intent.createChooser(intent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {

        }
    }

    public static void openMap(Activity activity, double latitude, double longitude, String title) {
        String geoUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude + " (" + title + ")";
        //String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
        activity.startActivity(intent);
    }

}
