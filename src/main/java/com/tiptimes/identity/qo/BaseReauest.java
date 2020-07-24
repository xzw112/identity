package com.tiptimes.identity.qo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseReauest implements Serializable {

    private int pageNumber;

    private int pageSize;
}
