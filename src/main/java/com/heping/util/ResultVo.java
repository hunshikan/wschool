package com.heping.util;

/**
 * 〈一句话功能简述〉<br>
 * 〈通用数据返回〉
 *
 * @author liupengtao
 * @create 2017/8/25
 * @since 1.0.0
 */
public class ResultVo <T>{
    private boolean success = false;
    private String message = null;
    private T result = null;
    public void isSuccess(boolean b) {
        this.success=b;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setResult(T result) {
        this.result = result;
    }
    public boolean getSuccess(){
        return success;
    }
    public String getMessage() {
        return message;
    }
    public T getResult() {
        return result;
    }

}
