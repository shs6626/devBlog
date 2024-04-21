package com.example.devblog.utils.paging;

public class PagingUtils {

    public static PagingInfo getPagingInfo(String currentPage_, String pageSize_, int totalRowCnt) {
        // 1. @RequestParam 값 유효성 검사
        int pageSize = checkPageSizeValidation(pageSize_);
        int pageCnt = 5; // 보여줄 - [1] [2] [3] [4] [5] 개수
        int totalPageCnt = calTotalPageCnt(totalRowCnt, pageSize); // [1] [2] [3] .... [14]
        // 현재 페이지 방어 로직
        int currentPage = checkCurrentPageValidation(Integer.parseInt(currentPage_), totalPageCnt);

        int endPage = calEndPage(currentPage, pageCnt, totalPageCnt);
        int startPage = calStartPage(endPage, pageCnt);

        int startNum = (currentPage-1) * pageSize + 1;
        int endNum = startNum + pageSize - 1;

        return PagingInfo.builder()
                .pageSize(String.valueOf(pageSize))
                .currentPage(String.valueOf(currentPage))
                .endPage(String.valueOf(endPage))
                .startPage(String.valueOf(startPage))
                .startNum(String.valueOf(startNum))
                .endNum(String.valueOf(endNum))
                .build();
    }

    private static int checkPageSizeValidation(String pageSize) {
        if ("5".equals(pageSize) || "10".equals(pageSize) || "50".equals(pageSize)) {
            return Integer.parseInt(pageSize);
        }
        return 10; // default 값 10
    }

    private static int checkCurrentPageValidation(int currentPage, int totalPageCnt) {
        if (currentPage > totalPageCnt)
            currentPage = totalPageCnt;
        else if (currentPage < 1)
            currentPage = 1;

        return currentPage;
    }

    private static int calTotalPageCnt(int totalRowCnt, int pageSize) {
        if (totalRowCnt == 0) {
            return 1;
        }
        return (totalRowCnt % pageSize == 0) ? totalRowCnt / pageSize : totalRowCnt / pageSize + 1;
    }

    private static int calEndPage(int currentPage, int pageCnt , int totalPageCnt) {
        int endPage = (int)Math.ceil((double) currentPage/pageCnt) * pageCnt;

        return checkEndPageValidation(endPage, totalPageCnt);
    }

    private static int checkEndPageValidation(int endPage, int totalPageCnt) {
        if (endPage > totalPageCnt)
            endPage = totalPageCnt;

        return endPage;
    }

    private static int calStartPage(int endPage, int pageCnt) {
        int startPage = endPage - pageCnt + 1;

        return checkStartPageValidation(startPage);
    }

    private static int checkStartPageValidation(int startPage) {
        if (startPage < 1) {
            startPage = 1;
        }
        return startPage;
    }

}
