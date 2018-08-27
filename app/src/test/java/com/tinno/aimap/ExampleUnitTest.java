package com.tinno.aimap;

import android.support.annotation.NonNull;

import com.tinno.aimap.model.RecgResultBean;
import com.tinno.aimap.service.AIService;
import com.tinno.aimap.service.ErrorCode;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static com.tinno.aimap.service.AIService.getToken;
import static com.tinno.aimap.service.AIService.getTokenJson;
import static com.tinno.aimap.service.AIService.parseRecgResult;
import static com.tinno.aimap.service.AIService.parseToken;
import static com.tinno.aimap.utils.Util.getBase64String;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {

    public static final String IMG_MAIN_DIR_PATH = "src/main/res/raw/img";
    public static final String JSON_MAIN_DIR_PATH = "src/main/res/raw/json";
    public static final String TEST_IMG_DIR = "src/test/res/raw/img";
    public static final String TEST_JSON_DIR = "src/test/res/raw/json";

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testReadFile() {
        File imgDir = new File(TEST_IMG_DIR);
        assert imgDir.exists();
        String[] list = imgDir.list();
        assert list.length>0 ;
        System.out.println(Arrays.deepToString(list));
        File imgFile = new File(imgDir.getAbsolutePath() + File.separator + list[0]);
        try {
            byte[] bytes = FileUtils.readFileToByteArray(imgFile);
            assert bytes.length>0 ;
            System.out.println("img bytes="+bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            String json = getTokenJson();
            FileUtils.writeStringToFile(new File(TEST_JSON_DIR +File.separator+"token.json"),json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetTokenAndPrint() {
        try {
            String token = getToken();
            System.out.println(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetObjectRecognitionResult() {
        String name = "carlogo.jpeg";
        String input = getInputPath(Catalog.BRAND, name);
        String output = getOutputPath(Catalog.BRAND, name);
        File imgFile = new File(input);
        assert imgFile.exists();
        try {
            String base64String = getBase64String(imgFile.getPath());
            String token = getToken();
            String resultJson = AIService.objectRecognize(token, base64String);
            File file = new File(output);
            FileUtils.writeStringToFile(file,resultJson);
            RecgResultBean jsonResult = parseRecgResult(resultJson);
            Map<String, Object> result = jsonResult.getResult();
            result.keySet().forEach(System.out::println);
            assert result.keySet().size() >= 2;
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
        byte[] bytes = null;
        File imgFile = new File(TEST_IMG_DIR + File.separator + list[0]);
        assert imgFile.exists();
        File tokenFile = new File(TEST_JSON_DIR + File.separator + "token.json");
        assert imgDir.exists();
        try {
            String base64String = getBase64String(imgFile.getPath());
            String json = FileUtils.readFileToString(tokenFile);
            String token = parseToken(json);
            String resultJson = AIService.objectRecognize(token, base64String);
            File file = new File(TEST_JSON_DIR +File.separator+imgFile.getName()+".json");
            FileUtils.writeStringToFile(file,resultJson);
            RecgResultBean jsonResult = parseRecgResult(resultJson);
            System.out.println(jsonResult.getErrorMsg());
            assert jsonResult.getErrorCode()== ErrorCode.ACCESS_TOKEN_EXPIRED;

            token = getToken();
            resultJson = AIService.objectRecognize(token, base64String);
            jsonResult = parseRecgResult(resultJson);
            System.out.println(jsonResult.getErrorMsg());
            assert jsonResult.getErrorCode()==ErrorCode.SUCCESS;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPostImgsAndGetJsons() {
//        byte[] bytes = FileUtils.readFileToByteArray(new File());
//        String image = Base64.encodeToString(bytes, Base64.DEFAULT);
        String pathname = TEST_IMG_DIR + File.separator + "brand";
        File imgDir = new File(pathname);
        assert imgDir.exists();
        String[] imgNames = imgDir.list();
        assert imgNames.length>0 ;
        try {
            String token = getToken();
            for (String imgName : imgNames) {
                File imgFile = new File(pathname + File.separator + imgName);
                String base64String = getBase64String(imgFile.getPath());

                String resultJson = AIService.objectRecognize(token, base64String);
                RecgResultBean jsonResult = parseRecgResult(resultJson);
                File file = new File(pathname + File.separator + imgFile.getName() + ".json");
                FileUtils.writeStringToFile(file, resultJson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCharacterRecognition() {
        String folder = "ocr";
        String imgName = "IMG_20180103_022117.jpg";
        String path = getInputPath(imgName, folder);
        File imgFile = new File(path);
        assert imgFile.exists();
        try {
            String base64String = getBase64String(imgFile.getPath());
            String token = getToken();
            String resultJson = AIService.characterRcognize(token, base64String);
            File file = new File(imgFile.getParent() + File.separator + "json" + File.separator + imgName + ".json");
            FileUtils.writeStringToFile(file,resultJson);
            RecgResultBean jsonResult = parseRecgResult(resultJson);
            System.out.println(jsonResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAnimal() {
        try {
            String token = getToken();
            testRecgInFolder(Catalog.ANIMAL, token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBook() {
        try {
            String token = getToken();
            testRecgInFolder(Catalog.BOOK, token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBrand() {
        try {
            String token = getToken();
            testRecgInFolder(Catalog.BRAND, token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCar(){
        try {
            String token = getToken();
            testRecgInFolder(Catalog.CAR, token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDish() {
        try {
            String token = getToken();
            testRecgInFolder(Catalog.DISH, token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFace(){
        try {
            String token = getToken();
            testRecgInFolder(Catalog.FACE, token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMovie(){
        try {
            String token = getToken();
            testRecgInFolder(Catalog.MOVIE, token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMusic() {
        try {
            String token = getToken();
            testRecgInFolder(Catalog.MUSIC, token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testPlant() {
        try {
            String token = getToken();
            testRecgInFolder(Catalog.PLANT, token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testProduct() {
        try {
            String token = getToken();
            testRecgInFolder(Catalog.PRODUCT, token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testScan() {
        try {
            String token = getToken();
            testRecgInFolder(Catalog.SCAN, token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOCR(){
        try {
            String token = getToken();
            testOcrInFolder(Catalog.OCR,token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testOcrInFolder(@Catalog String folder, String token) {
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
                String resultJson = AIService.characterRcognize(token, base64String);
                RecgResultBean jsonResult = parseRecgResult(resultJson);
                File file = new File(outputPath + File.separator + imgFile.getName() + ".json");
                FileUtils.writeStringToFile(file, resultJson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testRecgInFolder(@Catalog String folder, String token) {
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

                String resultJson = AIService.objectRecognize(token, base64String);
                RecgResultBean jsonResult = parseRecgResult(resultJson);
                assert jsonResult.getErrorCode() == ErrorCode.SUCCESS;
                File file = new File(outputPath + File.separator + imgFile.getName() + ".json");
                FileUtils.writeStringToFile(file, resultJson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetAnimalBean() {
        String inputPath = "src/test/res/raw/img/animal/json/animal.jpg.json";
        try {
            String s = FileUtils.readFileToString(new File(inputPath));
            RecgResultBean recgResultBean = parseRecgResult(s);
            Map<String, Object> result = recgResultBean.getResult();
            Object animal = result.get(Catalog.ANIMAL);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}