package com.example.demo.dto;

import java.util.List;

public class PageResponse<T> {
    private long total;
    private int pageSize;
    private int currentPage;
    private List<T> data;
    public PageResponse( List<T> data,long total, int pageSize, int currentPage) {
        this.data = data;
        this.total = total;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }
    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
