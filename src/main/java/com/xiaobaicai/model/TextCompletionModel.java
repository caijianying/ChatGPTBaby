package com.xiaobaicai.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author liguang
 * @date 2023/2/9 星期四 2:48 下午
 */
public class TextCompletionModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String object;
    private Long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public static class Usage implements Serializable{
        private static final long serialVersionUID = 1L;

        private String prompt_tokens;
        private String completion_tokens;
        private String total_tokens;

        public String getPrompt_tokens() {
            return prompt_tokens;
        }

        public void setPrompt_tokens(String prompt_tokens) {
            this.prompt_tokens = prompt_tokens;
        }

        public String getCompletion_tokens() {
            return completion_tokens;
        }

        public void setCompletion_tokens(String completion_tokens) {
            this.completion_tokens = completion_tokens;
        }

        public String getTotal_tokens() {
            return total_tokens;
        }

        public void setTotal_tokens(String total_tokens) {
            this.total_tokens = total_tokens;
        }
    }

    public static class Choice implements Serializable{
        private static final long serialVersionUID = 1L;

        private String text;
        private String index;
        private String logprobs;
        private String finish_reason;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getLogprobs() {
            return logprobs;
        }

        public void setLogprobs(String logprobs) {
            this.logprobs = logprobs;
        }

        public String getFinish_reason() {
            return finish_reason;
        }

        public void setFinish_reason(String finish_reason) {
            this.finish_reason = finish_reason;
        }
    }
}
