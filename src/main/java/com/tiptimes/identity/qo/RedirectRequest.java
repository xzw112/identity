package com.tiptimes.identity.qo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RedirectRequest implements Serializable {
    private String token;

    private String redirectUri;

    private String userId;
}
