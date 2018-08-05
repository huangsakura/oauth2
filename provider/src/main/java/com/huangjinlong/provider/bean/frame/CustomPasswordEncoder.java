package com.huangjinlong.provider.bean.frame;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

    /**
     * 将明文加上这个前缀，就是密文
     */
    private static final String PASSWORD_PREFIX = "sakura_";

    @Override
    public String encode(CharSequence charSequence) {
        return PASSWORD_PREFIX + charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s/*密文*/) {
        String openText = charSequence.toString();//明文
        if (StringUtils.isBlank(openText) || StringUtils.isBlank(s)) {
            return false;
        }
        return s.equals(PASSWORD_PREFIX + openText);
    }
}
