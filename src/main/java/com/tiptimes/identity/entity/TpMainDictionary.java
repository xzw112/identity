package com.tiptimes.identity.entity;

import lombok.Data;
import javax.persistence.Id;

@Data
public class TpMainDictionary {

    @Id
    private Integer id;

    private String dictionaryValue;

    private String dictionaryClass;

    private String isDelete;

}