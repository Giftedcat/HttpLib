package com.giftedcat.httplib.model;

public class BaseResponse {

    private int code;
    private Integer currPage;
    private Integer totalPage;
    private String msg;

    @Override
    public String toString() {
        return "BaseBean{" +
                "code=" + code +
                ", currPage=" + currPage +
                ", totalPage=" + totalPage +
                ", msg=" + msg +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
