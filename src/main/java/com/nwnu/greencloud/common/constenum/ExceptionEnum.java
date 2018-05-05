package com.nwnu.greencloud.common.constenum;

/**
* @Author zhangqi
* @Desccription: 异常常量
* @Date: 12:01 2018/5/5
*/
public enum ExceptionEnum {
    EmailFail(10001,"邮件发送失败");
    private int code;
    private String info;
    ExceptionEnum(int code,String info){
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
