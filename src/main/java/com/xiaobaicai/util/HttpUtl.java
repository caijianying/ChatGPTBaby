package com.xiaobaicai.util;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.Lists;
import lombok.Data;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author liguang
 * @date 2023/3/8 星期三 9:34 下午
 */
public class HttpUtl {

    public static String getChatGPTResp3_5(String question) {
        String bodyTpls = "{\n"
            + "  \"model\": \"text-davinci-003\",\n"
            + "  \"prompt\": \"%s\",\n"
            + "  \"temperature\": 1,\n"
            + "  \"max_tokens\": 1000\n"
            + "}";

        RequestBody requestBody = new FormBody.Builder()//创建表单
            .add("model", "gpt-3.5-turbo-0301")        //传入需要提交的数据
            .add("messages", "[{\"role\": \"IT\", \"content\": \"%s\"}]")        //传入需要提交的数据
            .add("stream", "true")        //传入需要提交的数据
            .build();
        String token = "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";
        OkHttpClient client = new OkHttpClient();    //实例化OkHttpClient 对象
        Request request = new Request.Builder()        //创建Request对象的Builder模式
            .url("https://api.openai.com/v1/chat/completions")    //设置访问的网址
            //.get()		//设置为GET请求  默认为GET请求，可以忽略
            .post(requestBody)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", String.format("Bearer %s", token))
            .build();    //创建Request对象
        Call call = client.newCall(request);    //通过request的对象去构造得到一个Call对象
        //call.enqueue(new Callback() {	//调用call的enqueue(),进行异步请求
        //    @Override
        //    public void onFailure(Call call, IOException e) {
        //        //错误回调，通过e.getMessage()可以拿到错误信息
        //        System.out.println("onFailure: "+e.getMessage());
        //        e.printStackTrace();
        //    }
        //
        //    @Override
        //    public void onResponse(Call call, Response response) throws IOException {
        //        //成功回调,在这里我们可以拿到服务器返回的数据
        //        System.out.println("onResponse: "+response.body().string());
        //    }
        //});

        String str = "";
        try {
            Response execute = call.execute();
            str = execute.body().string();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        String[] split = str.split("data: ");
        StringBuilder sb = new StringBuilder();
        for (String s : split) {
            if (s.contains("[DONE]")) {
                break;
            }
            com.alibaba.fastjson.JSONObject jobj = com.alibaba.fastjson.JSON.parseObject(s);
            if (jobj == null) {
                continue;
            }
            JSONArray jarr = jobj.getJSONArray("choices");
            if (jarr == null) {
                continue;
            }
            for (int i = 0; i < jarr.size(); i++) {
                JSONObject delta = jarr.getJSONObject(i).getJSONObject("delta");
                if (delta != null) {
                    String content = delta.getString("content");
                    if (content != null) {
                        sb.append(content);
                    }
                }
            }
        }
        return sb.toString();
    }
}
