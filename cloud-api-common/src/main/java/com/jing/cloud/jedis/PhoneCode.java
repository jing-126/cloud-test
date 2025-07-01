package com.jing.cloud.jedis;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class PhoneCode {
    // 验证码120s过期 每个手机号24小时内只能获取三次
    public static void main(String[] args) {
        System.out.println(setRedisCode("15373998755"));
//        verifyCode("15373998755", "555809");
    }

    public static void verifyCode(String phone, String code) {
        Jedis jedis = new Jedis("www.jinglecode.top", 6336);
        jedis.auth("jing@1234");

        String phoneCode = "Phone:" + phone + ":code";
        String redisCode = jedis.get(phoneCode);
        if (code != null && code.equals(redisCode)) {
            System.out.println("校验成功");
        } else {
            System.out.println("校验失败");
        }
        jedis.close();
    }

    public static String setRedisCode(String phone) {
        Jedis jedis = new Jedis("www.jinglecode.top", 6336);
        jedis.auth("jing@1234");

        String phoneKey = "PhoneKey:" + phone + ":count";
        String phoneCode = "Phone:" + phone + ":code";

        String phoneCount = jedis.get(phoneKey);
        if (phoneCount == null) {
            jedis.setex(phoneKey, 24 * 60 * 60, "1");
        } else if (Integer.parseInt(phoneCount) <= 2) {
            jedis.incr(phoneKey);
        } else if (Integer.parseInt(phoneCount) > 2) {
            System.out.println("24小时内以获取三次！");
            jedis.close();
            return null;
        }
        String code = getCode();
        System.out.println("code:" + code);
        jedis.setex(phoneCode, 60, code);
        jedis.close();
        return code;
    }

    public static String getCode() {
        String code = "";
        Random random = new Random();
        random.nextInt(10);
        for (int i = 0; i < 6; i++) {
            code += random.nextInt(10);
        }
        return code;
    }
}
