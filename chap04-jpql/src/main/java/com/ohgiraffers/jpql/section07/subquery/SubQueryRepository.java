package com.ohgiraffers.jpql.section07.subquery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubQueryRepository {

    /* 내가 외워야 하는 부분
    JPQL에서는 SELECT나 FROM절에서 서브 쿼리를 쓰는 것이 불가능 하다.
     * WHERE절에서 쓴다. */

    @PersistenceContext
    private EntityManager entityManager;

    public List<Menu> selectWithSubQuery(String categoryName) {
        String jpql = "SELECT m FROM Section07Menu m WHERE m.categoryCode = " +
                "(SELECT c.categoryCode FROM Section07Category c WHERE c.categoryName = :categoryName)";


        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class)
                .setParameter("categoryName", categoryName)
                .getResultList();
        return menuList;
    }


}
