package com.tiptimes.identity.cahe.redis;

import java.util.concurrent.TimeUnit;

/**
 * @author lvhaibao
 * @description
 * @date 2018/12/17 0017 17:15
 */
public interface VcodeManager {


    /**
     * 生成验证码
     *
     * @return 验证码
     */
    String generateVcode();

    /**
     * 发送短信
     * @param phoneNumber 手机号
     * @param smsCode 短信验证码
     * @return
     */
    int sendSmsCode(String phoneNumber, String smsCode);



    /**
     * 保存验证码
     *
     * @param mobile     手机号
     * @param code       验证码
     * @param expireTime 保存时间
     * @param unit       保存得单位时间
     */
    void saveVcode(String mobile, String code, long expireTime, TimeUnit unit);

    /**
     * 获取验证码
     *
     * @param mobile 手机号
     * @return 验证码
     */
    Object getVcode(String mobile);


    /**
     * 删除验证码
     *
     * @param mobile
     */
    void removeVcode(String mobile);
}
