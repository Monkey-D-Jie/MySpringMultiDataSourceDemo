package com.mydemo.multidatasources.entity;

import java.io.Serializable;

/**
 */
public class ResponseBean implements Serializable {
    private static final long serialVersionUID = 5684055910696744369L;
    /**
     * 请求结果：请求是否成功（仅表示后台有无异常）
     */
    private boolean result;
    /**
     * 错误代码
     */
    private String code;
    /**
     * 错误说明
     */
    private String message;
    /**
     * 返回的结果
     */
    private Object data;
    /**
     * 分页请求使用，总可用数据量
     */
    private int total;
    /**
     * 后台版本
     */
    private String version= "1.0.0";

    public ResponseBean() {
        result=true;
        //无错误
        code="-1";
        message = "OK";
        data = new Empty();
    }

    public ResponseBean(Object data) {
        this();
        this.data = data;
    }

    public ResponseBean(Object data, int total) {
        this(data);
        this.total = total;
    }

    public ResponseBean(Throwable t, String exceptionCode) {
        result=false;
        this.code=exceptionCode;
        this.message= t.getMessage();
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    class  Empty{

    }
}


