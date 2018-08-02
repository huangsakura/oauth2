package com.huangjinlong.provider.bean.entity;

import com.huangjinlong.provider.bean.enums.ClientStatus;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.io.Serializable;
import java.util.*;

@Data
public class PClient implements ClientDetails, Serializable {

    private static final long serialVersionUID = -4154776304730720312L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 客户端标识，全局唯一
     */
    private String clientId;

    /**
     * 资源，逗号分隔
     */
    private String resourceIds;

    /**
     * "noop" ->
     * "pbkdf2" ->
     * "SHA-1" ->
     * "sha256" ->
     * "ldap" ->
     * "SHA-256" ->
     * "MD4" ->
     * "scrypt" ->
     * "bcrypt" ->
     * "MD5" ->
     *
     * 客户端密钥
     */
    private String clientSecret;

    /**
     * 权限，逗号分隔
     */
    private String scope;

    /**
     * accessToken 有效时间，单位秒
     */
    private Integer accessTokenValiditySeconds;

    /**
     * refreshToken 有效时间，单位秒
     */
    private Integer refreshTokenValiditySeconds;

    /**
     * 回调地址，逗号分隔
     */
    private String redirectUri;

    /**
     * implicit
     * authorization_code
     * refresh_token
     *
     * 能调用的模式，逗号分隔
     */
    private String authorizedGrantTypes;

    /**
     *
     */
    private String grantTypes;

    /**
     * 状态
     */
    private ClientStatus status;

    /**
     * 自动授权的权限，逗号分隔
     *
     * 必须是 scope字段的子集或全集
     *
     * 放入这里面的权限，就不需要客户授权了
     */
    private String autoApproveScope;

    /**
     * 创建时间
     */
    private Date createTime;

    private Date updateTime;

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        Set<String> stringSet = new HashSet<>();
        if (StringUtils.isBlank(resourceIds)) {
            return stringSet;
        }
        Collections.addAll(stringSet,resourceIds.split(","));
        return stringSet;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {

        /**
         * 必须在密码前加上下面其中的一个
         *
         * noop
         * pbkdf2
         * SHA-1
         * sha256
         * ldap
         * SHA-256
         * MD4
         * scrypt
         * bcrypt
         * MD5
         *
         * 参见 org.springframework.security.crypto.password.DelegatingPasswordEncoder#extractId(java.lang.String)方法
         */
        /**
         * 相关链接 https://www.ktanx.com/blog/p/4917
         */
        return "{noop}"+clientSecret;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        Set<String> stringSet = new HashSet<>();
        if (StringUtils.isBlank(scope)) {
            return stringSet;
        }
        Collections.addAll(stringSet,scope.split(","));
        return stringSet;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        Set<String> stringSet = new HashSet<>();
        if (StringUtils.isBlank(authorizedGrantTypes)) {
            return stringSet;
        }
        Collections.addAll(stringSet,authorizedGrantTypes.split(","));
        return stringSet;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        Set<String> result = new HashSet<>();
        if (StringUtils.isBlank(redirectUri)) {
            return result;
        }
        Collections.addAll(result,redirectUri.split(","));
        return result;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> result = new HashSet<>();
        if (StringUtils.isBlank(grantTypes)) {
            return result;
        }
        for (String x : grantTypes.split(",")) {
            result.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return x;
                }
            });
        }
        return result;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String s) {
        if (StringUtils.isBlank(autoApproveScope)) {
            return false;
        }
        for (String x : autoApproveScope.split(",")) {
            if (x.equals(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return Collections.emptyMap();
    }
}
