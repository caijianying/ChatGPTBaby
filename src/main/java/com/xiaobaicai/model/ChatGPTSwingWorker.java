package com.xiaobaicai.model;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import com.xiaobaicai.toolwindow.ChatDemoWindow;

/**
 * @author liguang
 * @date 2023/3/7 星期二 10:12 上午
 */
public class ChatGPTSwingWorker extends SwingWorker<String, String> {

    private String message;
    private String resp;

    private ChatDemoWindow window;

    public ChatGPTSwingWorker(ChatDemoWindow window, String message, String resp) {
        this.window = window;
        this.message = message;
        this.resp = resp;
    }

    @Override
    protected String doInBackground() throws InterruptedException {
        setProgress(1);
        publish(String.format("%s：%s \n", "你", message));

        TimeUnit.SECONDS.sleep(1);
        if (resp != null) {
            setProgress(2);
            publish(String.format("%s：%s \n", "AI", resp));
        }

        return "OK！";
    }

    @Override
    protected void process(List<String> chunks) {
        String message = chunks.get(0);
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.LIGHT_GRAY);
        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.FontSize, 15);
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        int len = this.window.getHistoryTextPane().getDocument().getLength();
        this.window.getHistoryTextPane().setCaretPosition(len);
        this.window.getHistoryTextPane().setCharacterAttributes(aset, false);
        this.window.getHistoryTextPane().replaceSelection(message);
    }

    @Override
    protected void done() {
        super.done();
    }
}
