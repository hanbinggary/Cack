package com.wx.cack.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wx.cack.model.Login;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

/**
 * WeChat
 * @author liuxuelian
 * @date 2021/03/24
 */
public class WeChatUtil {
    private static String APP_ID = "wxc23cb57de0ac32c8";
    private static String SECRET = "61ce8ca08fa345698af2f59984b7fc51";

    public static Login httpsRequest(String JS_CODE) throws IOException {
        String requestUrl = String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", APP_ID, SECRET, JS_CODE);
        URL url = new URL(requestUrl);

        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        httpsURLConnection.setDoOutput(true);
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.setUseCaches(false);
        httpsURLConnection.setRequestMethod("GET");

        httpsURLConnection.connect();

        InputStream inputStream = httpsURLConnection.getInputStream();
        Login login = new ObjectMapper().readValue(inputStream, Login.class);
        if (Objects.isNull(login.getErrcode()) || login.getErrcode() == 0) {
            return login;
        }

        return null;
    }
}
