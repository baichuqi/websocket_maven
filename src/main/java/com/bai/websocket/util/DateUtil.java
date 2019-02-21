package com.bai.websocket.util;

import java.time.LocalDateTime;

public class DateUtil {

	public static String now() {
        return LocalDateTime.now().toString().replace("T", " ");
    }
}
