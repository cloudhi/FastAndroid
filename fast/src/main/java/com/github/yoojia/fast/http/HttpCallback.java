package com.github.yoojia.fast.http;

import java.io.IOException;

/**
 * Http api callback
 *
 * @author  yoojiachen@gmail.com
 * @version version 2015-04-27
 * @since   1.1
 */
public interface HttpCallback {

    /**
     * 在请求过程中发生IO异常时回调
     * @param ioe IO异常
     */
    void onErrors(IOException ioe);

    /**
     * 在获取响应后回调
     * @param statusCode 响应状态码
     * @param content 响应内容
     */
    void onResponse(int statusCode, String content);

    /**
     * 请求完成后调用
     */
    void onCompleted();

    /**
     * 请求开始时
     */
    void onStarted();
}
