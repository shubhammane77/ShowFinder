package com.showFinder.showFinder.entity.dao;


import com.showFinder.showFinder.model.data.ShowBasicInfo;
import com.showFinder.showFinder.model.data.ShowInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ShowServicePrimaryDao extends JpaRepository<ShowInfo, Long> {
    
    List<ShowInfo> findByShowId(Integer id);

    @Query("select new com.showFinder.showFinder.model.data.ShowBasicInfo(e.showId, e.showName,e.releaseyear) from ShowInfo e where e.rating > 7 order by e.releaseyear desc")
    List<ShowBasicInfo> findPopularShows(Pageable pageable);


    @Query(value = "select count(*) from ShowInfo r where r.rating > 7")
    Integer getTotalPageCount();

    @Query("select new com.showFinder.showFinder.model.data.ShowBasicInfo(e.showId, e.showName,e.releaseyear) from ShowInfo e where lower(e.showName) like lower(concat('%', :searchCriteria,'%')) order by e.releaseyear desc")
    List<ShowBasicInfo> findShow(String searchCriteria, Pageable pageable);
}
