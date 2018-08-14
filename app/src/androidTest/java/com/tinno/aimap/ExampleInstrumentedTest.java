package com.tinno.aimap;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static final String TAG = "ExampleInstrumentedTest";
    private Context mContext;
    @Before
    public void setUp() {
        mContext = InstrumentationRegistry.getTargetContext();
    }
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.tinno.aimap", appContext.getPackageName());
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private void post(String url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder().add("username","xiaoyi").build();
        Request request = new Request.Builder().post(body).url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse: "+response);
            }
        });
    }

    private void get(String url) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                gson.fromJson(response.body().string(), JsonUtil.TokenJson.class);
                Log.i(TAG, "onResponse: "+ response.body().string());
            }
        });

//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().get().url(url).build();
//        try (Response response = client.newCall(request).execute()) {
//          Gson gson = new Gson();
//          ResponseBody body = response.body();
//          if (body != null) {
//              Log.i(TAG, "get: " + body.string());
//              gson.fromJson(body.string(), JsonUtil.TokenJson.class);
//          }
//      }
    }

    @Test
    public void test() {
        try {
            get(MainActivity.RequestPacket.mAccessTokenUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
