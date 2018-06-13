package com.example.song.retrofitdemoapplication.Retrofit2;

import com.example.song.retrofitdemoapplication.been.Translation;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018/6/13.
 *
 * 1
 */

public interface httpservice1 {
    @HTTP(method = "" ,path = "")
    Call<ResponseBody> getCall(@Path("id") int id);

    @GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.1&q=car")
    Call<Translation> getCall();
    //@Get注解：采用Get方法发送网络请求

    //getCall() = 接受网络请求数据方法
    // 其中返回类型为Call<*>，*是接收数据的类（即上面定义的Translation类）
    // 如果想直接获得Responsebody中的内容，可以定义网络请求返回值为Call<ResponseBody>


    /**------标记------**/
    /**
     *表明是一个表单格式的请求（Content-Type:application/x-www-form-urlencoded）
     * <code>Field("username")</code> 表示将后面的 <code>String name</code> 中name的取值作为 username 的值
     */
    @POST("/form")
    @FormUrlEncoded
    Call<ResponseBody> testFormUrlEncode1(
            @Field("username") String name, @Field("age") int age);

    /**
     * {@link Part} 后面支持三种类型，{@link RequestBody}、{@link okhttp3.MultipartBody.Part} 、任意类型
     * 除 {@link okhttp3.MultipartBody.Part} 以外，其它类型都必须带上表单字段({@link okhttp3.MultipartBody.Part} 中已经包含了表单字段的信息)，
     */

    @POST("/form")
    @Multipart
    Call<ResponseBody> testFileUpload1(
            @Part("name") RequestBody request01, @Part("name")RequestBody requestBody02);


    /**       网络请求参数        **/
    //@Header & @Headers
// •作用：添加请求头 &添加不固定的请求头
// •具体使用如下：
    //Header
    @GET("user")
    Call<Translation> getUser(@Header("Authorization") String authorization);

    //Headers
    //@Headers("Authorization:authorization")
    @Headers({
            "Authorization:authorization",
            "Authorization:authorization"})
    @GET("user")
    Call<Translation> getUser();
    // 以上的效果是一致的。
// 区别在于使用场景和使用方式
// 1. 使用场景：@Header用于添加不固定的请求头，@Headers用于添加固定的请求头
// 2. 使用方式：@Header作用于方法的参数；@Headers作用于方法


    //@Body
// •作用：以 Post方式 传递 自定义数据类型 给服务器
// •特别注意：如果提交的是一个Map，那么作用相当于 @Field
//  不过Map要经过 FormBody.Builder 类处理成为符合 Okhttp 格式的表单
// 举例：查看Other.postBody();


    //@Field & @FiledMap
// •作用：发送 Post请求 时提交请求的表单字段
// •具体使用：与 @FormUrlEncoded 注解配合使用
    @POST("/form")
    @FormUrlEncoded
    Call<ResponseBody> testFiled(@Field("username") String name,
                                @Field("age") int age);
    //map的key作为表单的键
    @POST("/form")
    @FormUrlEncoded
    Call<ResponseBody> testFiledMap(@FieldMap Map<String, Object> map);

//@Part & @PartMap
    //作用：发送Post请求提交请求是表单字段
//    与@Field的区别：功能相同，但携带的参数类型更加丰富，包括数据流，所以适用于 有文件上传 的场景
    //•具体使用：与 @Multipart 注解配合使用
    @POST("/form")
    @Multipart
    Call<ResponseBody> testMultipart(@Part("name") RequestBody name,
                               @Part("age") int age);

//    PartMap 注解支持一个Map作为参数，支持 {@link RequestBody } 类型，
//    如果有其它的类型，会被{@link retrofit2.Converter}转换，如后面会介绍的 使用{@link com.google.gson.Gson} 的 {@link retrofit2.converter.gson.GsonRequestBodyConverter}
//    所以{@link MultipartBody.Part} 就不适用了,所以文件只能用<b> @Part MultipartBody.Part </b>
//
    @POST("/form")
    @Multipart
    Call<ResponseBody> testMultipartMap(@PartMap Map<String, RequestBody> tranMap);

    /**   @Query @QueryMap   **/
//    •作用：用于 @GET 方法的查询参数（Query = Url 中 ‘?’ 后面的 key-value）
    @GET("/")
    Call<String> cate(@Query("cate") String cate);

    /**   @Path      **/
// 作用：Url地址的缺省值
    @GET("users/{user}/repos")
    Call<ResponseBody> getBlog(@Path("user") String user);
    // 访问的API是：https://api.github.com/users/{user}/repos
    // 在发起请求时， {user} 会被替换为方法的第一个参数 user（被@Path注解作用）

    /** @url **/
//    作用：直接传入一个请求URL变量，用于URL设置
    @GET
    Call<ResponseBody> testUrlAndQuery(@Url String url, @Query("showAll") boolean showAll);
    // 当有URL注解时，@GET传入的URL就可以省略
    // 当GET、POST...HTTP等方法中没有设置Url时，则必须使用 {@link Url}提供








}


class Other{
    public void postBody(){
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("key", "value");
    }
}
