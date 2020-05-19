package com.kanq.admin.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class Md5Util {
    public static String MD5pwd(String username, String pwd) {
        String md5Pwd = new SimpleHash("MD5", pwd, ByteSource.Util.bytes(username + "salt"), 2).toHex();
        return md5Pwd;
    }

//    public static void main(String[] args) {
//        String username = "public";
//        String pwd = "123456";
//        String md5Pwd = MD5pwd(username , pwd);
//        System.out.println(md5Pwd);//def0e4568a393af9aca7bfa3d54c5147
//    }

}
