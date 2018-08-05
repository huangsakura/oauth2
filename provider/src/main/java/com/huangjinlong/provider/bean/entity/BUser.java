package com.huangjinlong.provider.bean.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class BUser implements Serializable, UserDetails, CredentialsContainer {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    private String authority;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String username;

    /**
     * 创建时间
     */
    private Date createdDate;

    /**
     * 更新时间
     */
    private Date stateDate;

    /**
     * 状态
     */
    private String state;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> result = new HashSet<>();

        if (StringUtils.isNotBlank(authority)) {
            for (String x : authority.split(",")) {
                result.add(new SimpleGrantedAuthority(x));
            }
        }
        return result;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return "10A".equals(state);
    }

    @Override
    public void eraseCredentials() {
        password = null;
    }
}
