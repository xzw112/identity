package com.tiptimes.identity.dao;


import com.tiptimes.identity.entity.HatCity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HatCityMapper {
    List<HatCity> selectCityList();
}