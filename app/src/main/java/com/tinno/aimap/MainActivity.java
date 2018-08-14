package com.tinno.aimap;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final int REQUEST_PERMISSION = 10000;
    private static final int RESULT_PICK_IMAGE = 10001;
    private static final int REQUEST_SYSTEM_CAMERA = 10002;

    private AppCompatActivity mActivity;
    private Uri mFileUri;

    private TextView mBtnPickImage;
    private ImageView mImageView;
    private TextView mBtnRequestAiMap;
    private TextView mTextReturn;
    private TextView mTextResult;
    private TextView mTextUrl;
    private TextView mTextSupport;
    private TextView mTextLogId;
    private TextView mTextErrorMsg;
    private TextView mTextErrorCode;
    private TextView mBtnRequestUrl;

    private FrameLayout mProgressFrame;
    private View mViewCover;

    private Bitmap mThumbnail;
    private BitmapLoadTask mBitmapLoadTask;
    private String mUrl;
    private boolean mActivityDestroy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_main);

        if (checkPermission()) {
            findView();
        }
    }

    public boolean checkPermission() {
        ArrayList<String> permissionsNeeded = new ArrayList<>();
        permissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissionsNeeded.add(Manifest.permission.READ_PHONE_STATE);

        ArrayList<String> permissionsNeedRequest = new ArrayList<>();
        for (String permission : permissionsNeeded) {
            int permissionGranted = ActivityCompat.checkSelfPermission(this, permission);
            if (permissionGranted == PackageManager.PERMISSION_GRANTED) {
                continue;
            }
            permissionsNeedRequest.add(permission);
        }

        if (permissionsNeedRequest.size() == 0) {
            return true;
        }

        String[] permissions = new String[permissionsNeedRequest.size()];
        permissions = permissionsNeedRequest.toArray(permissions);
        ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSION);
        return false;
    }

    private void findView() {
        mBtnPickImage = findViewById(R.id.button_pick_image);
        mBtnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_PICK_IMAGE);
            }
        });
        mImageView = findViewById(R.id.image_view);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mFileUri = Util.getOutputImageFileUri(mActivity.getApplicationContext());
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mFileUri);
                startActivityForResult(intent, REQUEST_SYSTEM_CAMERA);
            }
        });

        mBtnRequestAiMap = findViewById(R.id.button_request_aimap);
        mBtnRequestAiMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mThumbnail == null || mThumbnail.isRecycled()) {
                    Toast.makeText(mActivity, "请先选择或拍摄一张图片", Toast.LENGTH_SHORT).show();
                    return;
                }
                networkRequestAiMap(mThumbnail);
            }
        });
        mTextReturn = findViewById(R.id.text_view_return);
        mTextResult = findViewById(R.id.text_view_result);
        mTextUrl = findViewById(R.id.text_view_url);
        mTextSupport = findViewById(R.id.text_view_support);
        mTextLogId = findViewById(R.id.text_view_logid);
        mTextErrorMsg = findViewById(R.id.text_view_error_msg);
        mTextErrorCode = findViewById(R.id.text_view_error_code);

        mBtnRequestUrl = findViewById(R.id.button_request_url);
        mBtnRequestUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mUrl)) {
                    Toast.makeText(mActivity, "请先识别图片", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent();
                intent.setData(Uri.parse(mUrl));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });

        mProgressFrame = findViewById(R.id.frame_progress_bar);
        mViewCover = findViewById(R.id.view_cover);
        mViewCover.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        if (mBitmapLoadTask != null) {
            mBitmapLoadTask.cancel(true);
        }
        mActivityDestroy = true;
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults == null || grantResults.length == 0) {
                Log.w("aaa", "request permissions is interrupted");
                return;
            }
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    finish();
                    return;
                }
            }
            findView();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("aaa", "onActivityResult, requestCode = " + requestCode
                + ", resultCode = " + resultCode + ", data = " + data + ", pre uri = " + mFileUri);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            switch (requestCode) {
                case RESULT_PICK_IMAGE:
                    if (data == null) return;
                    uri = data.getData();
                    break;
                case REQUEST_SYSTEM_CAMERA:
                    if (mFileUri == null) return;
                    uri = mFileUri;
                    mFileUri = null;
                    break;
                default:
                    Log.d("aaa", "default, request code = " + requestCode);
                    uri = null;
            }

            Log.d("aaa", "onActivityResult, uri = " + uri);
            if (mBitmapLoadTask != null) {
                Toast.makeText(mActivity, "正在加载上一张图片", Toast.LENGTH_SHORT).show();
                return;
            }
            mBitmapLoadTask = new BitmapLoadTask(getApplicationContext(), mImageView);
            mBitmapLoadTask.execute(uri);
        }
    }

    private void networkRequestAiMap(Bitmap bitmap) {
        RequestPacket packet = new RequestPacket();
        packet.setBitmap(bitmap);
        Observable.just(packet)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mProgressFrame.setVisibility(View.VISIBLE);
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<RequestPacket, RequestPacket>() {
                    @Override
                    public RequestPacket apply(RequestPacket packet) throws Exception {
                        if (mActivityDestroy) return null;
                        String accessTokenUrl = packet.getAccessTokenUrl();
                        String accessToken = requestAccessToken(accessTokenUrl);
                        packet.setAccessToken(accessToken);
                        Log.d("aaa", "networkRequestAiMap map1 access token = " + packet.getAccessToken());
                        return packet;
                    }
                })
                .map(new Function<RequestPacket, String>() {
                    @Override
                    public String apply(RequestPacket packet) throws Exception {
                        if (mActivityDestroy) return null;
                        String result = requestAiMap(packet);
                        Log.d("aaa", "networkRequestAiMap map2 result.length = " + result.length());
                        return result;
                    }
                })
                .map(new Function<String, JsonUtil.Json>() {
                    @Override
                    public JsonUtil.Json apply(String str) throws Exception {
                        JsonUtil.Json json = JsonUtil.parseJSONWithJSONObject(str);
                        Log.d("aaa", "networkRequestAiMap map3 json = " + json);
                        /*String url = json.getUrl();
                        if (!TextUtils.isEmpty(url)) {
                            requestResult(url);
                        }*/
                        return json;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<JsonUtil.Json>() {
                    @Override
                    public void accept(JsonUtil.Json json) throws Exception {
                        Log.d("aaa", "networkRequestAiMap subscribe json = " + json);
                        if (json == null) return;
                        updateAiMapResult(json);
                        mProgressFrame.setVisibility(View.GONE);
                    }
                });
    }

    private String requestAccessToken(String str) {
        try {
            URL url = new URL(str);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int response = connection.getResponseCode();
            Log.d("aaa", "requestAccessToken response = " + response);
            if (response == HttpURLConnection.HTTP_OK) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                //得到InputStream, 并读取成String
                InputStream input = connection.getInputStream();
                byte[] buffer = new byte[1024];
                int len = -1;
                while ((len = input.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                baos.close();
                input.close();

                String result = baos.toString();
                Log.d("aaa", "requestAccessToken result = \n" + result);
                JsonUtil.TokenJson json = JsonUtil.parseTokenJson(result);
                String token = json.getAccess_token();
                Log.d("aaa", "requestAccessToken token = " + token);
                return token;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressLint("MissingPermission")
    private String requestAiMap(RequestPacket packet) {
        StringBuilder result = new StringBuilder();
        ByteArrayOutputStream arrayOutput = new ByteArrayOutputStream(65536);
        Bitmap bitmap = packet.getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, arrayOutput);
        byte[] bitmapBytes = arrayOutput.toByteArray();
        String image = Base64.encodeToString(bitmapBytes, Base64.NO_WRAP);
        String imageUtf8 = null;
        try {
            imageUtf8 = URLEncoder.encode(image, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.d("aaa", "requestAiMap image.length = " + image.length());

        try {
            // 得到path,创建URL对象
            String aiMapUrl = packet.getAiMapUrl();
            URL url = new URL(aiMapUrl);
            // 打开连接, 得到HttpURLConnection对象
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置请求方式,连接超时, 读取数据超时
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            // 连接服务器
            connection.connect();

            OutputStream output = connection.getOutputStream();
            String meid = null;
            TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                meid = manager.getMeid();
            } else {
                meid = manager.getDeviceId();
                Log.i(TAG, "requestAiMap: "+meid);
            }

            String data = "access_token=" + packet.getAccessToken()
                    + "&image=" + imageUtf8
//                    + "&client_app_id=" + packet.getClientAppId()
                    +"&ecology_uid="+meid
                    +"&eco=general";
            output.write(data.getBytes());

            int responseCode = connection.getResponseCode();
            Log.d("aaa", "requestAiMap responseCode = " + responseCode);
            if(responseCode == HttpURLConnection.HTTP_OK) {
                //得到InputStream, 并读取成String
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line = null;
                    while ((line = reader.readLine()) != null) { // 循环从流中读取
                        result.append(line).append("\n");
                        Log.d("result", "requestAiMap result = \n" + line);
                    }
                } catch (IOException e) {
                    Log.e(TAG, "requestAiMap: ", e);
                }


                return result.toString();
            }
        } catch (MalformedURLException e) {
            Log.d("aaa", "requestAiMap e = " + e);
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("aaa", "requestAiMap e = " + e);
            e.printStackTrace();
        }

        return null;
    }

    private void requestResult(String str) {
        try {
            URL url = new URL(str);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int response = connection.getResponseCode();
            Log.d("aaa", "requestResult response = " + response);
            if(response == HttpURLConnection.HTTP_OK) {
                String folderPath = Util.getAndCreateAppFolderPath();
                String filePath = folderPath + "/ai_map_result.html";
                File file = new File(filePath);
                if (file.exists()) file.delete();
                file.getParentFile().mkdirs();
                file.createNewFile();
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");

                //ByteArrayOutputStream baos = new ByteArrayOutputStream();

                //得到InputStream, 并读取成String
                InputStream input = connection.getInputStream();
                byte[] buffer = new byte[1024];
                int len = -1;
                while ((len = input.read(buffer)) != -1) {
                    //baos.write(buffer, 0, len);
                    raf.seek(file.length());
                    raf.write(buffer);
                }
                input.close();
                //baos.close();
                raf.close();

                //String result = baos.toString();
                //Log.d("aaa", "requestResult result = >>>>>>\n" + result + "<<<<<<\n");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d("aaa", "requestResult e = " + e);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("aaa", "requestResult e = " + e);
        }
    }

    private void resetAiMapResult() {
        mTextReturn.setText("return: - ");
        mUrl = null;
        mTextResult.setText("result: ");
        mTextUrl.setText("url: ");
        mTextSupport.setText("support: ");
        mTextLogId.setText("log id: ");
        mTextErrorMsg.setText("error msg: ");
        mTextErrorCode.setText("error code: ");
    }

    private void updateAiMapResult(JsonUtil.Json json) {
        Log.d("aaa", "onPostExecute json = " + json);
        boolean result = json.getResult();
        if (result) {
            mTextReturn.setText("return: success");
            mUrl = json.getUrl();
        } else {
            mTextReturn.setText("return: fail");
        }
        mTextResult.setText("result: " + result);
        mTextUrl.setText("url: " + json.getUrl());
        mTextSupport.setText("support: " + json.getSupport());
        mTextLogId.setText("log id: " + json.getLogId());
        mTextErrorMsg.setText("error msg: " + json.getErrorMsg());
        mTextErrorCode.setText("error code: " + json.getErrorCode());
    }

    public static class RequestPacket {
        /**
         * client_id:zP0b7bjP1oXxwQGeZRrYtkxPRq11T3d0
         * client_secret:3BGv0l41KP5og0BW4Sj6GjFZGac90kmv
         * client_app_id:1585928312337326
         **/
        public static String mAccessTokenUrl = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials&client_id=zP0b7bjP1oXxwQGeZRrYtkxPRq11T3d0&client_secret=3BGv0l41KP5og0BW4Sj6GjFZGac90kmv";
//        public static String mAiMapUrl = "https://openapi.baidu.com/rest/2.0/mms/general/url";
        public static String mAiMapUrl = "https://openapi.baidu.com/rest/2.0/mms/v1/general/generalInfo";
        public static String mAccessToken = "24.a66354d3d7d8366a98345a80a00e2d63.7200.1533636724.282335-10487822";
        public static String mClientAppId = "1585928312337326";
        Bitmap bitmap;

        public String getAccessTokenUrl() {
            return mAccessTokenUrl;
        }

        public String getAiMapUrl() {
            return mAiMapUrl;
        }

        public String getAccessToken() {
            return mAccessToken;
        }

        public void setAccessToken(String mAccessToken) {
            this.mAccessToken = mAccessToken;
        }

        public String getClientAppId() {
            return mClientAppId;
        }

        public Bitmap getBitmap() {
            return bitmap;
        }

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }
    }

    private class BitmapLoadTask extends AsyncTask<Uri, Void, Bitmap> implements JobContext {

        private Context mContext;
        private ImageView mImageView;

        private BitmapLoadTask(Context context, ImageView imageView) {
            mContext = context;
            mImageView = imageView;
        }

        @Override
        public boolean isCancel() {
            return isCancelled();
        }

        @Override
        protected Bitmap doInBackground(Uri... params) {
            Uri uri = params[0];
            Log.d("aaa", "BitmapLoadTask.doInBackground uri = " + uri);
            int size = 500;//mContext.getResources().getDimensionPixelSize(R.dimen.photo_view_height);
            Bitmap bitmap = Util.createImageThumbnail(this, mContext, uri, size);
            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            mProgressFrame.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.d("aaa", "BitmapLoadTask.onPostExecute bitmap = " + bitmap);
            if (bitmap != null && !bitmap.isRecycled()) {
                Log.d("aaa", "BitmapLoadTask.doInBackground w = "
                        + bitmap.getWidth() + ", h = " + bitmap.getHeight());
                mImageView.setImageBitmap(bitmap);
                mThumbnail = bitmap;
            }
            mBitmapLoadTask = null;
            resetAiMapResult();
            mProgressFrame.setVisibility(View.GONE);
        }
    }

}
