package com.tiptimes.identity.qo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OutUserRequest extends BaseReauest implements Serializable {

    private String userId;

    private String userName;

    private String searchText;

}
