package com.xiaobaicai.listener;

import java.awt.event.ActionEvent;

import javax.swing.*;

import com.xiaobaicai.config.ChatGPTConfig;

/**
 * @author liguang
 * @date 2022/10/12 星期三 8:14 下午
 */
public class ToolsSettingActionListener extends AbstractAction {

    private final JTextField textField;

    private final ChatGPTConfig config;

    public ToolsSettingActionListener(JTextField textField, ChatGPTConfig config) {
        this.textField = textField;
        this.config = config;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // btn name
        String actionCommand = e.getActionCommand();
        textField.setText("");
        this.config.apply();
    }
}
