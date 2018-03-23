package home.smart.fly.scucommunity.util;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/3/17.
 */

public class HttpUtil {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    /*获取十条json信息*/
    public static void sendOkHttpRequest(String address, Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    /*向网络上传一个对象数组*/
    public static <Type> void postOkHttpRequestion(String adress, List<Type> typeList){
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();
        String json = gson.toJson(typeList);
        RequestBody body = RequestBody.create(JSON,json);

        Request request = new Request.Builder().url(adress).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
            }
        });


    }

    /*向网络上传一个对象数组*/
    public static <Type> void postOkHttpRefresh(String adress, List<Type> typeList,Callback callback){
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();
        String json = gson.toJson(typeList);
        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder().url(adress).post(body).build();
        client.newCall(request).enqueue(callback);

    }

    /*根据键值返回一个*/
    public static void postOkHttpgetdata(String adress, String key, String value, Callback callback){
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add(key,value );
        RequestBody body = builder.build();
        Request request = new Request.Builder().url(adress).post(body).build();
        client.newCall(request).enqueue(callback);
    }

}
