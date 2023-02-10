package com.xiaobaicai.listener;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import com.alibaba.fastjson.JSON;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.xiaobaicai.cache.ChatGPTCache;
import com.xiaobaicai.model.ErrorModel;
import com.xiaobaicai.model.TextCompletionModel;
import com.xiaobaicai.model.TextCompletionModel.Choice;
import com.xiaobaicai.toolwindow.ChatGPTWindow;
import com.xiaobaicai.util.MessageUtil;
import org.apache.commons.lang3.StringUtils;

public class ChatGPTButtonActionListener extends AbstractAction {

    private final ChatGPTWindow window;

    public ChatGPTButtonActionListener(ChatGPTWindow window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取原语言文本、原语言、和目标翻译语言
        String originalText = window.getOriginalTextArea().getText();
        String authKey = ChatGPTCache.getInstance().openAiAuthKey;
        if (StringUtils.isBlank(authKey)) {
            MessageUtil.infoOpenToolWindow("请先配置密钥！");
            return;
        }

        // ChatGPT
        HttpRequest post = HttpUtil.createPost("https://api.openai.com/v1/completions");
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        String bodyTpls = "{\n"
            + "  \"model\": \"text-davinci-003\",\n"
            + "  \"prompt\": \"%s\",\n"
            + "  \"temperature\": 0,\n"
            + "  \"max_tokens\": 1000\n"
            + "}";
        HttpResponse execute;
        try {
             execute = post.timeout(10_000).auth(String.format("Bearer %s", ChatGPTCache.getInstance().openAiAuthKey)).body(
                String.format(bodyTpls, originalText)).execute();
        }catch (HttpException ex){
            MessageUtil.error("请求服务器失败！");
            return;
        }

        if (execute.isOk()) {
            TextCompletionModel model = JSON.parseObject(execute.body(), TextCompletionModel.class);
            List<Choice> choices = model.getChoices();
            window.getAnwserTextArea().setText(CollectionUtil.isEmpty(choices) ? "" : choices.get(0).getText());
        } else {
            ErrorModel model = JSON.parseObject(execute.body(), ErrorModel.class);
            window.getAnwserTextArea().setText(model.getMessage());
        }
    }

}
