package com.showFinder.showFinder.entity.dao;

import com.showFinder.showFinder.model.data.ShowBasicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ShowServiceSecondaryDao {

    @Autowired
    EntityManager entityManager;


    public List<ShowBasicInfo> findFilteredShows(double rating, int lowerYearRange, int upperYearRange, String dynamicQuery, int pageno) {
        Query query = entityManager.createQuery("select new com.showFinder.showFinder.model.data.ShowBasicInfo(e.showId, e.showName,e.releaseyear) from ShowInfo e where e.rating > :rating and e.releaseyear >= :lowerYearRange and e.releaseyear <= :upperYearRange "+dynamicQuery + " order by e.releaseyear desc");
        query.setParameter("upperYearRange",upperYearRange);
        query.setParameter("lowerYearRange",lowerYearRange);
        query.setParameter("rating",rating);
        query.setFirstResult(pageno * 15);
        query.setMaxResults(15);
        return (List) query.getResultList();

        }

    public int countFilteredShows(double rating, int lowerYearRange, int upperYearRange, String dynamicQuery) {
        Query query = entityManager.createQuery("select count(*) from ShowInfo e where e.rating > :rating and e.releaseyear >= :lowerYearRange and e.releaseyear <= :upperYearRange "+dynamicQuery);
        query.setParameter("upperYearRange",upperYearRange);
        query.setParameter("lowerYearRange",lowerYearRange);
        query.setParameter("rating",rating);

        return Integer.parseInt(String.valueOf(query.getSingleResult()));
    }

    }
