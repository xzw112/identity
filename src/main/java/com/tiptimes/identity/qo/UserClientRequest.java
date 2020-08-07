package com.tiptimes.identity.qo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserClientRequest implements Serializable {

    private String userId;

    private String[] clientId;
}
