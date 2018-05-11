package com.nwnu.greencloud.util;

import java.util.Random;
import java.util.UUID;

public class UuidUtil {
    public static  String generateUuid(){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }
}
