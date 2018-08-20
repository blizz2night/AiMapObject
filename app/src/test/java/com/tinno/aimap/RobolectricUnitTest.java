package com.tinno.aimap;

import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 27)
public class RobolectricUnitTest {
    private static final String TAG = "RobolectricUnitTest";
    @Test
    public void test(){
        Log.i(TAG, "test: ");
    }
}
