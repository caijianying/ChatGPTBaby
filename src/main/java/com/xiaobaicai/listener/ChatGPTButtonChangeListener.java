package com.xiaobaicai.listener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.xiaobaicai.toolwindow.ChatGPTWindow;
import org.apache.commons.lang3.StringUtils;

/**
 * @author liguang
 * @date 2023/2/10 星期五 10:13 上午
 */
public class ChatGPTButtonChangeListener implements ChangeListener {

    private final ChatGPTWindow window;

    public ChatGPTButtonChangeListener(ChatGPTWindow window) {
        this.window = window;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (StringUtils.isBlank(window.getAnwserTextArea().getText())) {
            window.getAnwserTextArea().setText("等待应答中...");
            return;
        }
    }
}
