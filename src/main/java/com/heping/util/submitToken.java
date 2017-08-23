package com.heping.util;

import java.util.UUID;

/**
 * Created by liupengtao
 * ClassName  submitToken
 * 表单提交Token生成器
 */
public class submitToken {

    private submitToken(){}

    private static final submitToken instance = new submitToken();

    public static submitToken getInstance(){
        return instance;
    }
    public String makeToken(){
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

    public static void main(String[] args){
    }

}
