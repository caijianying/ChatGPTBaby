package com.xiaobaicai.cache;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.xiaobaicai.config.ChatGPTConfig;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author liguang
 * @date 2023/2/9 星期四 3:32 下午
 */
@State(name = ChatGPTConfig.PLUGIN_NAME, storages = {@Storage(value = "chatGpt-tools-setting.xml")})
public class ChatGPTCache implements PersistentStateComponent<ChatGPTCache> {

    public String openAiAuthKey;

    public static ChatGPTCache getInstance() {
        return ApplicationManager.getApplication().getService(ChatGPTCache.class);
    }

    @Override
    public @Nullable ChatGPTCache getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull ChatGPTCache toolsSetting) {
        this.openAiAuthKey = toolsSetting.openAiAuthKey;
    }
}