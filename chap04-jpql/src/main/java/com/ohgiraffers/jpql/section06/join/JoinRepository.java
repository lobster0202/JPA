package com.ohgiraffers.jpql.section06.join;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JoinRepository {

    @PersistenceContext
    private EntityManager entityManager;


    /* 메뉴 행 마다 얘가 어떤 카테고리인지 보여주기 위해서 카
        테고리 부분이 계속 반복해서 보여지는 것
        굉장히 비효율적이다. */
    public List<Menu> selectByInnerJoin() {
        String jpql = "SELECT m FROM Section06Menu m JOIN m.category c";

        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class).getResultList();

        return menuList;

    }

    /* 위에 InnerJoin의 문제점을 해결하기 위해서 FETCH를 사용한다.
    * fetch : 즉시 로딩을 호출해서 불러온다.  */

    public List<Menu> selectByFetchJoin() {
        String jpql = "SELECT m FROM Section06Menu m JOIN FETCH m.category c";

        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class).getResultList();

        return menuList;

    }

    /* 스칼라 타입 프로젝션 */
    public List<Object[]> selectByOuterJoin() {

        /* 연관관계 매핑을 통해서 .(점)으로 연결 할 수 있다는 것을 보여주는 것 */
        String jpql = "SELECT m.menuName, c.categoryName FROM Section06Menu m RIGHT JOIN m.category c" +
                " ORDER BY m.category.categoryCode";

        List<Object[]> menuList = entityManager.createQuery(jpql).getResultList();

        return menuList;

    }

    public List<Object[]> selectByCollectionJoin() {

        String jpql = "SELECT m.menuName, c.categoryName FROM Section06Category c LEFT JOIN c.menuList m";

        List<Object[]> categoryList = entityManager.createQuery(jpql).getResultList();

        return categoryList;

    }

    public List<Object[]> selectByThetaJoin() {

        /* FROM 절에다가 Entity를 다 적어주면 알아서 Cross Join이 됨 */
        /* 이렇게 하면 크로스 조인을 해서 모든 경우의 수를 조인함 */
        String jpql = "SELECT m.menuName, c.categoryName FROM Section06Category c, Section06Menu m";

        List<Object[]> categoryList = entityManager.createQuery(jpql).getResultList();

        return categoryList;

    }




}
