package com.showFinder.showFinder.ServiceFacade;

import com.showFinder.showFinder.entity.service.ShowServiceImpl;
import com.showFinder.showFinder.model.facade.GetFilterShowsRqst;
import com.showFinder.showFinder.model.facade.GetFilteredShowsResp;
import com.showFinder.showFinder.model.facade.GetPopularShowResp;
import com.showFinder.showFinder.model.facade.GetShowInfoResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;


@RestController
@RequestMapping("/showInfo")
class ServiceFacade {

    @Autowired
    private ShowServiceImpl service;

    @GetMapping("/{id}")
    public GetShowInfoResp findShowInfo(@PathVariable("id") Integer id) {

        return service.findByShowId(id);
    }
    @GetMapping("/popular")
    public GetPopularShowResp findPopularShows() {

        return service.findPopularShows();
    }

    @GetMapping("/popular/{pageno}")
    public GetPopularShowResp findPopularShows(@PathVariable("pageno") Integer pageNo) {

        return service.findPopularShowsByPage(pageNo);
    }

    @GetMapping(path = "filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetFilteredShowsResp filter(@RequestParam String releaseyear, @RequestParam double rating, @RequestParam String[] genre, @RequestParam Integer pageno ) throws InvocationTargetException, IllegalAccessException {
        GetFilterShowsRqst getFilterShowsRqst= new GetFilterShowsRqst();
        getFilterShowsRqst.setGenre(Arrays.asList(genre));
        getFilterShowsRqst.setRatings(rating);
        getFilterShowsRqst.setPageNo(pageno);
        getFilterShowsRqst.setReleaseYearRange(releaseyear);
       GetFilteredShowsResp shows = service.getFilteredShows(getFilterShowsRqst);
        return shows;
    }

    @GetMapping(path = "search", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetFilteredShowsResp search(@RequestParam(value = "search") String searchCriteria) throws InvocationTargetException, IllegalAccessException {
        GetFilterShowsRqst getFilterShowsRqst= new GetFilterShowsRqst();
        getFilterShowsRqst.setName(searchCriteria);

        GetFilteredShowsResp shows = service.searchShows(getFilterShowsRqst);
        return shows;
    }
}