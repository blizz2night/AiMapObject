package com.tinno.aimap;

import android.content.Context;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class RobolectricUnitTest {
    private static final String TAG = "RobolectricUnitTest";
    private Context mContext;

    @Before
    public void setUp() {
//        mContext = RuntimeEnvironment.systemContext;
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        mContext = activity.getApplicationContext();
    }

    @Test
    public void test(){
        Log.i(TAG, "test: ");
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(mContext.getResources().openRawResource(R.raw.baikejson)))){
//            String s;
//            while ((s = reader.readLine()) != null) {
//                System.out.println(s);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
