package com.cn.zwb.youmesay.support.http.bean;

/**
 * Created by Administrator on 2016/4/25.
 */
public class HttpRequestResult  <T>{

    /**
     * 是否成功
     */
   private String success;

    /**
     * 返回码
     */
   private String code;

    /**
     * 返回的数据类型
     */
   private T result;

    /**
     * 当前页
     */
    private int page;

    /**
     * 总页数
     */
    private int totalPage;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
