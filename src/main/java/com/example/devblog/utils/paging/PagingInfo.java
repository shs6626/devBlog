package com.example.devblog.utils.paging;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PagingInfo {

    private String pageSize;
    private String currentPage;
    private String startPage;
    private String endPage;
    private String startNum;
    private String endNum;

}