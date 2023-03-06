package com.xiaobaicai.toolwindow;

import com.xiaobaicai.listener.ChatGPTSendBtnActionListener;
import lombok.Data;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author caijy
 * @description TODO
 * @date 2023/3/6 星期一 11:28 下午
 */
@Data
public class ChatDemoWindow {
    private JTextArea sendText;
    private JButton sendBtn;
    private JPanel mainPanel;
    private JTextArea historyTextArea;

    public ChatDemoWindow() {
        sendBtn.addActionListener(new ChatGPTSendBtnActionListener(this));
    }


    public void chat() {
        SwingWorker<String, String> task = new SwingWorker<String, String>() {

            @Override
            protected String doInBackground() throws Exception {
                for (int i = 0; i < 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    setProgress(i);
                    publish("good!");
                }
                return "下载完成！";
            }

            @Override
            protected void process(List<String> chunks) {
                String processValue = chunks.get(0);
                historyTextArea.append(processValue);
            }

            @Override
            protected void done() {
                String res = null;
                try {
                    res = get();
                } catch (Exception e) {

                }
                System.out.println("done... ");
                historyTextArea.append(res);
            }
        };
        task.execute();
    }


    public void showHis() {
        SwingWorker<String, Integer> task = new SwingWorker<String, Integer>() {

            @Override
            protected String doInBackground() throws Exception {
                for (int i = 0; i < 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    setProgress(i);
                    publish(i);
                }
                return "下载完成！";
            }

            @Override
            protected void process(List<Integer> chunks) {
                Integer processValue = chunks.get(0);
                historyTextArea.append(String.format("已下载百分之%s\n", processValue * 10));
            }

            @Override
            protected void done() {
                String res = null;
                try {
                    res = get();
                } catch (Exception e) {

                }
                System.out.println("done... ");
                historyTextArea.append(res);
            }
        };
        task.execute();
    }
}
