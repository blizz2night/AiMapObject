package com.tinno.aimap;

import android.support.annotation.NonNull;

import com.tinno.aimap.model.BookBean;
import com.tinno.aimap.model.BrandBean;
import com.tinno.aimap.model.CarBean;
import com.tinno.aimap.model.DishesBean;
import com.tinno.aimap.model.MovieBean;
import com.tinno.aimap.model.MusicBean;
import com.tinno.aimap.model.PlantBean;
import com.tinno.aimap.model.RecgResponseBean;
import com.tinno.aimap.model.ScanBean;
import com.tinno.aimap.service.BaiduService;
import com.tinno.aimap.service.ErrorCode;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.tinno.aimap.service.BaiduService.getToken;
import static com.tinno.aimap.service.BaiduService.getTokenJson;
import static com.tinno.aimap.service.BaiduService.parseRecgResult;
import static com.tinno.aimap.service.BaiduService.parseToken;
import static com.tinno.aimap.utils.GsonUtil.getBase64String;
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
        String name = "book2.jpeg";
        String folder = Catalog.BOOK/*+File.separator+Catalog.POSITIVE*/;
        String input = getInputPath(folder, name);
        String output = getOutputPath(folder, name);
        File imgFile = new File(input);
        assert imgFile.exists();
        try {
            String base64String = getBase64String(imgFile.getPath());
            String token = getToken();
            String resultJson = BaiduService.objectRecognize(token, base64String);
            System.out.println(resultJson);
            File file = new File(output);
            FileUtils.writeStringToFile(file,resultJson);
            RecgResponseBean jsonResult = parseRecgResult(resultJson);
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
        byte[] bytes = null;
        File imgFile = new File(TEST_IMG_DIR + File.separator + list[0]);
        assert imgFile.exists();
        File tokenFile = new File(TEST_JSON_DIR + File.separator + "token.json");
        assert imgDir.exists();
        try {
            String base64String = getBase64String(imgFile.getPath());
            String json = FileUtils.readFileToString(tokenFile);
            String token = parseToken(json);
            String resultJson = BaiduService.objectRecognize(token, base64String);
            File file = new File(TEST_JSON_DIR +File.separator+imgFile.getName()+".json");
            FileUtils.writeStringToFile(file,resultJson);
            RecgResponseBean jsonResult = parseRecgResult(resultJson);
            System.out.println(jsonResult.getErrorMsg());
            assert jsonResult.getErrorCode()== ErrorCode.ACCESS_TOKEN_EXPIRED;

            token = getToken();
            resultJson = BaiduService.objectRecognize(token, base64String);
            jsonResult = parseRecgResult(resultJson);
            System.out.println(jsonResult.getErrorMsg());
            assert jsonResult.getErrorCode()==ErrorCode.SUCCESS;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCharacterRecognition() {
        String folder = Catalog.OCR;
        String imgName = "02.png";
        String path = getInputPath(folder, imgName);
        File imgFile = new File(path);
        assert imgFile.exists();
        try {
            String base64String = getBase64String(imgFile.getPath());
            String token = getToken();
            String resultJson = BaiduService.characterRecognize(token, base64String);
            File file = new File(imgFile.getParent() + File.separator + "json" + File.separator + imgName + ".json");
            FileUtils.writeStringToFile(file,resultJson);
            System.out.println(resultJson);
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
    public void testDishes() {
        try {
            String token = getToken();
            testRecgInFolder(Catalog.DISHES, token);
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
                String resultJson = BaiduService.characterRecognize(token, base64String);
                RecgResponseBean jsonResult = parseRecgResult(resultJson);
                System.out.println(jsonResult);
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
                File f = new File(inputPath, imgName);
                if (f.isDirectory()) {
                    testRecgInFolder(folder + File.separator + imgName, token);
                } else if ((imgName.endsWith("jpeg") || imgName.endsWith("jpg") || imgName.endsWith("png"))) {
                    File imgFile = new File(inputPath + File.separator + imgName);
                    String base64String = getBase64String(imgFile.getPath());
                    String resultJson = BaiduService.objectRecognize(token, base64String);
                    RecgResponseBean jsonResult = parseRecgResult(resultJson);
                    System.out.println(jsonResult);
                    System.out.println();
                    assert jsonResult.getErrorCode() == ErrorCode.SUCCESS;
                    File file = new File(outputPath + File.separator + imgFile.getName() + ".json");
                    FileUtils.writeStringToFile(file, resultJson);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetFaceBean() {
        String inputPath = "src/test/res/raw/img/face/json/face.jpg.json";
        try {
            String s = FileUtils.readFileToString(new File(inputPath));
            RecgResponseBean recgResponseBean = parseRecgResult(s);
            System.out.println(recgResponseBean);
            Object face = recgResponseBean.getResult().getFace();
            System.out.println(face);
//            Map<String, Object> result = recgResultBean.getResult();
//            Map<String,Object> face = (Map<String, Object>) result.get(Catalog.FACE);
//            String baike = (String) face.get("baike");
//            FaceBean faceBean = getInstance().fromJson(baike, FaceBean.class);
//            System.out.println(faceBean.getBaike());
//
//            Map<String,Object> o = getInstance().fromJson(faceBean.getBaike(), type.getType());
//            String abst = (String) o.get("abstract");
//            System.out.println(abst);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGetCarBean() {
        String inputPath = "src/test/res/raw/img/car/json/car.jpg.json";
        try {
            String s = FileUtils.readFileToString(new File(inputPath));
            RecgResponseBean recgResponseBean = parseRecgResult(s);
            List<CarBean> value = recgResponseBean.getResult().getCar();
            System.out.println(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetAnimalBean() {
        String inputPath = "src/test/res/raw/img/animal/json/animal.jpg.json";
        try {
            String s = FileUtils.readFileToString(new File(inputPath));
            RecgResponseBean recgResponseBean = parseRecgResult(s);
            RecgResponseBean.Result result = recgResponseBean.getResult();
            Object o = result.getAnimal();

            System.out.println(o);
//            Object animal = result.get(Catalog.ANIMAL);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testGetPlantBean() {
        String inputPath = "src/test/res/raw/img/plant/json/plant.jpeg.json";
        try {
            String s = FileUtils.readFileToString(new File(inputPath));
            RecgResponseBean recgResponseBean = parseRecgResult(s);
            System.out.println(recgResponseBean);
            List<PlantBean> plants = recgResponseBean.getResult().getPlant();
            System.out.println(plants);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetDishBean() {
        String inputPath = "src/test/res/raw/img/dishes/json/dishes02.jpeg.json";
        try {
            String s = FileUtils.readFileToString(new File(inputPath));
            RecgResponseBean recgResponseBean = parseRecgResult(s);
            System.out.println(recgResponseBean);
            List<DishesBean> dishes = recgResponseBean.getResult().getDishes();
            System.out.println(dishes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetBrandBean() {
        String inputPath = "src/test/res/raw/img/brand/json/3.jpeg.json";
        try {
            String s = FileUtils.readFileToString(new File(inputPath));
            RecgResponseBean recgResponseBean = parseRecgResult(s);
            System.out.println(recgResponseBean);
            BrandBean brand = recgResponseBean.getResult().getBrandLogo();
            System.out.println(brand);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetBookBean() {
        String inputPath = "src/test/res/raw/img/book/json/book.jpeg.json";
        try {
            String s = FileUtils.readFileToString(new File(inputPath));
            RecgResponseBean recgResponseBean = parseRecgResult(s);
            List<BookBean> book = recgResponseBean.getResult().getBook();
            System.out.println(book.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //TODO moive=>movie
    @Test
    public void testGetMovieBean() {
        String inputPath = "src/test/res/raw/img/movie/json/movie.jpg.json";
        try {
            String s = FileUtils.readFileToString(new File(inputPath));
            RecgResponseBean recgResponseBean = parseRecgResult(s);
            System.out.println(recgResponseBean);
            System.out.println();
            List<MovieBean> movie = recgResponseBean.getResult().getMovie();
            System.out.println(movie);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGetMusicBean() {
        String inputPath = "src/test/res/raw/img/music/json/music.jpg.json";
        try {
            String s = FileUtils.readFileToString(new File(inputPath));
            RecgResponseBean recgResponseBean = parseRecgResult(s);
            List<MusicBean> music = recgResponseBean.getResult().getMusic();
            System.out.println(music.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    @Test
//    public void testGetProductBean() {
//        String inputPath = "src/test/res/raw/img/product/json/coffee.jpg.json";
//        try {
//            String s = FileUtils.readFileToString(new File(inputPath));
//            RecgResultBean recgResultBean = parseRecgResult(s);
//            List<ProductBean> product = recgResultBean.getResult().getProduct();
//            System.out.println(product);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    @Test
    public void testGetScanBean() {
        String inputPath = "src/test/res/raw/img/scan/json/tiantan.jpg.json";
        try {
            String s = FileUtils.readFileToString(new File(inputPath));
            RecgResponseBean recgResponseBean = parseRecgResult(s);
            List<ScanBean> scan = recgResponseBean.getResult().getScan();
            System.out.println(scan);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}