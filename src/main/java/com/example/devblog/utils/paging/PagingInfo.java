package com.example.devblog.utils.paging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PagingInfo {

    private int pageSize;
    private int currentPage;
    private int startPageBlock;
    private int endPageBlock;
    private int startRowDataNum;
    private int endRowDataNum;
    private int totalPageBlockCnt;
    private long totalRowDataCnt;
    private boolean prev;
    private boolean next;

}