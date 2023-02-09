package com.xiaobaicai.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author liguang
 * @date 2023/2/9 星期四 2:48 下午
 */
@Data
public class TextCompletionModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String object;
    private Long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;


    @Data
    public static class Usage implements Serializable{
        private static final long serialVersionUID = 1L;

        private String prompt_tokens;
        private String completion_tokens;
        private String total_tokens;
    }

    @Data
    public static class Choice implements Serializable{
        private static final long serialVersionUID = 1L;

        private String text;
        private String index;
        private String logprobs;
        private String finish_reason;

    }
}
