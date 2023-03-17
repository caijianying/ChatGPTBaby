package com.xiaobaicai.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.xiaobaicai.cache.ChatGPTCache;
import com.xiaobaicai.model.ChatGPT3_5ErrorModel;

/**
 * @author liguang
 * @date 2023/3/8 星期三 9:34 下午
 */
public class HttpUtl {

    public static String getChatGPT_3_5_Turbo(String question) {
        String token = ChatGPTCache.getInstance().openAiAuthKey;
        HttpRequest post = HttpUtil.createPost("https://api.openai.com/v1/chat/completions");
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", String.format("Bearer %s", token));
        String bodyTpls = "{\n"
            + "  \"model\": \"gpt-3.5-turbo-0301\",\n"
            + "  \"messages\": [{\"role\": \"assistant\", \"content\": \"%s\"}],\n"
            + "  \"stream\": true\n"
            + "}";
        HttpResponse execute;
        String str;
        try {
            execute = post.timeout(10_000).auth(String.format("Bearer %s", token))
                .body(
                    String.format(bodyTpls, question)).execute();
            str = execute.body();
        } catch (HttpException ex) {
            MessageUtil.error("请求服务器失败！");
            return null;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }


        if (JSONUtil.isTypeJSON(str)){
            ChatGPT3_5ErrorModel gpt3_5ErrorModel = JSONObject.parseObject(str, ChatGPT3_5ErrorModel.class);
            if (gpt3_5ErrorModel != null) {
                return gpt3_5ErrorModel.getError().getMessage();
            }
        }
        System.out.println(str);

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
