package com.xiaobaicai.toolwindow;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.xiaobaicai.listener.ChatGPTSendBtnActionListener;
import lombok.Data;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import java.awt.*;
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
    private JTextPane historyTextPane;
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

    public void showHis2() {
        SwingWorker<String, String> task = new SwingWorker<String, String>() {

            @Override
            protected String doInBackground() throws Exception {
                for (int i = 0; i < 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    setProgress(i);
                    publish(i % 2 == 1 ? "你" : "AI");
                }
                return "下载完成！";
            }

            @Override
            protected void process(List<String> chunks) {
                String role = chunks.get(0);
                StyleContext sc = StyleContext.getDefaultStyleContext();
                AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground,
                    Color.LIGHT_GRAY);
                aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
                aset = sc.addAttribute(aset, StyleConstants.FontSize, 15);
                aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
                int len = historyTextPane.getDocument().getLength();
                historyTextPane.setCaretPosition(len);
                historyTextPane.setCharacterAttributes(aset, false);
                historyTextPane.replaceSelection(role + ":" + "XXX\n\n");
            }

            @Override
            protected void done() {
                System.out.println("done... ");
            }
        };
        task.execute();
    }

    {
        // GUI initializer generated by IntelliJ IDEA GUI Designer
        // >>> IMPORTANT!! <<<
        // DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        sendText = new JTextArea();
        panel1.add(sendText, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50),
            null, 0, false));
        sendBtn = new JButton();
        sendBtn.setText("发送");
        panel1.add(sendBtn, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        mainPanel.add(scrollPane1,
            new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0,
                false));
        historyTextPane = new JTextPane();
        scrollPane1.setViewportView(historyTextPane);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() { return mainPanel; }
}
