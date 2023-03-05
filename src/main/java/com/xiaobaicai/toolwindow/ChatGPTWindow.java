package com.xiaobaicai.toolwindow;

import javax.swing.*;

import com.xiaobaicai.listener.ChatGPTButtonActionListener;
import com.xiaobaicai.listener.ChatGPTButtonChangeListener;
import lombok.Data;

@Data
public class ChatGPTWindow {
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JPanel translatorPanel;
    private JComboBox<String> comboBox1;
    private JTextArea originalTextArea;
    private JComboBox<String> comboBox2;
    private JButton submitButton;
    private JTextArea anwserTextArea;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JLabel ask;
    private JLabel anwser;

    public ChatGPTWindow() {
        // 添加按钮监听器
        submitButton.addActionListener(new ChatGPTButtonActionListener(this));
        submitButton.addChangeListener(new ChatGPTButtonChangeListener(this));
    }
}
