package com.tiptimes.identity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class UserDetailsVo implements UserDetails, Serializable {


    private String id;

    private String userName;

    private String loginName;

    @JsonIgnore
    private String loginPassword;

    private String userContact;

    private String userAddress;

    private String mail;

    private String roleId;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Integer status;

    private Integer isDelete;

    private String remark;

    private Integer isAdmin;

    private String updateTimeStr;//更新时间

    private Integer isLeave; // 是否离职(0未离职 1已离职)

    private Integer nature; // 人员性质（0正式编 1社会化）

    private String post; // 岗位

    private Integer sort; // 排序

    private String department; // 部门

    private String headerUrl; // 头像

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> au = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("p1");
        au.add(grantedAuthority);
        return au;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return loginPassword;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return loginName;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
