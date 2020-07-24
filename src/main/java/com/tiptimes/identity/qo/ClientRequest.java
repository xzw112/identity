package com.tiptimes.identity.qo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClientRequest extends BaseReauest implements Serializable {

    private String searchText;
}
