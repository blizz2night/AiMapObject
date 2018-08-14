package com.tinno.aimap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {


    public static final String TIME_STAMP_NAME = "_yyyyMMdd_HHmmss";

    private static final SimpleDateFormat sDataFormat = new SimpleDateFormat(TIME_STAMP_NAME);
    // Note: it takes a few milliseconds to get each path, about 6 ~ 10 ms
    private static final String sAppFolderPath =
            Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures";

    public static String getAndCreateAppFolderPath() {
        File folder = new File(sAppFolderPath);
        if (!folder.exists()) folder.mkdirs();
        return sAppFolderPath;
    }

    public static String getOutputImageFilePath() {
        String folderName = getAndCreateAppFolderPath();
        String filePath = folderName + "/IMAGE"
                + sDataFormat.format(new Date(System.currentTimeMillis())) + ".jpg";
        return filePath;
    }

    public static Uri getOutputImageFileUri(Context context) {
        String filePath = getOutputImageFilePath();
        File file = new File(filePath);

        String authority = context.getString(R.string.app_file_provider_authorities);
        Uri fileUri = FileProvider.getUriForFile(context, authority, file);

        return fileUri;
    }

    public static Bitmap createImageThumbnail(JobContext jc, Context context, Uri uri, int size) {
        try {
            ParcelFileDescriptor fileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor fd = fileDescriptor.getFileDescriptor();
            if (jc.isCancel()) return null;

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(fd, null, options);
            if (jc.isCancel()) return null;

            int w = options.outWidth;
            int h = options.outHeight;

            // For screen nail, we only want to keep the longer side >= targetSize.
            float scale = (float) size / Math.max(w, h);
            options.inSampleSize = computeSampleSizeLarger(scale);

            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fd, null, options);
            if (bitmap == null) return null;

            if (bitmap == null || bitmap.getConfig() != null) {
                return bitmap;
            } else {
                Bitmap newBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, false);
                bitmap.recycle();
                return newBitmap;
            }
        } catch (FileNotFoundException e) {
            Log.w("aaa", "createImageThumbnail e: " + e);
            return null;
        }
    }

    // Find the min x that 1 / x >= scale
    public static int computeSampleSizeLarger(float scale) {
        int initialSize = (int) Math.floor(1f / scale);
        if (initialSize <= 1) return 1;

        return initialSize <= 8 ? prevPowerOf2(initialSize) : initialSize / 8 * 8;
    }

    // Returns the previous power of two.
    // Returns the input if it is already power of 2.
    // Throws IllegalArgumentException if the input is <= 0
    public static int prevPowerOf2(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        return Integer.highestOneBit(n);
    }
}
