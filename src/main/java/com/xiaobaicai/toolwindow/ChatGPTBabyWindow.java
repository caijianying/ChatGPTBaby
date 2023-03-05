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
 * @date 2023/3/5 星期日 9:28 下午
 */
@Data
public class ChatGPTBabyWindow {
    private JTabbedPane chatTabbedPane;
    private JTextArea sendText;
    private JButton sendBtn;
    private JScrollPane historyScrollPane;
    private JPanel chatPane;
    private JPanel sendPane;
    private JTextArea historyTextArea;
    private JScrollPane sendTextScrollPane;

    public void createUIComponents() {

    }

    public ChatGPTBabyWindow() {
        if (sendBtn == null){
            sendBtn = new JButton("send");
        }
        sendBtn.addActionListener(new ChatGPTSendBtnActionListener(this));
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
                historyTextArea.append(String.format("已下载%s%\n", processValue * 10));
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
        task.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("process".equals(evt.getPropertyName())) {
                    int newValue = (int) evt.getNewValue();
                    System.out.println("addPropertyChangeListener.propertyChange " + newValue);
                }
            }
        });
        task.execute();
    }
}
