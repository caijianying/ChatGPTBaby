package com.xiaobaicai.util;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationAction;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.options.ShowSettingsUtil;
import org.jetbrains.annotations.NotNull;

/**
 * @Description 右下角的吐司提示
 * @Date 2020/8/28
 * @Created by fangla
 */
public class MessageUtil {
    private static NotificationGroup notificationGroup;

    static {
        notificationGroup = new NotificationGroup("Java2Json.NotificationGroup", NotificationDisplayType.BALLOON, true);
    }

    public static void info(String message) {
        Notification info = notificationGroup.createNotification(message, NotificationType.INFORMATION);
        Notifications.Bus.notify(info, null);
    }

    public static void error(String message) {
        Notification error = notificationGroup.createNotification(message, NotificationType.ERROR);
        Notifications.Bus.notify(error, null);
    }

    public static void warn(String message) {
        Notification warn = notificationGroup.createNotification(message, NotificationType.WARNING);
        Notifications.Bus.notify(warn, null);
    }

    public static void infoOpenToolWindow(String message) {
        Notification notification = new Notification("Print", "提示", message, NotificationType.INFORMATION);
        // 为顶层通知添加 Action，触发 Action 会弹出一个新的通知
        notification.addAction(new NotificationAction("前往配置") {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e, @NotNull Notification notification) {
                ShowSettingsUtil.getInstance().showSettingsDialog(e.getProject(), "Tools.ChatGPTBaby");
            }
        });
        Notifications.Bus.notify(notification, null);
    }
}
