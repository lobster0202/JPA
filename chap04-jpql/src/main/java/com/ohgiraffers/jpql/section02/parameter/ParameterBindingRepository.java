package com.ohgiraffers.jpql.section02.parameter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParameterBindingRepository {

    @PersistenceContext
    private EntityManager entityManager;


    /* :menuName = 파라미터 바인딩을 나타낸다. */
    public List<Menu> selectMenuByBindingName(String menuName) {
        /* AND를 통해서 파라미터를 더 넣을 수 있다. */
        String jpql = "SELECT m FROM Section02Menu m WHERE m.menuName = :menuName";

        /* TypedQuery에 setParameter를 적어줘야한다. */
        List<Menu> resultMenuList = entityManager.createQuery(jpql, Menu.class)
                .setParameter("menuName",menuName)
                .getResultList();

        return resultMenuList;
    }

    public List<Menu> selectMenuByBindingPosition(String menuName) {
        String jpql = "SELECT m FROM Section02Menu m WHERE m.menuName = ?1";
        List<Menu> resultMenuList = entityManager.createQuery(jpql, Menu.class)
                /* 위치같은 경우는 (숫자,변수)*/
                .setParameter(1,menuName)
                .getResultList();

        return resultMenuList;
    }

}
