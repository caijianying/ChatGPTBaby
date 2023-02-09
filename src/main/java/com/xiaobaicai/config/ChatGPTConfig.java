package com.xiaobaicai.config;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import com.alibaba.fastjson.JSON;

import com.google.common.collect.Lists;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.util.NlsContexts.ConfigurableName;
import com.intellij.ui.JBColor;
import com.xiaobaicai.cache.ChatGPTCache;
import com.xiaobaicai.listener.ToolsSettingActionListener;
import com.xiaobaicai.model.SettingsRowModel;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

/**
 * @author liguang
 * @date 2023/2/9 星期四 3:19 下午
 */
public class ChatGPTConfig implements Configurable {

    public static final String PLUGIN_NAME = "ChatGPTBaby";

    List<JTextField> textFieldList = Lists.newArrayList();

    private static final int column = 3;

    private JPanel jPanel;
    private JLabel jLabel;
    private JButton commandBtn;

    List<SettingsRowModel> configItems = Lists.newArrayList();

    public ChatGPTConfig() {
        int row = 18;
        jPanel = new JPanel(new GridLayout(row, column));

        SettingsRowModel openAIRowModel = new SettingsRowModel();
        openAIRowModel.setLabel("官网密钥");
        openAIRowModel.setValue(ChatGPTCache.getInstance().openAiAuthKey);
        openAIRowModel.setBtn("清空");
        configItems.add(openAIRowModel);

        configItems.stream().forEach(rowModel -> {
            // 拿到各个命令的展示信息
            // 1. 设置命令行
            JLabel command = new JLabel(rowModel.getLabel());
            // 2. 设置回显
            JTextField textField = new JTextField();
            textField.setText(rowModel.getValue());
            // 焦点监听
            textField.addFocusListener(
                new TextFieldListener(textField, "登录OpenAI 打开 https://platform.openai.com/account/api-keys"));
            // 3.设置按钮名字
            JButton systemBtn = new JButton(rowModel.getBtn());
            // 设置Action
            systemBtn.addActionListener(new ToolsSettingActionListener(textField, this));

            jPanel.add(command);
            jPanel.add(textField);
            jPanel.add(systemBtn);
            // 保存输入框
            textFieldList.add(textField);
        });
        // 计算缺省行
        int restRow = (row - configItems.size()) * column;
        for (int i = 0; i < restRow; i++) {
            jPanel.add(new JLabel());
        }
    }

    @Override
    public @ConfigurableName String getDisplayName() {
        return PLUGIN_NAME;
    }

    @Override
    public @Nullable JComponent createComponent() {
        return jPanel;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() {
        if (!textFieldList.isEmpty()) {
            JTextField textField = textFieldList.get(0);
            ChatGPTCache.getInstance().openAiAuthKey = textField.getText();
        }
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public JLabel getjLabel() {
        return jLabel;
    }

    public void setjLabel(JLabel jLabel) {
        this.jLabel = jLabel;
    }

    public JButton getCommandBtn() {
        return commandBtn;
    }

    public void setCommandBtn(JButton commandBtn) {
        this.commandBtn = commandBtn;
    }

    static class TextFieldListener implements FocusListener {

        private final String defaultHint;
        private final JTextField textField;

        public TextFieldListener(JTextField textField, String defaultHint) {
            this.defaultHint = defaultHint;
            this.textField = textField;
        }

        @Override
        public void focusGained(FocusEvent e) {
            // 清空提示语，设置为黑色字体
            if (textField.getText().equals(defaultHint)) {
                textField.setText("");
                textField.setForeground(JBColor.BLACK);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            // 如果内容为空，设置提示语
            if (StringUtils.isBlank(textField.getText())) {
                textField.setText(defaultHint);
                textField.setForeground(JBColor.GRAY);
            }
        }
    }
}
