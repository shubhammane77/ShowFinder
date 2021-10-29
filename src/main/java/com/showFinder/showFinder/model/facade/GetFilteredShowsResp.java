package com.showFinder.showFinder.model.facade;

import com.showFinder.showFinder.model.data.Error;
import com.showFinder.showFinder.model.data.ShowBasicInfo;

import java.util.List;

public class GetFilteredShowsResp extends Error {
    List<ShowBasicInfo> showInfoList;
    int pageCount;

    String errorMessage;
    public List<ShowBasicInfo> getShowInfoList() {
        return showInfoList;
    }

    public void setShowInfoList(List<ShowBasicInfo> showInfoList) {
        this.showInfoList = showInfoList;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
