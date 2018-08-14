package com.tinno.aimap;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestUtil {

    public static void logConfigXml() {
        String xmlFilePath = Util.getAndCreateAppFolderPath() + "/config.xml"; // dev_config.xml // config.xml
        String newXmlFilePath = Util.getAndCreateAppFolderPath() + "/new_config.xml"; // dev_config.xml // config.xml
        File file = new File(xmlFilePath);
        File newFile = new File(newXmlFilePath);
        if (newFile.exists()) newFile.delete();
        try {
            FileInputStream fis = new FileInputStream(file);
            int length = fis.available();
            Log.d("bbb", "logConfigXml fis.length = " + length);

            newFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(newFile);

            int index = 0;
            int data;
            do {
                data = fis.read();
                boolean isXR = (data == 0xd);
                boolean isXN = (data == 0xa);
                String dataS = Integer.toHexString(data & 0xFF);
                Log.d("bbb", "logConfigXml " + (index++) + " data = " + dataS + ", xr = " + isXR + ", isXN = " + isXN);
                if (isXR) continue;
                //byte[] bytes = new byte[1];
                //bytes[0] = (byte) (data & 0xFF);
                fos.write(data);
            } while (data != -1);

            fis.close();
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
