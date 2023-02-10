package com.xiaobaicai.model;

import java.io.Serializable;

/**
 * @author liguang
 * @date 2023/2/10 星期五 4:41 下午
 */
public class ErrorModel implements Serializable {

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

    public Object getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
