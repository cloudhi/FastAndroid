package com.github.yoojia.fast.http;

import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

/**
 * Http API 客户端
 *
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-27
 * @since   1.1
 */
public class HttpClient {

    public static final String TAG = HttpClient.class.getSimpleName();

    private static final MediaType DEFAULT_TYPE = MediaType.parse("application/json;charset=utf-8; charset=utf-8");
    private static final String DEFAULT_UA = "YOOJIA.FAST/HTTP/1.1";
    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    private static final SparseArray<Call> REQUESTS = new SparseArray<>();

    private static String REMOTE_HOST;
    private static String USER_AGENT;
    private static MediaType MEDIA_TYPE;

    /**
     * 初始化全局参数
     * @param remoteHost 远程服务器地址
     * @param userAgent User-Agent
     * @param mediaType Media Type
     */
    public static void init(String remoteHost, String userAgent, String mediaType){
        REMOTE_HOST = remoteHost;
        USER_AGENT = userAgent;
        MEDIA_TYPE = TextUtils.isEmpty(mediaType) ? null : MediaType.parse(mediaType);
    }

    /**
     * 以POST方式提交数据到服务端
     * @param params 未做URL转码的参数列表
     * @param callback 回调接口
     */
    public static int post(String params, HttpCallback callback){
        if (TextUtils.isEmpty(REMOTE_HOST)) throw new IllegalArgumentException("Remote host not set !");
        final MediaType mediaType = MEDIA_TYPE == null ? DEFAULT_TYPE : MEDIA_TYPE;
        return post(REMOTE_HOST, params, mediaType, callback);
    }

    /**
     * POST到指定URL
     * @param url 目标URL
     * @param params 参数
     * @param mediaType MediaType
     * @param callback 回调接口
     * @return 返回一个请求ID
     */
    public static int post(String url, String params, MediaType mediaType, HttpCallback callback){
        final RequestBody body = RequestBody.create(mediaType, params);
        final Request request = new Request.Builder()
                .addHeader("User-Agent", TextUtils.isEmpty(USER_AGENT) ? DEFAULT_UA : USER_AGENT)
                .url(url)
                .post(body)
                .build();
        Call call = HTTP_CLIENT.newCall(request);
        final int requestId = call.hashCode();
        REQUESTS.put(requestId, call);
        call.enqueue(new RequestRunner(requestId, callback));
        return requestId;
    }

    /**
     * 根据一个请求ID，将此请求取消
     * @param requestId 请求ID
     */
    public static void cancel(int requestId){
        Call req = REQUESTS.get(requestId);
        if (req != null) req.cancel();
    }

    private static class RequestRunner implements Callback {

        private final HttpCallback mCallback;
        private final int mRequestId;

        private RequestRunner(int requestId, HttpCallback mCallback) {
            mRequestId = requestId;
            this.mCallback = mCallback;
            mCallback.onStarted();
        }

        @Override
        public void onFailure(Request request, IOException e) {
            Log.w(TAG, e);
            try{
                mCallback.onErrors(e);
            }finally {
                REQUESTS.remove(mRequestId);
                mCallback.onCompleted();
            }
        }

        @Override
        public void onResponse(Response response) throws IOException {
            final ResponseBody body = response.body();
            final int statusCode = response.code();
            final String content = body.string();
            try{
                mCallback.onResponse(statusCode, content);
            }finally {
                REQUESTS.remove(mRequestId);
                mCallback.onCompleted();
            }
        }
    }
}
