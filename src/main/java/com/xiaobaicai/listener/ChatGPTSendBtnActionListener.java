package com.xiaobaicai.listener;

import com.xiaobaicai.cache.ChatGPTCache;
import com.xiaobaicai.model.ChatGPTSwingWorker;
import com.xiaobaicai.toolwindow.ChatDemoWindow;
import com.xiaobaicai.util.HttpUtl;
import com.xiaobaicai.util.MessageUtil;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;

import java.awt.event.ActionEvent;

/**
 * @author caijy
 * @description TODO
 * @date 2023/3/5 星期日 10:02 下午
 */
public class ChatGPTSendBtnActionListener extends AbstractAction {

    private ChatDemoWindow window;

    public ChatGPTSendBtnActionListener(ChatDemoWindow window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String authKey = ChatGPTCache.getInstance().openAiAuthKey;
        if (StringUtils.isBlank(authKey)) {
            MessageUtil.infoOpenToolWindow("请先配置密钥！");
            return;
        }
        String originalText = this.window.getSendText().getText();
        System.out.println("ChatGPTSendBtnActionListener.actionPerformed: " + originalText);
        new ChatGPTSwingWorker(this.window, originalText, null).execute();
        String chatGPTResp = HttpUtl.getChatGPT_3_5_Turbo(originalText);
        System.out.println("getChatGPT_3_5_TurboResp:" + chatGPTResp);
        this.window.getSendText().setText(null);
        new ChatGPTSwingWorker(this.window, null, chatGPTResp).execute();
    }
}
