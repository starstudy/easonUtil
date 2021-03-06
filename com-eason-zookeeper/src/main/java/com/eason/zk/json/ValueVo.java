package com.eason.zk.json;


import com.eason.zk.constants.Constants;


public class ValueVo {

    // 是否存在此KEy
    private Integer status = Constants.OK;

    //
    private String message = "";

    //
    private String value = "";

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ValueVo [status=" + status + ", message=" + message + ", value=" + value + "]";
    }

}
