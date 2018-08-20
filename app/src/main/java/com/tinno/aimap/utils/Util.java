package com.tinno.aimap.utils;

import android.annotation.SuppressLint;

import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class Util {
    private static class GsonHolder{
        private static final Gson INSTANCE = new Gson();
    }

    public static Gson getGson(){
        return GsonHolder.INSTANCE;
    }

    @SuppressLint("NewApi")
    public static String getBase64String(String path) throws IOException {
        byte[] bytes = FileUtils.readFileToByteArray(new File(path));
        return Base64.getEncoder().encodeToString(bytes);
    }
}
