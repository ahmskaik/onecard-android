package com.nawasoft.datalayer.model;

import java.util.List;

public class Page<T> {
    private List<T> pageData;
    private int currentPage;
    private int totalPages;

    public Page(List<T> pageData, int currentPage, int totalPages) {
        this.pageData = pageData;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
