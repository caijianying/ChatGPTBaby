package com.xiaobaicai.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;

/**
 * @author caijy
 * @description 右键选择
 * @date 2022/11/5 星期六 10:41 下午
 */
public class SelectTextAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Editor editor = event.getData(CommonDataKeys.EDITOR);
        String text = editor.getSelectionModel().getSelectedText();
        System.out.println("actionPerformed>>>:" + text);

    }
}
