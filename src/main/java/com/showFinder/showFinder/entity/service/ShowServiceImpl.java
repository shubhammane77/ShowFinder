package com.showFinder.showFinder.entity.service;

import com.showFinder.showFinder.entity.dao.ShowServicePrimaryDao;
import com.showFinder.showFinder.entity.dao.ShowServiceSecondaryDao;
import com.showFinder.showFinder.model.data.ShowBasicInfo;
import com.showFinder.showFinder.model.data.ShowInfo;
import com.showFinder.showFinder.model.facade.GetFilterShowsRqst;
import com.showFinder.showFinder.model.facade.GetFilteredShowsResp;
import com.showFinder.showFinder.model.facade.GetPopularShowResp;
import com.showFinder.showFinder.model.facade.GetShowInfoResp;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class ShowServiceImpl {

    @Autowired
    ShowServicePrimaryDao showServicePrimaryDao;

    @Autowired
    ShowServiceSecondaryDao showServiceDAO;

    @Value("${imagepath}")
    private String imagePath;

    public GetShowInfoResp findByShowId(Integer id){
        GetShowInfoResp getShowInfoResp= new GetShowInfoResp();
        try{
            List<ShowInfo> showInfo;
           showInfo= showServicePrimaryDao.findByShowId(id);
            setImageForInfo(showInfo);
            getShowInfoResp.setShowNames(showInfo);
        }
        catch (Exception e){
            getShowInfoResp.setErrorCode("E102");
            getShowInfoResp.setErrorMessage("Exception while fetching show Info");
        }

        return getShowInfoResp;
    }

    
    public GetPopularShowResp findPopularShows(){
        GetPopularShowResp getPopularShowResp = new GetPopularShowResp();
        try {

            List<ShowBasicInfo> popNames = showServicePrimaryDao.findPopularShows(PageRequest.of(0, 15));
            if(popNames.size()>0) {
                setImage(popNames);
                getPopularShowResp.setShowNames(popNames);

                Integer showCount = showServicePrimaryDao.getTotalPageCount();

                getPopularShowResp.setPageCount((showCount / 15) + 1);
            } else {
                getPopularShowResp.setErrorCode("103");
                getPopularShowResp.setErrorMessage("No Popular shows found.");
            }


        } catch (Exception e){
            getPopularShowResp.setErrorCode("E100");
            getPopularShowResp.setErrorMessage("Exception while fetching popular shows");
        }

        return getPopularShowResp;

    }

    public GetPopularShowResp findPopularShowsByPage(int pageNo){
        GetPopularShowResp getPopularShowResp= new GetPopularShowResp();

        try {
            List<ShowBasicInfo> popNames = showServicePrimaryDao.findPopularShows(PageRequest.of(pageNo - 1, 15));
            setImage(popNames);
            getPopularShowResp.setShowNames(popNames);

        }catch (Exception e){
            getPopularShowResp.setErrorCode("E104");
            getPopularShowResp.setErrorMessage("Exception while fetching popular shows");
        }
        return  getPopularShowResp;
    }

    public GetFilteredShowsResp getFilteredShows(GetFilterShowsRqst getFilterShowsRqst) throws InvocationTargetException, IllegalAccessException {
        GetFilteredShowsResp getFilteredShowsResp= new GetFilteredShowsResp();
        try {
            int lowerYearRange = Integer.parseInt(getFilterShowsRqst.getReleaseYearRange().split("-")[0]);
            int upperYearRange = Integer.parseInt(getFilterShowsRqst.getReleaseYearRange().split("-")[1]);
            double rating = getFilterShowsRqst.getRatings();
            StringBuilder dynamicQuery = new StringBuilder();
            getFilterShowsRqst.getGenre().forEach(p -> {
                dynamicQuery.append("and e." + p + "Flag=true ");
            });
            List<ShowBasicInfo> showNames = showServiceDAO.findFilteredShows(rating, lowerYearRange, upperYearRange, dynamicQuery.toString(), (getFilterShowsRqst.getPageNo() - 1) * 15);
            if(showNames.size()>0) {
                setImage(showNames);
                getFilteredShowsResp.setShowInfoList(showNames);
                if (getFilterShowsRqst.getPageNo() == 1) {
                    int count = showServiceDAO.countFilteredShows(rating, lowerYearRange, upperYearRange, dynamicQuery.toString());
                    getFilteredShowsResp.setPageCount((count / 15) + 1);
                }
            } else {
                getFilteredShowsResp.setErrorCode("E105");
                getFilteredShowsResp.setErrorMessage("No Filtered shows found");
            }
        } catch (Exception e){
            getFilteredShowsResp.setErrorCode("E101");
            getFilteredShowsResp.setErrorMessage("Exception while fetching filtered shows");
        }
        return  getFilteredShowsResp;
    }

    public GetFilteredShowsResp searchShows(GetFilterShowsRqst getFilterShowsRqst) {
        GetFilteredShowsResp getFilteredShowsResp = new GetFilteredShowsResp();
        try {
            getFilteredShowsResp.setShowInfoList(showServicePrimaryDao.findShow(getFilterShowsRqst.getName(), PageRequest.of(0, 15)));
        } catch (Exception e){
            getFilteredShowsResp.setErrorCode("E106");
            getFilteredShowsResp.setErrorMessage("Exception while searching shows");
        }
        return getFilteredShowsResp;
    }


    public void setImage(List<ShowBasicInfo> showIds){
        showIds.stream().forEach(i-> {
            try {
            File f = new File(System.getProperty("user.home")+ imagePath + i.getShowId() + ".jpeg");
            FileInputStream fileInputStreamReader;

                fileInputStreamReader = new FileInputStream(f);
                byte[] bytes = new byte[(int) f.length()];
                fileInputStreamReader.read(bytes);
                i.setImage(new String(Base64.encodeBase64(bytes), "UTF-8"));

            } catch (IOException e) {
                i.setImage(null);
            }
        });
    }
    public void setImageForInfo(List<ShowInfo> showIds){
        showIds.stream().forEach(i-> {
            try {
            File f = new File(System.getProperty("user.home")+imagePath + i.getShowId() + ".jpeg");
            FileInputStream fileInputStreamReader;

                fileInputStreamReader = new FileInputStream(f);
                byte[] bytes = new byte[(int) f.length()];
                fileInputStreamReader.read(bytes);
                i.setImage(new String(Base64.encodeBase64(bytes), "UTF-8"));

            } catch (IOException e) {
                i.setImage(null);
            }
        });
    }
}
