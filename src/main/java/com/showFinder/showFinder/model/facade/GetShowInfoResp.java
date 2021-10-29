package com.showFinder.showFinder.model.facade;

import com.showFinder.showFinder.model.data.Error;
import com.showFinder.showFinder.model.data.ShowInfo;

import java.util.List;

public class GetShowInfoResp extends Error {
    List<ShowInfo> showNames;

    public List<ShowInfo> getShowNames() {
        return showNames;
    }

    public void setShowNames(List<ShowInfo> showNames) {
        this.showNames = showNames;
    }
}
