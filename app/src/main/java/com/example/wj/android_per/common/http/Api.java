package com.example.wj.android_per.common.http;

import android.text.TextUtils;
import android.util.Log;


import com.example.wj.android_per.BuildConfig;
import com.example.wj.android_per.common.http.adapter.StringValueAdapter;
import com.example.wj.android_per.common.http.json.JsonConverterFactory;
import com.example.wj.android_per.common.persistence.FastData;
import com.example.wj.android_per.common.view.DeviceUtil;
import com.example.wj.android_per.common.view.ToastSnackbarUtiles;
import com.google.gson.GsonBuilder;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.CacheControl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Api service
 * Created by  on 2017/3/16.
 */

public class Api {
    private ApiService apiService;
    private static final String PLATFORM = "android";
    private static final String CLIENT = "store";

    private Api(String type) {
        String baseUrl = "";
        switch (type) {
            case "API":
                baseUrl = BuildConfig.BASE_URL;
                break;
                default:
                    break;

        }

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(String.class, new StringValueAdapter());

        Retrofit retrofit = retrofitBuilder
                .baseUrl(baseUrl+"/")
                .client(createOkHttpClient(type))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build();

        switch (type) {
            case "API":
                apiService = retrofit.create(ApiService.class);
                break;
                default:
                    break;
        }
    }

    private static OkHttpClient createOkHttpClient(String type) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(10, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(30, TimeUnit.SECONDS);

        //addCache(httpClientBuilder);
       // addCheckNetworkChain(httpClientBuilder);

        httpClientBuilder.networkInterceptors()
                .add(chain -> {
                    Request.Builder req = chain.request().newBuilder();
                    req.addHeader("Accept-Charset", "utf-8");
                    req.addHeader("Content-Type", "CAPI".equals(type) ? "application/json; charset=UTF-8" : "application/x-www-form-urlencoded; charset=UTF-8")
                            .addHeader("Connection", "keep-alive")
                            .addHeader("Accept", "*/*")
                            .addHeader("x-version", BuildConfig.VERSION_NAME)
                            .addHeader("x-platform", PLATFORM)
                            .addHeader("x-client", CLIENT);

                   // addAuthorizationHeader(req);
                    Response response = chain.proceed(req.build());

                    return response.newBuilder().build();
                });
        httpClientBuilder.addInterceptor(
                new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE
                )
        );

        switch (type) {
            case "API":
                addNetworkInterceptorByErrorCode(httpClientBuilder);
                break;
        }
        return httpClientBuilder.build();
    }



    private static void addNetworkInterceptorByErrorCode(OkHttpClient.Builder httpClientBuilder) {
        httpClientBuilder.addNetworkInterceptor(chain -> {
            Charset UTF8 = Charset.forName("UTF-8");
            Response response = chain.proceed(chain.request());
            ResponseBody responseBody = response.body();
            Log.d("response", response.toString());
            String resultsBody = null;
            if (HttpHeaders.hasBody(response)) {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();

                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    try {
                        charset = contentType.charset(UTF8);
                    } catch (UnsupportedCharsetException e) {
                        e.printStackTrace();
                    }
                }
                resultsBody = buffer.clone().readString(charset);
            }
            return response;
        });
    }

    protected static void addCheckNetworkChain(OkHttpClient.Builder httpClientBuilder) {
        httpClientBuilder.addInterceptor(chain -> {

            Request request = chain.request();
            if (!DeviceUtil.isNetworkAvailable()) {

                Maybe.just("没有可用网络, 请连接网络后重试")
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(ToastSnackbarUtiles::show, throwable -> {
                            Log.e("Debug", "error", throwable);
                        });

                // @notice  由于服务器不支持缓存，所以这里其实是没有起作用，等服务器起作用就可以了
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            return chain.proceed(request)
                    .newBuilder()
                    .build();
        });

    }


   protected static void addAuthorizationHeader(Request.Builder req) {
        if (!TextUtils.isEmpty(FastData.getToken())) {
            String authorizationHeader = String.format(Locale.CHINA, "Bearer %s", FastData.getToken());
            req.addHeader("Authorization", authorizationHeader);
            Log.d("OkHttp", "Authorization: " + authorizationHeader);
        }
    }

    static Api api = null;

    public static ApiService getApiServiceInstance() {

        if (api == null) {
            api = new Api("API");
        }
        return api.getApiService();
    }

    private ApiService getApiService() {
        return apiService;
    }



    /**
     * jsonObject 类型
     */
    private static Retrofit initRetrofit(String type) {
        StringBuilder baseUrl = new StringBuilder();
        switch (type) {
            case "API":
                baseUrl.append(BuildConfig.BASE_URL);
                break;
                default:
                    break;
        }
        return new Retrofit.Builder()
                .baseUrl(baseUrl.toString()+"/")
                .addConverterFactory(JsonConverterFactory.create())
                .client(createOkHttpClient(type))
                .build();
    }

    /**
     * 创建 RetrofitManage 服务
     *
     * @return ApiService
     */
    public static ApiService createApiServiceJson() {
        return initRetrofit("API").create(ApiService.class);
    }

}
