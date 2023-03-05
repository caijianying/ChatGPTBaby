package com.xiaobaicai.listener;

import com.xiaobaicai.toolwindow.ChatGPTBabyWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author caijy
 * @description TODO
 * @date 2023/3/5 星期日 10:02 下午
 */
public class ChatGPTSendBtnActionListener extends AbstractAction {

    private ChatGPTBabyWindow window;

    public ChatGPTSendBtnActionListener(ChatGPTBabyWindow window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = this.window.getSendText().getText();
        System.out.println("ChatGPTSendBtnActionListener.actionPerformed: " + text);
        this.window.showHis();
    }
}
