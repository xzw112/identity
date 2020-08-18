package com.tiptimes.identity.dao;

import com.tiptimes.identity.entity.HatArea;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HatAreaMapper {

    List<HatArea> selectAreaList();
}