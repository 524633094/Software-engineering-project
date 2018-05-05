package com.nwnu.greencloud.common.constenum;

/**
* @Author zhangqi
* @Desccription: 用户注册状态
* @Date: 11:05 2018/5/5
*/
public enum UserStateEnum {
    ACTIVATE(1),UNACTIVATE(0);
    private int state;
    private UserStateEnum(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    @Override
    public String toString() {
        return "state"+this.state;
    }
}
