package com.tinno.aimap.utils;

import android.annotation.SuppressLint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.tinno.aimap.model.AnimalBean;
import com.tinno.aimap.model.BrandBean;
import com.tinno.aimap.model.CarBean;
import com.tinno.aimap.model.ComplexJsonObjBean;
import com.tinno.aimap.model.DishesBean;
import com.tinno.aimap.model.FaceBean;
import com.tinno.aimap.model.LocationBean;
import com.tinno.aimap.model.PlantBean;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class GsonUtil {
    private static class GsonHolder{
        private static final Gson INSTANCE = new GsonBuilder()
                .registerTypeAdapter(
                        new TypeToken<List<BrandBean>>(){}.getType()
                        , new BrandDeserializer()
                )
                .registerTypeAdapter(new TypeToken<List<PlantBean>>(){}.getType(),new MyComplexJsonDeserializer<PlantBean>())
                .registerTypeAdapter(new TypeToken<List<DishesBean>>(){}.getType(),new MyComplexJsonDeserializer<DishesBean>())
                .registerTypeAdapter(new TypeToken<List<AnimalBean>>(){}.getType(),new MyComplexJsonDeserializer<AnimalBean>())
                .registerTypeAdapter(new TypeToken<List<CarBean>>(){}.getType(),new MyComplexJsonDeserializer<CarBean>())
                .registerTypeAdapter(FaceBean.class ,new FaceBeanDeserializer())
                .create();
    }

    public static Gson getInstance(){
        return GsonHolder.INSTANCE;
    }

    @SuppressLint("NewApi")
    public static String getBase64String(String path) throws IOException {
        byte[] bytes = FileUtils.readFileToByteArray(new File(path));
        return Base64.getEncoder().encodeToString(bytes);
    }


    public static class BrandDeserializer implements JsonDeserializer<List<BrandBean>> {
        @SuppressLint("NewApi")
        @Override
        public List<BrandBean> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null || json.isJsonNull()) {
                return null;
            }

            List<BrandBean> list = new ArrayList<>();
            if (json.isJsonArray()) {
                JsonArray jsonArray = json.getAsJsonArray();
                for (JsonElement jsonElement : jsonArray) {
                    BrandBean brandBean = GsonUtil.getInstance().fromJson(jsonElement, BrandBean.class);
                    if (brandBean != null) {
                        list.add(brandBean);
                    }
                }
            } else {
                BrandBean brandBean = GsonUtil.getInstance().fromJson(json, BrandBean.class);
                if (brandBean != null) {
                    list.add(brandBean);
                }
            }

            return list.size() > 0 ? list : null;
        }
    }

    public static class MyComplexJsonDeserializer<T extends ComplexJsonObjBean> implements JsonDeserializer<List<T>> {
        @SuppressLint("NewApi")
        @Override
        public List<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null || json.isJsonNull()) {
                return null;
            }
            List<T> list = new ArrayList<>();
            ComplexJsonObjBean.Outter outter = GsonUtil.getInstance().fromJson(json, ComplexJsonObjBean.Outter.class);
            List<ComplexJsonObjBean.Outter.BaikeBean> baikes = outter.getBaike();
            for (ComplexJsonObjBean.Outter.BaikeBean baike : baikes) {
                String name = baike.getKeyword();
                if (name != null && !"".equals(name)) {
                    if (typeOfT instanceof ParameterizedType) {
                        Type[] actualTypeArguments = ((ParameterizedType) typeOfT).getActualTypeArguments();
                        if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                            Class<T> clazz = (Class<T>) actualTypeArguments[0];
                            try {
                                Constructor<T> constructor = clazz.getConstructor(String.class);
                                T t = constructor.newInstance(name);
                                String valueStr = baike.getValue();
                                ComplexJsonObjBean.Inner value = GsonUtil.getInstance().fromJson(valueStr, ComplexJsonObjBean.Inner.class);
                                t.setDescription(value.getDescription());
                                t.setLinkUrl(value.getLinkUrl());
                                t.setThumbUrl(value.getPic_1());
                                list.add(t);
                            } catch (NoSuchMethodException |
                                    IllegalAccessException |
                                    InvocationTargetException |
                                    InstantiationException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }
            return list.size() > 0 ? list : null;
        }
    }

    public static class FaceBeanDeserializer implements JsonDeserializer<FaceBean> {

        @Override
        public FaceBean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json == null || json.isJsonNull()) {
                return null;
            }
            FaceBean faceBean = null;
            FaceBean.Outter outter = GsonUtil.getInstance().fromJson(json, FaceBean.Outter.class);
            if (outter == null) {
                return null;
            }
            String baike = outter.getBaike();
            if (baike == null) {
                return null;
            }
            FaceBean.Inner inner = GsonUtil.getInstance().fromJson(baike, FaceBean.Inner.class);
            if (inner == null) {
                return null;
            }
            String name = inner.getName();
            if (name != null && !"".equals(name)) {
                faceBean = new FaceBean(name);
                faceBean.setThumbUrl(inner.getTnurl());
                faceBean.setLocation(new LocationBean(inner.getFace_x(),
                        inner.getFace_y(), inner.getFace_w(), inner.getFace_h()));

                baike = inner.getBaike();
                if (baike != null && !"".equals(baike)) {
                    FaceBean.FurtherInner furtherInner = GsonUtil.getInstance().fromJson(baike, FaceBean.FurtherInner.class);
                    faceBean.setLinkUrl(furtherInner.getLinkUrl());
                    faceBean.setDescription(furtherInner.getDescription());
                }
            }
            return faceBean;
        }
    }

}
