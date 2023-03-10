//package com.xiaobaicai.util;
//
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.net.http.HttpClient;
//import java.net.http.HttpClient.Version;
//import java.nio.charset.StandardCharsets;
//import java.security.KeyManagementException;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//
//import cn.hutool.core.collection.CollectionUtil;
//import cn.hutool.http.HttpException;
//import cn.hutool.http.HttpRequest;
//import cn.hutool.http.HttpResponse;
//import cn.hutool.http.HttpUtil;
//import com.xiaobaicai.cache.ChatGPTCache;
//import com.xiaobaicai.model.ErrorModel;
//import com.xiaobaicai.model.TextCompletionModel;
//import com.xiaobaicai.model.TextCompletionModel.Choice;
//import com.xiaobaicai.model.TextCompletionModel3_5;
//import com.xiaobaicai.model.TextCompletionModel3_5.Choice3_5;
//import com.xiaobaicai.model.TextCompletionModel3_5.Dela;
//
//public class HttpUtils {
//
//    protected static final int SOCKET_TIMEOUT = 10000; // 10S
//
//    public static String getChatGPTResp(String question) {
//        // ChatGPT
//        HttpRequest post = HttpUtil.createPost("https://api.openai.com/v1/completions");
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Content-Type", "application/json");
//        String bodyTpls = "{\n"
//            + "  \"model\": \"text-davinci-003\",\n"
//            + "  \"prompt\": \"%s\",\n"
//            + "  \"temperature\": 1,\n"
//            + "  \"max_tokens\": 1000\n"
//            + "}";
//        HttpResponse execute;
//        try {
//            execute = post.timeout(SOCKET_TIMEOUT).auth(
//                String.format("Bearer %s", ChatGPTCache.getInstance().openAiAuthKey))
//                .body(
//                    String.format(bodyTpls, question)).execute();
//        } catch (HttpException ex) {
//            MessageUtil.error("请求服务器失败！");
//            return null;
//        }
//
//        String respContent = "";
//        if (execute.isOk()) {
//            TextCompletionModel model = JSON.parseObject(execute.body(), TextCompletionModel.class);
//            List<Choice> choices = model.getChoices();
//            String text = "";
//            if (CollectionUtil.isNotEmpty(choices)) {
//                text = choices.get(0).getText();
//                if (choices.get(0).getText().startsWith("?")) {
//                    text = choices.get(0).getText().substring(1);
//                }
//            }
//            respContent = text;
//        } else {
//            ErrorModel model = JSON.parseObject(execute.body(), ErrorModel.class);
//            respContent = model.getMessage();
//        }
//        return respContent;
//    }
//
//    public static String getChatGPT3_5Resp(String question) {
//        //// ChatGPT
//        //HttpRequest post = HttpUtil.createPost("https://api.openai.com/v1/chat/completions");
//        //Map<String, String> headers = new HashMap<>();
//        //headers.put("Content-Type", "application/json");
//        //String bodyTpls = "{\n"
//        //    + "  \"model\": \"gpt-3.5-turbo-0301\",\n"
//        //    + "  \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}],\n"
//        //    + "  \"stream\": true\n"
//        //    + "}";
//        //HttpResponse execute = null;
//        //System.out.println("准备发送请求！");
//        //try {
//        //    execute = post.timeout(10_000).auth(String.format("Bearer %s", ChatGPTCache.getInstance().openAiAuthKey))
//        //        .body(
//        //            String.format(bodyTpls, question)).execute();
//        //} catch (HttpException ex) {
//        //    MessageUtil.error("请求服务器失败！");
//        //    return null;
//        //} catch (Throwable ex) {
//        //    ex.printStackTrace();
//        //    return null;
//        //}
//        //
//        //String respContent = "";
//        //if (execute.isOk()) {
//        //    TextCompletionModel3_5 model = JSON.parseObject(execute.body(), TextCompletionModel3_5.class);
//        //    List<Choice3_5> choices = model.getChoices();
//        //    String text = "";
//        //    if (CollectionUtil.isNotEmpty(choices)) {
//        //        Dela dela = choices.get(0).getDelta();
//        //        if (dela != null && dela.getContent() != null) {
//        //            text = dela.getContent();
//        //        }
//        //    }
//        //    respContent = text;
//        //} else {
//        //    ErrorModel model = JSON.parseObject(execute.body(), ErrorModel.class);
//        //    respContent = model.getMessage();
//        //}
//        //return respContent;
//
//        HttpRequest getRequest = HttpRequest.newBuilder()
//            .
//
//
//        // ChatGPT
//        HttpRequest post = HttpUtil.createPost("https://api.openai.com/v1/chat/completions");
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Content-Type", "application/json");
//        String bodyTpls = "{\n"
//            + "  \"model\": \"gpt-3.5-turbo-0301\",\n"
//            + "  \"messages\": [{\"role\": \"IT\", \"content\": \"%s\"}],\n"
//            + "  \"stream\": true\n"
//            + "}";
//        HttpResponse execute;
//        try {
//            String token = "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";
//            execute = post.timeout(10_000).auth(String.format("Bearer %s", token))
//                .body(
//                    String.format(bodyTpls, question)).execute();
//        } catch (HttpException ex) {
//            MessageUtil.error("请求服务器失败！");
//            return null;
//        } catch (Throwable e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        String[] split = execute.body().split("data: ");
//        StringBuilder sb = new StringBuilder();
//        for (String s : split) {
//            if (s.contains("[DONE]")) {
//                break;
//            }
//            com.alibaba.fastjson.JSONObject jobj = com.alibaba.fastjson.JSON.parseObject(s);
//            if (jobj == null) {
//                continue;
//            }
//            JSONArray jarr = jobj.getJSONArray("choices");
//            if (jarr == null) {
//                continue;
//            }
//            for (int i = 0; i < jarr.size(); i++) {
//                JSONObject delta = jarr.getJSONObject(i).getJSONObject("delta");
//                if (delta != null) {
//                    String content = delta.getString("content");
//                    if (content != null) {
//                        sb.append(content);
//                    }
//                }
//            }
//        }
//        return sb.toString();
//    }
//}
