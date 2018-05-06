package com.nwnu.greencloud.common.reply;

import com.nwnu.greencloud.common.anyexception.AnyException;
import com.nwnu.greencloud.common.constenum.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
* @Author zhangqi
* @Desccription: controller应答
* @Date: 14:40 2018/5/6
*/
@Log4j2
@Data
@AllArgsConstructor
public class Reply {
    private int code;
    private String message;
    public Reply(ExceptionEnum ex){
        this.code = ex.getCode();
        this.message = ex.getInfo();
    }

}
