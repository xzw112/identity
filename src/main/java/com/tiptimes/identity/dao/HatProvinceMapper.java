package com.tiptimes.identity.dao;


import com.tiptimes.identity.entity.HatProvince;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HatProvinceMapper {
    List<HatProvince> selectProvinceList();
}