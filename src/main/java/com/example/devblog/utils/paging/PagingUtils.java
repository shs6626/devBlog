package com.example.devblog.utils.paging;

public class PagingUtils {

    public static PagingInfo getPagingInfo(String currentPage_, String pageSize_, long totalRowDataCnt) {
        /* STEP 1*/
        int pageBlockCnt = 10; // 개발자가 정하면 됨
        int pageSize = checkPageSizeValidation(pageSize_);
        int totalPageBlockCnt = calTotalPageBlockCnt(totalRowDataCnt, pageSize);
        int currentPage = checkCurrentPageValidation(Integer.parseInt(currentPage_), totalPageBlockCnt);
        /* STEP 2 */
        int endPageBlock = calEndPageBlock(currentPage, pageBlockCnt, totalPageBlockCnt);
        int startPageBlock = calStartPageBlock(endPageBlock, pageBlockCnt);
        // 유효성 검사를 더 늦게 해줘야 함
        endPageBlock = checkEndPageBlockValidation(currentPage, pageBlockCnt);
        boolean next = (endPageBlock == totalPageBlockCnt);

        startPageBlock = checkStartPageBlockValidation(endPageBlock);
        boolean prev = (startPageBlock == 1);
        /* STEP 3 */
        int startRowDataNum = (currentPage-1) * pageSize + 1;
        int endRowDataNum = startRowDataNum + pageSize -1;

        return new PagingInfo(
                pageSize,
                currentPage,
                startPageBlock,
                endPageBlock,
                startRowDataNum,
                endRowDataNum,
                prev,
                next
        );
    }

    // pageSize(한 페이지 당 보여줄 entity 개수) -- 몇 개 보여줄 것인지 개발자가 정하고 나머지는 디폴트 값을 뿌리기
    private static int checkPageSizeValidation(String pageSize) {
        // pageSize 조절 -- default값은 10
        if ("10".equals(pageSize) || "30".equals(pageSize) || "50".equals(pageSize)) {
            return Integer.parseInt(pageSize);
        }
        return 10;
    }

    private static int checkCurrentPageValidation(int currentPage, int totalPageBlockCnt) {
        if (currentPage > totalPageBlockCnt)
            currentPage = totalPageBlockCnt;
        else if (currentPage < 1)
            currentPage = 1;

        return currentPage;
    }

    private static int calTotalPageBlockCnt(long totalRowDataCnt, int pageSize) {
        if (totalRowDataCnt == 0) {
            return 1;
        }
        return (int)(totalRowDataCnt + pageSize - 1) / pageSize;
    }

    private static int calEndPageBlock(int currentPage, int pageBlockCnt , int totalPageBlockCnt) {
        return ((currentPage + pageBlockCnt - 1) / pageBlockCnt) * pageBlockCnt;
    }

    private static int checkEndPageBlockValidation(int endPageBlock, int totalPageBlockCnt) {
        return Math.min(endPageBlock, totalPageBlockCnt);
    }

    private static int calStartPageBlock(int endPageBlock, int totalPageBlockCnt) {
        return endPageBlock - totalPageBlockCnt + 1;
    }

    private static int checkStartPageBlockValidation(int startPageBlock) {
        return Math.max(startPageBlock, 1);
    }

}
