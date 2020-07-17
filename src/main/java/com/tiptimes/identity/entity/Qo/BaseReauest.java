package com.tiptimes.identity.entity.Qo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseReauest implements Serializable {

    private int pageNumber;

    private int pageSize;
}
