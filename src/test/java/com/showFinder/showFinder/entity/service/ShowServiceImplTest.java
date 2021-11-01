package com.showFinder.showFinder.entity.service;

import com.showFinder.showFinder.entity.dao.ShowServicePrimaryDao;
import com.showFinder.showFinder.entity.dao.ShowServiceSecondaryDao;
import com.showFinder.showFinder.model.data.ShowBasicInfo;
import com.showFinder.showFinder.model.data.ShowInfo;
import com.showFinder.showFinder.model.facade.GetFilterShowsRqst;
import com.showFinder.showFinder.model.facade.GetFilteredShowsResp;
import com.showFinder.showFinder.model.facade.GetPopularShowResp;
import com.showFinder.showFinder.model.facade.GetShowInfoResp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
@SpringBootTest
@ExtendWith(SpringExtension.class)
class ShowServiceImplTest {


    ShowBasicInfo showBasicInfo = new ShowBasicInfo(1, "A Quiet place", 2018);
    ShowBasicInfo showBasicInfo2 = new ShowBasicInfo(2, "A Quiet place 2", 2020);
    ShowBasicInfo showBasicInfo3 = new ShowBasicInfo(3, "A Quiet place 3", 2020);

    ShowInfo show = new ShowInfo(1, "A Quiet place2", 7.5, 2018, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, null);

    @MockBean
    ShowServicePrimaryDao showServicePrimaryDao;

    @MockBean
    ShowServiceSecondaryDao showServiceSecondaryDao;
    @Autowired
    ShowServiceImpl showService;

    @Test
    void findByShowId() {
        when(showServicePrimaryDao.findByShowId(anyInt())).thenReturn(Arrays.asList(show)); // Added this line
        GetShowInfoResp getShowInfoResp= new GetShowInfoResp();
        getShowInfoResp.setShowNames(Arrays.asList(show));

        GetShowInfoResp getShowInfoResp1= showService.findByShowId(1);
        assertEquals(1,getShowInfoResp1.getShowNames().size());
    }

    @Test
    void findPopularShows() {
        when(showServicePrimaryDao.findPopularShows(any())).thenReturn(Arrays.asList(showBasicInfo,showBasicInfo2));
       GetPopularShowResp getPopularShowResp= showService.findPopularShows();
       assertEquals(2, getPopularShowResp.getShowNames().size());
    }

    @Test
    void findPopularShowsByPage() {
        when(showServicePrimaryDao.findPopularShows(any())).thenReturn(Arrays.asList(showBasicInfo,showBasicInfo2));
        GetPopularShowResp getPopularShowResp= showService.findPopularShowsByPage(1);
        assertEquals(2, getPopularShowResp.getShowNames().size());
    }

    @Test
    void getFilteredShows() throws InvocationTargetException, IllegalAccessException {
        when(showServiceSecondaryDao.findFilteredShows(7.8,2018,2020,"and e.thrillerFlag=true ",0)).thenReturn(Arrays.asList(showBasicInfo,showBasicInfo2));
        when(showServiceSecondaryDao.countFilteredShows(7.8,2018,2020,"and e.thrillerFlag=true ")).thenReturn(2);
        GetFilterShowsRqst getFilterShowsRqst=new GetFilterShowsRqst();
        getFilterShowsRqst.setRatings(7.8);
        getFilterShowsRqst.setGenre(Arrays.asList("thriller"));
        getFilterShowsRqst.setReleaseYearRange("2018-2020");
        getFilterShowsRqst.setPageNo(1);
        GetFilteredShowsResp getFilteredShowsResp= showService.getFilteredShows(getFilterShowsRqst);
        System.out.println(getFilteredShowsResp.getShowInfoList());
        assertEquals(2, getFilteredShowsResp.getShowInfoList().size());
    }

    @Test
    void searchShows() {

        when(showServicePrimaryDao.findShow("a quiet", PageRequest.of(0,15))).thenReturn(Arrays.asList(showBasicInfo,showBasicInfo2));
        GetFilterShowsRqst getFilterShowsRqst=new GetFilterShowsRqst();
        getFilterShowsRqst.setName("a quiet");
        GetFilteredShowsResp getFilteredShowsResp= showService.searchShows(getFilterShowsRqst);
        assertEquals(2, getFilteredShowsResp.getShowInfoList().size());
    }

    @Test
    void setImage() {
        List<ShowBasicInfo> showBasicInfos=Arrays.asList(showBasicInfo,showBasicInfo3);
        showService.setImage(showBasicInfos);
        assertNotNull(showBasicInfos.get(0).getImage());
        assertNull(showBasicInfos.get(1).getImage());
    }

    @Test
    void setImageForInfo() {
        List<ShowInfo> s=Arrays.asList(show);
        showService.setImageForInfo(s);
        assertNotNull(s.get(0).getImage());
    }
}