package com.youma.util;

/***
 * 封装分页类
 * @author Administrator
 */
public class Page {
    /**
     * 每页显示多少条
     */
    private int pageSize = 5;
    /**
     * 当前页数
     */
    private int pageNo = 1;
    /**
     * 总页数
     */
    private int totalPage = 0;
    /**
     * 总条数
     */
    private int totalCount = 0;
    /**
     * 偏移量
     */
    private int offset = 0;

    public Page() {
    }

    public Page(int pageSize) {
        this.pageSize = pageSize;
    }

    public Page(int pageNo, int pageSize, int totalCount) {
        this.setPageSize(pageSize);
        this.setTotalCount(totalCount);
        this.setPageNo(pageNo);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    /**
     * setPageNo 封装页码时直接求offset 偏移量
     *
     * @param pageNo
     */
    public void setPageNo(int pageNo) {
        if (pageNo <= 1) {
            this.pageNo = 1;
        } else if (pageNo >= getTotalPage()) {
            this.pageNo = getTotalPage();
        } else {
            this.pageNo = pageNo;
        }
        this.offset = (this.pageNo - 1) * this.pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 当总记录赋值时，直接求总页数，即pageSize不需要在调用set方法
     *
     * @param totalCount
     */
    public void setTotalCount(int totalCount) {

        if (totalCount > 0) {
            this.totalPage = (totalCount - 1) / this.pageSize + 1;
        } else {
            this.totalPage = 1;
        }
        this.totalCount = totalCount;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

}