package com.tiptimes.identity.common;

import lombok.Data;
import java.util.List;

@Data
public class PageResult<T> {
    private List<T> rows;
    private long total = 0L;
}
