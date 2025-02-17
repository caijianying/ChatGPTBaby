package com.xiaobaicai.util;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationAction;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.options.ShowSettingsUtil;
import org.jetbrains.annotations.NotNull;

/**
 * @Description 右下角的吐司提示
 * @Date 2023/2/10 10:02 上午
 */
public class MessageUtil {

    /**
     * @author liguang
     * @date 2023/2/10 10:02 上午
     **/
    public static void info(String message){
        Notification notification = new Notification("Print", null, message, NotificationType.INFORMATION);
        Notifications.Bus.notify(notification, null);
    }

    /**
     * @author liguang
     * @date 2023/2/10 10:02 上午
     **/
    public static void warn(String message){
        Notification notification = new Notification("Print", "警告", message, NotificationType.WARNING);
        Notifications.Bus.notify(notification, null);
    }

    /**
     * @author liguang
     * @date 2023/2/10 10:02 上午
     **/
    public static void error(String message){
        Notification notification = new Notification("Print", "错误", message, NotificationType.ERROR);
        Notifications.Bus.notify(notification, null);
    }

    /**
     * @author liguang
     * @date 2023/2/10 10:02 上午
     **/
    public static void infoOpenToolWindow(String message) {
        Notification notification = new Notification("Print", "提示", message, NotificationType.INFORMATION);
        // 为顶层通知添加 Action，触发 Action 会弹出一个新的通知
        notification.addAction(new NotificationAction("前往配置") {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e, @NotNull Notification notification) {
                ShowSettingsUtil.getInstance().showSettingsDialog(e.getProject(), "Tools.CodeBaby");
            }
        });
        Notifications.Bus.notify(notification, null);
    }
}
