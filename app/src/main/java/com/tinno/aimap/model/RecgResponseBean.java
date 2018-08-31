package com.tinno.aimap.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecgResponseBean extends ResponseBean {
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result {
        @SerializedName(value = "result_page")
        private String imgSearchPageUrl;
        private List<ScanBean> scan;
        private List<BookBean> book;
        @SerializedName(value = "movie", alternate = "moive")
        private List<MovieBean> movie;
        private List<MusicBean> music;
//        private List<ProductBean> product;

        private BrandBean brandLogo;
        private FaceBean face;
        private List<CarBean> car;
        private List<DishesBean> dishes;
        private List<PlantBean> plant;
        private List<AnimalBean> animal;

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

//        public List<ProductBean> getProduct() {
//            return product;
//        }
//
//        public void setProduct(List<ProductBean> product) {
//            this.product = product;
//        }

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

        public List<CarBean> getCar() {
            return car;
        }

        public void setCar(List<CarBean> car) {
            this.car = car;
        }

        public List<DishesBean> getDishes() {
            return dishes;
        }

        public void setDishes(List<DishesBean> dishes) {
            this.dishes = dishes;
        }

        public List<PlantBean> getPlant() {
            return plant;
        }

        public void setPlant(List<PlantBean> plant) {
            this.plant = plant;
        }

        public List<AnimalBean> getAnimal() {
            return animal;
        }

        public void setAnimal(List<AnimalBean> animal) {
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
//                    ", product=" + product +'\n'+
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
}
