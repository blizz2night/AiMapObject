package com.tinno.aimap.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.tinno.aimap.model.AnimalBean;
import com.tinno.aimap.model.CarBean;
import com.tinno.aimap.model.ComplexJsonObjBean;
import com.tinno.aimap.model.DishesBean;
import com.tinno.aimap.model.FaceBean;
import com.tinno.aimap.model.LocationBean;
import com.tinno.aimap.model.OcrResponseBean;
import com.tinno.aimap.model.PlantBean;
import com.tinno.aimap.model.RecgResponseBean;
import com.tinno.aimap.model.ResponseBean;
import com.tinno.aimap.model.TokenBean;

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

public class GsonHelper {
    private static class GsonHolder{
        private static final Gson INSTANCE = new GsonBuilder()
//                .registerTypeAdapter(new TypeToken<List<BrandBean>>(){}.getType(), new BrandDeserializer())
                .registerTypeAdapter(new TypeToken<List<PlantBean>>(){}.getType(),
                        new MyComplexJsonDeserializer<PlantBean>())
                .registerTypeAdapter(new TypeToken<List<DishesBean>>(){}.getType(),
                        new MyComplexJsonDeserializer<DishesBean>())
                .registerTypeAdapter(new TypeToken<List<AnimalBean>>(){}.getType(),
                        new MyComplexJsonDeserializer<AnimalBean>())
                .registerTypeAdapter(new TypeToken<List<CarBean>>(){}.getType(),
                        new MyComplexJsonDeserializer<CarBean>())
                .registerTypeAdapter(FaceBean.class,
                        new FaceBeanDeserializer())
                .create();
    }

    public static Gson getInstance(){
        return GsonHolder.INSTANCE;
    }

    public static String getBase64String(String path) throws IOException {
        byte[] bytes = FileUtils.readFileToByteArray(new File(path));
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static TokenBean getTokenBean(String json) throws IOException {
        return getInstance().fromJson(json, TokenBean.class);
    }

    public static RecgResponseBean getRecgResponseBean(String jsonResult) {
        return getInstance().fromJson(jsonResult, RecgResponseBean.class);
    }

    public static OcrResponseBean getOcrResponseBean(String jsonResult) {
        return getInstance().fromJson(jsonResult, OcrResponseBean.class);
    }

    public static ResponseBean getResponseBean(String jsonResult) {
        return getInstance().fromJson(jsonResult, ResponseBean.class);
    }

//    public static class BrandDeserializer implements JsonDeserializer<List<BrandBean>> {
//        @SuppressLint("NewApi")
//        @Override
//        public List<BrandBean> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//            List<BrandBean> list = new ArrayList<>();
//            if (json.isJsonArray()) {
//                JsonArray jsonArray = json.getAsJsonArray();
//                for (JsonElement jsonElement : jsonArray) {
//                    BrandBean brandBean = GsonUtil.getInstance().fromJson(jsonElement, BrandBean.class);
//                    if (brandBean != null) {
//                        list.add(brandBean);
//                    }
//                }
//            } else {
//                BrandBean brandBean = GsonUtil.getInstance().fromJson(json, BrandBean.class);
//                if (brandBean != null) {
//                    list.add(brandBean);
//                }
//            }
//
//            return list.size() > 0 ? list : null;
//        }
//    }

    public static class MyComplexJsonDeserializer<T extends ComplexJsonObjBean> implements JsonDeserializer<List<T>> {
        @Override
        public List<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            List<T> list = new ArrayList<>();
            ComplexJsonObjBean.Outter outter = GsonHelper.getInstance().fromJson(json, ComplexJsonObjBean.Outter.class);
            List<ComplexJsonObjBean.Outter.BaikeBean> baikes = outter.getBaike();
            for (ComplexJsonObjBean.Outter.BaikeBean baike : baikes) {
                String name = baike.getKeyword();
                if (name != null && !"".equals(name)) {
                    if (typeOfT instanceof ParameterizedType) {
                        Type[] actualTypeArguments = ((ParameterizedType) typeOfT).getActualTypeArguments();
                        if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                            Class<T> clazz = (Class<T>) actualTypeArguments[0];
                            try {
                                // Get constructor of (new *Bean(String name))
                                Constructor<T> constructor = clazz.getConstructor(String.class);
                                T t = constructor.newInstance(name);
                                String valueStr = baike.getValue();
                                ComplexJsonObjBean.Inner value = GsonHelper.getInstance().fromJson(valueStr, ComplexJsonObjBean.Inner.class);
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
            FaceBean faceBean = null;
            FaceBean.Outter outter = GsonHelper.getInstance().fromJson(json, FaceBean.Outter.class);
            if (outter == null) {
                return null;
            }
            String baike = outter.getBaike();
            if (baike == null) {
                return null;
            }
            FaceBean.Inner inner = GsonHelper.getInstance().fromJson(baike, FaceBean.Inner.class);
            if (inner == null) {
                return null;
            }
            String name = inner.getName();
            if (name != null && !"".equals(name)) {
                faceBean = new FaceBean(name);
                faceBean.setThumbUrl(inner.getTnurl());
                faceBean.setLocation(new LocationBean(inner.getFace_x(),
                        inner.getFace_y(), inner.getFace_w(), inner.getFace_h()));
                faceBean.setScore(inner.getScore());
                baike = inner.getBaike();
                if (baike != null && !"".equals(baike)) {
                    FaceBean.FurtherInner furtherInner = GsonHelper.getInstance().fromJson(baike, FaceBean.FurtherInner.class);
                    faceBean.setLinkUrl(furtherInner.getLinkUrl());
                    faceBean.setDescription(furtherInner.getDescription());
                }
            }
            return faceBean;
        }
    }

}
