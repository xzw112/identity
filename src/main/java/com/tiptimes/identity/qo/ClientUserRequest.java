package com.tiptimes.identity.qo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClientUserRequest implements Serializable {

    private String clientId;

    private String[] userId;
}
