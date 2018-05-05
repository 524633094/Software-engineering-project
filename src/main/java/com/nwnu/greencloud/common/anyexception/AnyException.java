package com.nwnu.greencloud.common.anyexception;

import com.nwnu.greencloud.common.constenum.ExceptionEnum;
/**
* @Author zhangqi
* @Desccription: 异常处理
* @Date: 11:18 2018/5/5
*/
public class AnyException extends Exception {
    public AnyException(ExceptionEnum exceptionEnum){
        super(exceptionEnum.getInfo());
    }
    public AnyException(String info){
        super(info);
    }
}
