package com.tinno.aimap;

import android.support.annotation.NonNull;

import com.tinno.aimap.model.OcrResponseBean;
import com.tinno.aimap.model.RecgResponseBean;
import com.tinno.aimap.model.ResponseBean;
import com.tinno.aimap.model.TokenBean;
import com.tinno.aimap.service.BaiduService;
import com.tinno.aimap.utils.GsonHelper;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.tinno.aimap.TestPath.TEST_IMG_DIR;
import static com.tinno.aimap.TestPath.TEST_TOKEN_DIR;
import static com.tinno.aimap.utils.GsonHelper.getBase64String;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class BaiduServiceUnitTest{

    private BaiduService mBaiduService;
    @Before
    public void setUp() throws Exception {
        mBaiduService = new BaiduService();
        String token = mBaiduService.getToken();
        mBaiduService.setAccessToken(token);
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /**
     * https://aip.baidubce.com/oauth/2.0/token
     * grant_type:client_credentials
     * client_id:zP0b7bjP1oXxwQGeZRrYtkxPRq11T3d0
     * client_secret:3BGv0l41KP5og0BW4Sj6GjFZGac90kmv
     * client_app_id:1585928312337326
     **/

    @Test
    public void testGetTokenAndOutputJson() {
        try {
            String json = mBaiduService.getTokenJson();
            FileUtils.writeStringToFile(new File(TEST_TOKEN_DIR +File.separator+"token.json"),json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetTokenAndPrint() {
        try {
            String token = mBaiduService.getToken();
            System.out.println(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetObjectRecognitionResult() {
        String name = "book2.jpeg";
        String folder = Catalog.BOOK/*+File.separator+Catalog.POSITIVE*/;
        String input = getInputPath(folder, name);
        String output = getOutputPath(folder, name);
        File imgFile = new File(input);
        assert imgFile.exists();
        try {
            String base64String = getBase64String(imgFile.getPath());
            String resultJson = mBaiduService.objectRecognize(base64String);
            System.out.println(resultJson);
            File file = new File(output);
            FileUtils.writeStringToFile(file,resultJson);
            RecgResponseBean jsonResult = GsonHelper.getRecgResponseBean(resultJson);
            RecgResponseBean.Result result = jsonResult.getResult();
            System.out.println(result);
//            result.keySet().forEach(System.out::println);
//            assert result.keySet().size() >= 2;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private String getInputPath(@Catalog String folder, String name) {
        return TEST_IMG_DIR + File.separator + folder + File.separator + name;
    }

    @NonNull
    private String getOutputPath(@Catalog String folder, String name) {
        return TEST_IMG_DIR + File.separator + folder + File.separator + "json" + File.separator + name + ".json";
    }


    @Test
    public void testTokenExpired() {
        File imgDir = new File(TEST_IMG_DIR);
        assert imgDir.exists();
        String[] list = imgDir.list();
        assert list.length>0 ;
        File imgFile = new File("src/test/res/raw/img/face/face.jpg");
        assert imgFile.exists();
        File tokenFile = new File(TEST_TOKEN_DIR + File.separator + "token.json");
        assert imgDir.exists();
        try {
            String base64String = getBase64String(imgFile.getPath());
            String json = FileUtils.readFileToString(tokenFile);
            TokenBean tokenBean = GsonHelper.getTokenBean(json);
            String token = tokenBean.getAccessToken();
            mBaiduService.setAccessToken(token);
            String resultJson = mBaiduService.objectRecognize(base64String);
//            File file = new File(TEST_TOKEN_DIR +File.separator+imgFile.getName()+".json");
//            FileUtils.writeStringToFile(file,resultJson);
            ResponseBean responseBean = GsonHelper.getResponseBean(resultJson);
            System.out.println(responseBean.getErrorMsg());
            assert responseBean.getErrorCode()== BaiduService.ErrorCode.ACCESS_TOKEN_EXPIRED;
            token = mBaiduService.getToken();
            mBaiduService.setAccessToken(token);
            resultJson = mBaiduService.objectRecognize(base64String);
            responseBean = GsonHelper.getRecgResponseBean(resultJson);
            System.out.println(responseBean.getErrorMsg());
            assert responseBean.getErrorCode()== BaiduService.ErrorCode.SUCCESS;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAndWriteCharacterRecognitionJsonString() {
        String folder = Catalog.OCR;
        String imgName = "02.png";
        String path = getInputPath(folder, imgName);
        File imgFile = new File(path);
        assert imgFile.exists();
        try {
            String base64String = getBase64String(imgFile.getPath());
            String resultJson = mBaiduService.characterRecognize(base64String);
            File file = new File(imgFile.getParent() + File.separator + "json" + File.separator + imgName + ".json");
            FileUtils.writeStringToFile(file,resultJson);
            System.out.println(resultJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAnimal() {
        testRecgInFolder(Catalog.ANIMAL);

    }

    @Test
    public void testBook() {
        testRecgInFolder(Catalog.BOOK);

    }

    @Test
    public void testBrand() {
        testRecgInFolder(Catalog.BRAND);

    }

    @Test
    public void testCar(){
        testRecgInFolder(Catalog.CAR);

    }

    @Test
    public void testDishes() {
        testRecgInFolder(Catalog.DISHES);

    }

    @Test
    public void testFace(){
        testRecgInFolder(Catalog.FACE);

    }

    @Test
    public void testMovie(){
        testRecgInFolder(Catalog.MOVIE);

    }

    @Test
    public void testMusic() {
        testRecgInFolder(Catalog.MUSIC);

    }
    @Test
    public void testPlant() {
        testRecgInFolder(Catalog.PLANT);

    }
    @Test
    public void testProduct() {
        testRecgInFolder(Catalog.PRODUCT);

    }
    @Test
    public void testScan() {
        testRecgInFolder(Catalog.SCAN);

    }

    @Test
    public void testOCR(){
        testOcrInFolder(Catalog.OCR);
    }

    private void testOcrInFolder(@Catalog String folder) {
        String inputPath = TEST_IMG_DIR + File.separator + folder;
        String outputPath = inputPath + File.separator + "json";
        File imgDir = new File(inputPath);
        assert imgDir.exists();
        String[] imgNames = imgDir.list();
        assert imgNames.length>0 ;
        try {
            for (String imgName : imgNames) {
                if (!(imgName.endsWith("jpeg") || imgName.endsWith("jpg") || imgName.endsWith("png"))) {
                    continue;
                }
                File imgFile = new File(inputPath + File.separator + imgName);
                String base64String = getBase64String(imgFile.getPath());
                String resultJson = mBaiduService.characterRecognize(base64String);
                OcrResponseBean bean = GsonHelper.getOcrResponseBean(resultJson);
                System.out.println(bean);
                File file = new File(outputPath + File.separator + imgFile.getName() + ".json");
                FileUtils.writeStringToFile(file, resultJson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testRecgInFolder(@Catalog String folder) {
        String inputPath = TEST_IMG_DIR + File.separator + folder;
        String outputPath = inputPath + File.separator + "json";
        File imgDir = new File(inputPath);
        assert imgDir.exists();
        String[] imgNames = imgDir.list();
        assert imgNames.length>0 ;
        try {
            for (String imgName : imgNames) {
                File f = new File(inputPath, imgName);
                if (f.isDirectory()) {
                    testRecgInFolder(folder + File.separator + imgName);
                } else if ((imgName.endsWith("jpeg") || imgName.endsWith("jpg") || imgName.endsWith("png"))) {
                    File imgFile = new File(inputPath + File.separator + imgName);
                    String base64String = getBase64String(imgFile.getPath());
                    String resultJson = mBaiduService.objectRecognize(base64String);
                    RecgResponseBean jsonResult = GsonHelper.getRecgResponseBean(resultJson);
                    System.out.println(jsonResult);
                    System.out.println();
                    assert jsonResult.getErrorCode() == BaiduService.ErrorCode.SUCCESS;
                    File file = new File(outputPath + File.separator + imgFile.getName() + ".json");
                    FileUtils.writeStringToFile(file, resultJson);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}