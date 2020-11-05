package com.nawasoft.oneapp.util;

import androidx.lifecycle.LiveData;

import com.nawasoft.datalayer.model.Page;

import java.util.ArrayList;
import java.util.List;

public class PagingData<T> {
    private List<T> allData = new ArrayList<>();
    private List<T> lastPage = new ArrayList<>();
    private boolean isRefreshing = false;
    private int currentPageNumber = 1, totalPages = 1;


    public void putPage(Page<T> page) {
        if (isRefreshing) {
            allData.clear();
            currentPageNumber = 0;
        }
        currentPageNumber++;
        totalPages = page.getTotalPages();
        allData.addAll(page.getPageData());
        lastPage.clear();
        lastPage.addAll(page.getPageData());
        isRefreshing = false;
    }

    public int getNextPageNumber() {
        return isRefreshing ? 1 : currentPageNumber;
    }

    public boolean canLoadMore() {
        return currentPageNumber <= totalPages || isRefreshing;
    }

    public boolean isRefreshing() {
        return isRefreshing;
    }

    public void refresh() {
        isRefreshing = true;
    }

    public void stopRefresh() {
        isRefreshing = false;
    }

    public List<T> getAllData() {
        return allData;
    }

    public List<T> getLastPage() {
        return lastPage;
    }
}
