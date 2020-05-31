package com.hdj.frame;

public class ServerAddressConfig {
    public static final int API_TYPE = 3;
    public static String BASE_URL = "";

    static {
        if (API_TYPE == 1){
            BASE_URL = "";
        }
        if (API_TYPE == 2){
            BASE_URL = "";
        }
        if (API_TYPE == 3){
            BASE_URL = "http://static.owspace.com/";
        }
    }
}
