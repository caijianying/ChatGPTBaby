package com.xiaobaicai.extension;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.xiaobaicai.toolwindow.ChatDemoWindow;
import com.xiaobaicai.toolwindow.ChatGPTBabyWindow;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ChatGPTBabyToolsWindow implements ToolWindowFactory {

    private static JTable table;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // ContentFactory 在 IntelliJ 平台 SDK 中负责UI界面的管理
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        // 创建我们的工具栏界面
        // TranslatorNote note = new TranslatorNote();
        // table = note.getTable();
        ChatDemoWindow chatGPTWindow = new ChatDemoWindow();
        // 在界面工厂中创建翻译插件的界面
        Content content = contentFactory.createContent(chatGPTWindow.getMainPanel(), "", false);
        // 将被界面工厂代理后创建的content，添加到工具栏窗口管理器中
        toolWindow.getContentManager().addContent(content);
    }
}
