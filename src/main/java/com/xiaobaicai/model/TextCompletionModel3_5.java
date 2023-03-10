package com.xiaobaicai.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author liguang
 * @date 2023/2/9 星期四 2:48 下午
 */
public class TextCompletionModel3_5 implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String object;
    private Long created;
    private String model;
    private List<Choice3_5> choices;

    public static class Choice3_5 implements Serializable {
        private static final long serialVersionUID = 1L;

        private Dela delta;
        private String index;
        private String finish_reason;

        public Dela getDelta() {
            return delta;
        }

        public void setDelta(Dela delta) {
            this.delta = delta;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getFinish_reason() {
            return finish_reason;
        }

        public void setFinish_reason(String finish_reason) {
            this.finish_reason = finish_reason;
        }
    }

    public static class Dela implements Serializable {
        private static final long serialVersionUID = 1L;

        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

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

    public List<Choice3_5> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice3_5> choices) {
        this.choices = choices;
    }
}
