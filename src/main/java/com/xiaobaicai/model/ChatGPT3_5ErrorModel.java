package com.xiaobaicai.model;

import java.io.Serializable;

/**
 * @author liguang
 * @date 2023/3/17 星期五 1:37 下午
 */
public class ChatGPT3_5ErrorModel implements Serializable{
    private static final long serialVersionUID = 1L;

    private ErrorModel error;

    public ErrorModel getError() {
        return error;
    }

    public void setError(ErrorModel error) {
        this.error = error;
    }

    public static class ErrorModel implements Serializable {

        private static final long serialVersionUID = 1L;

        private String message;
        private String type;
        private String param;
        private String code;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
