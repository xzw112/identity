package com.tiptimes.identity.cahe.redis;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.tiptimes.identity.common.Constants;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author lvhaibao
 * @description
 * @date 2018/12/17 0017 17:16
 */
@Service
public class VcodeManagerImpl implements VcodeManager {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public String generateVcode() {
        String code = RandomStringUtils.randomNumeric(Constants.SMS_CODE_LENGHT);
        return code;
    }

    @Override
    public int sendSmsCode(String phoneNumber, String smsCode) {
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(Constants.SMS_SERVER_IP, Constants.SMS_SERVER_PORT);
        sdk.setAccount(Constants.SMS_ACCOUNTS_ID, Constants.SMS_ACCOUNT_TOKEN);
        sdk.setAppId(Constants.SMS_APP_ID);
        sdk.setBodyType(BodyType.Type_JSON);
        String[] datas = {smsCode, Constants.SMS_CLOOPEN_TIME};
        try {
            HashMap<String, Object> result = sdk.sendTemplateSMS(phoneNumber, Constants.SMS_TEMPLATE_ID, datas);
            if ("000000".equals(result.get("statusCode"))) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void saveVcode(String mobile, String code, long expireTime, TimeUnit unit) {
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(mobile, code);
            redisTemplate.expire(mobile, expireTime, unit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getVcode(String mobile) {
        Object result;
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        result = operations.get(mobile);
        return result;
    }

    @Override
    public void removeVcode(String mobile) {
        if(redisTemplate.hasKey(mobile)){
            redisTemplate.delete(mobile);
        }
    }
}
