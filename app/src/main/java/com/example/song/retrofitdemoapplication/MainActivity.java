package com.example.song.retrofitdemoapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.song.retrofitdemoapplication.Retrofit2.httpservice1;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void netConnection(){
        //2
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //3 具体使用
        httpservice1 mhttpservice1 = retrofit.create(httpservice1.class);

        //@FormUrlEncoded(表单提交)
        Call<ResponseBody> call = mhttpservice1.testFormUrlEncode1("",2);



//        RequestBody name = RequestBody.create("1234","Carson");


        //@Field
        Call<ResponseBody> fieldCall = mhttpservice1.testFiled("admin",12);
        //@FieldMap
        Map<String, Object> map = new HashMap<>();
        map.put("username","carson");
        map.put("age","age");
        Call<ResponseBody> fieldMapCall = mhttpservice1.testFiledMap(map);


        //@Multipart
        MediaType textType = MediaType.parse("text/plain");
        RequestBody nameBody = RequestBody.create(textType, "Carson");
        RequestBody ageBody = RequestBody.create(textType, "24");
        RequestBody file = RequestBody.create(
                MediaType.parse("application/octet-stream"), "这里是模拟文件的内容");
        Call<ResponseBody> multipart = mhttpservice1.testMultipart(nameBody, 123);

        //@MultipartMap
        // 实现和上面同样的效果
        Map<String, RequestBody> fileUpload2Args = new HashMap<>();
        fileUpload2Args.put("name", ageBody);
        fileUpload2Args.put("age", ageBody);
        //这里并不会被当成文件，因为没有文件名(包含在Content-Disposition请求头中)，但上面的 filePart 有
        //fileUpload2Args.put("file", file);
        Call<ResponseBody> multipartmapCall = mhttpservice1.testMultipartMap(fileUpload2Args);

    }


    public void createRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 支持RxJava平台
                .build();

    }





}
