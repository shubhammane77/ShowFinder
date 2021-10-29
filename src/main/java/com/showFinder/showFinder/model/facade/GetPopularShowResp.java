package com.showFinder.showFinder.model.facade;

import com.showFinder.showFinder.model.data.Error;
import com.showFinder.showFinder.model.data.ShowBasicInfo;

import java.util.List;

public class GetPopularShowResp extends Error {

    private List<ShowBasicInfo> showNames;
    private Integer pageCount;
    String errorMessage;

    public List<ShowBasicInfo> getShowNames() {
        return showNames;
    }

    public void setShowNames(List<ShowBasicInfo> showNames) {
        this.showNames = showNames;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

}
