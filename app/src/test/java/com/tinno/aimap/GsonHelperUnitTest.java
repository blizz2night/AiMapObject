package com.tinno.aimap;

import com.tinno.aimap.model.BookBean;
import com.tinno.aimap.model.BrandBean;
import com.tinno.aimap.model.CarBean;
import com.tinno.aimap.model.DishesBean;
import com.tinno.aimap.model.MovieBean;
import com.tinno.aimap.model.MusicBean;
import com.tinno.aimap.model.PlantBean;
import com.tinno.aimap.model.RecgResponseBean;
import com.tinno.aimap.model.ScanBean;
import com.tinno.aimap.utils.GsonHelper;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * test parse jsonString to Beans
 */
public class GsonHelperUnitTest {

    @Test
    public void testReadFile() {
        File imgDir = new File(TestPath.TEST_IMG_DIR);
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

    @Test
    public void testGetFaceBean() {
        String inputPath = "src/test/res/raw/img/face/json/face.jpg.json";
        try {
            String s = FileUtils.readFileToString(new File(inputPath));
            RecgResponseBean recgResponseBean = GsonHelper.getRecgResponseBean(s);
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
            RecgResponseBean recgResponseBean = GsonHelper.getRecgResponseBean(s);
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
            RecgResponseBean recgResponseBean = GsonHelper.getRecgResponseBean(s);
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
            RecgResponseBean recgResponseBean =GsonHelper.getRecgResponseBean(s);
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
            RecgResponseBean recgResponseBean = GsonHelper.getRecgResponseBean(s);
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
            RecgResponseBean recgResponseBean =GsonHelper.getRecgResponseBean(s);
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
            RecgResponseBean recgResponseBean = GsonHelper.getRecgResponseBean(s);
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
            RecgResponseBean recgResponseBean = GsonHelper.getRecgResponseBean(s);
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
            RecgResponseBean recgResponseBean =GsonHelper.getRecgResponseBean(s);
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
            RecgResponseBean recgResponseBean = GsonHelper.getRecgResponseBean(s);
            List<ScanBean> scan = recgResponseBean.getResult().getScan();
            System.out.println(scan);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
