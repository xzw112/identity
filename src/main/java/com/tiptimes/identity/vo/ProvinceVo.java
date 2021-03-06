package com.tiptimes.identity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProvinceVo implements Serializable {

    private String lable;

    private String value;

    private List<CityVo> children;
}
