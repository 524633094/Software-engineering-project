package com.nwnu.greencloud.common.reply;

import com.nwnu.greencloud.common.anyexception.AnyException;
import com.nwnu.greencloud.common.constenum.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
* @Author zhangqi
* @Desccription: controller应答
* @Date: 14:40 2018/5/6
*/
@Log4j2
@Data
public class Reply<T>{
    private int code;
    private String message;
    private List<T> list;
    public Reply(ExceptionEnum ex){
        this.code = ex.getCode();
        this.message = ex.getInfo();
    }
    public Reply(int code,String message){
        this.code = code;
        this.message = message;
    }
    public Reply(int code,String message,List<T> list){
        this.code = code;
        this.message = message;
        this.list = list;
    }

}
