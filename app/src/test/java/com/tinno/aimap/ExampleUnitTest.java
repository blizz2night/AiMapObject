package com.tinno.aimap;

import com.tinno.aimap.model.ResultBean;
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
import static com.tinno.aimap.service.AIService.parseResult;
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
    public static final String IMG_TEST_DIR_PATH = "src/test/res/raw/img";
    public static final String JSON_TEST_DIR_PATH = "src/test/res/raw/json";

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testReadFile() {
        File imgDir = new File(IMG_TEST_DIR_PATH);
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
            FileUtils.writeStringToFile(new File(JSON_TEST_DIR_PATH+File.separator+"token.json"),json);
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
        String picName = "cb8065380cd7912349e12315a7345982b2b7804a.jpg";
        File imgDir = new File(IMG_TEST_DIR_PATH);
        assert imgDir.exists();
        File imgFile = new File(IMG_TEST_DIR_PATH + File.separator + picName);
        assert imgFile.exists();
        try {
            String base64String = getBase64String(imgFile.getPath());
            String token = getToken();
            String resultJson = AIService.objectRecognize(token, base64String);
            File file = new File(JSON_TEST_DIR_PATH +File.separator+imgFile.getName()+".json");
            FileUtils.writeStringToFile(file,resultJson);
            ResultBean jsonResult = parseResult(resultJson);
            Map<String, Object> result = jsonResult.getResult();
            result.keySet().forEach(System.out::println);
            assert result.keySet().size() >= 2;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testTokenExpired() {
        File imgDir = new File(IMG_TEST_DIR_PATH);
        assert imgDir.exists();
        String[] list = imgDir.list();
        assert list.length>0 ;
        byte[] bytes = null;
        File imgFile = new File(IMG_TEST_DIR_PATH + File.separator + list[0]);
        assert imgFile.exists();
        File tokenFile = new File(JSON_TEST_DIR_PATH + File.separator + "token.json");
        assert imgDir.exists();
        try {
            String base64String = getBase64String(imgFile.getPath());
            String json = FileUtils.readFileToString(tokenFile);
            String token = parseToken(json);
            String resultJson = AIService.objectRecognize(token, base64String);
            File file = new File(JSON_TEST_DIR_PATH +File.separator+imgFile.getName()+".json");
            FileUtils.writeStringToFile(file,resultJson);
            ResultBean jsonResult = parseResult(resultJson);
            System.out.println(jsonResult.getErrorMsg());
            assert jsonResult.getErrorCode()== ErrorCode.ACCESS_TOKEN_EXPIRED;

            token = getToken();
            resultJson = AIService.objectRecognize(token, base64String);
            jsonResult = parseResult(resultJson);
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
        String pathname = IMG_TEST_DIR_PATH + File.separator + "brand";
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
                ResultBean jsonResult = parseResult(resultJson);
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
        String path = IMG_TEST_DIR_PATH + File.separator + folder + File.separator + imgName;
        File imgFile = new File(path);
        assert imgFile.exists();
        try {
            String base64String = getBase64String(imgFile.getPath());
            String token = getToken();
            String resultJson = AIService.characterRcognize(token, base64String);
            File file = new File(imgFile.getParent() + File.separator + "json" + File.separator + imgName + ".json");
            FileUtils.writeStringToFile(file,resultJson);
            ResultBean jsonResult = parseResult(resultJson);
            System.out.println(jsonResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testOCR() {
        String pathname = IMG_TEST_DIR_PATH + File.separator + "ocr";
        File imgDir = new File(pathname);
        assert imgDir.exists();
        String[] imgNames = imgDir.list();
        assert imgNames.length>0 ;
        try {
            String token = getToken();
            for (String imgName : imgNames) {
                File imgFile = new File(pathname + File.separator + imgName);
                if (!imgFile.isFile()||(!imgName.endsWith("jpg")&&!imgName.endsWith("png"))) {
                    continue;
                }
                String base64String = getBase64String(imgFile.getPath());

                String resultJson = AIService.characterRcognize(token, base64String);
                ResultBean jsonResult = parseResult(resultJson);
                File file = new File(pathname + File.separator
                        + "json" + File.separator + imgFile.getName() + ".json");
                FileUtils.writeStringToFile(file, resultJson);
                System.out.println(jsonResult);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}