package com.tinno.aimap.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;
import java.util.List;

public class RecgResultBean {
    private Result result;
    @SerializedName("error_code")
    private int errorCode;
    @SerializedName("error_msg")
    private String errorMsg;
    private String support;
    private String logid;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public static class Result {
        @SerializedName(value = "result_page")
        private String imgSearchPageUrl;
        private List<ScanBean> scan;
        private List<BookBean> book;
        private List<MovieBean> movie;
        private List<MusicBean> music;
        private List<ProductBean> product;

        private BrandBean brandLogo;
        private FaceBean face;
        private CarBean car;
        private DishesBean dishes;
        private PlantBean plant;
        private AnimalBean animal;

        public String getImgSearchPageUrl() {
            return imgSearchPageUrl;
        }

        public void setiImgSearchPageUrl(String imgSearchPageUrl) {
            this.imgSearchPageUrl = imgSearchPageUrl;
        }

        public List<ScanBean> getScan() {
            return scan;
        }

        public void setScan(List<ScanBean> scan) {
            this.scan = scan;
        }

        public List<BookBean> getBook() {
            return book;
        }

        public void setBook(List<BookBean> book) {
            this.book = book;
        }

        public List<MovieBean> getMovie() {
            return movie;
        }

        public void setMovie(List<MovieBean> movie) {
            this.movie = movie;
        }

        public List<MusicBean> getMusic() {
            return music;
        }

        public void setMusic(List<MusicBean> music) {
            this.music = music;
        }

        public List<ProductBean> getProduct() {
            return product;
        }

        public void setProduct(List<ProductBean> product) {
            this.product = product;
        }

        public BrandBean getBrandLogo() {
            return brandLogo;
        }

        public void setBrandLogo(BrandBean brandLogo) {
            this.brandLogo = brandLogo;
        }

        public FaceBean getFace() {
            return face;
        }

        public void setFace(FaceBean face) {
            this.face = face;
        }

        public CarBean getCar() {
            return car;
        }

        public void setCar(CarBean car) {
            this.car = car;
        }

        public DishesBean getDishes() {
            return dishes;
        }

        public void setDishes(DishesBean dishes) {
            this.dishes = dishes;
        }

        public PlantBean getPlant() {
            return plant;
        }

        public void setPlant(PlantBean plant) {
            this.plant = plant;
        }

        public AnimalBean getAnimal() {
            return animal;
        }

        public void setAnimal(AnimalBean animal) {
            this.animal = animal;
        }

        @Override
        public String toString() {
            return "Result{" +'\n'+
                    "imgSearchPageUrl='" + imgSearchPageUrl + '\'' +'\n'+
                    ", scan=" + scan +'\n'+
                    ", book=" + book +'\n'+
                    ", movie=" + movie +'\n'+
                    ", music=" + music +'\n'+
                    ", product=" + product +'\n'+
                    ", brandLogo=" + brandLogo +'\n'+
                    ", face=" + face +'\n'+
                    ", car=" + car +'\n'+
                    ", dishes=" + dishes +'\n'+
                    ", plant=" + plant +'\n'+
                    ", animal=" + animal +'\n'+
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RecgResultBean{" +
                "result=" + result +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", support='" + support + '\'' +
                ", logid='" + logid + '\'' +
                '}';
    }

    public static class MyDeserializer implements JsonDeserializer<RecgResultBean> {

        @Override
        public RecgResultBean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonArray scans = jsonObject.getAsJsonArray("scan");
            if (scans != null) {
                for (JsonElement jsonElement : scans) {
                    ScanBean scanBean = com.tinno.aimap.utils.Util.getGson().fromJson(jsonElement, ScanBean.class);
                }
            }
            return null;
        }
    }
}
