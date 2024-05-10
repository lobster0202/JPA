package com.ohgiraffers.jpql.section01.simple;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SimpleJPQLRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /* 결과 값의 타입을 지정할 수 있을 때 */
    public String selectSingleMenuByTypedQuery() {

        String jpql = "SELECT m.menuName FROM Section01Menu as m WHERE m.menuCode = 8";

        entityManager.createQuery(jpql, String.class);

        /* String.class : 여기에 적은 타입이 TypedQuery<T>의 타입으로 들어간다. */
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        String resultMenuName = query.getSingleResult();

        return resultMenuName;
    }

    /* 결과 타입을 지정할 수 없어서 Object를 써줬을 때 */
    public Object selectSingleMenuByQuery() {

        String jpql = "SELECT m.menuName FROM Section01Menu as m WHERE m.menuCode = 8";

        entityManager.createQuery(jpql, String.class);

        Query query = entityManager.createQuery(jpql);
        Object resultMenuName = query.getSingleResult();

        return resultMenuName;
    }

    public Menu selectSingleRowByTypedQuery() {

        /* 모든 값을 불러오고 싶으면 m.* 을 쓰는게 아니라 그냥 별칭인 m만 써도 모든 값이 불러와짐 */
        String jpql = "SELECT m FROM Section01Menu as m WHERE m.menuCode = 8";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);
        Menu resultMenu = query.getSingleResult();

        return resultMenu;
    }


    public List<Menu> selectMultiRowByTypedQuery() {
        String jpql = "SELECT m FROM Section01Menu as m";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);

        List<Menu> resultMenuList = query.getResultList();

        return resultMenuList;
    }

    public List<Integer> selectUsingDistinct() {
        String jpql = "SELECT DISTINCT m.categoryCode FROM Section01Menu as m";
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);

        List<Integer> resultCategoryList = query.getResultList();

        return resultCategoryList;
    }

    /* 11, 12 카테고리 코드를 가진 메뉴 목록 조회 */
    public List<Menu> selectUsigIn() {
        String jpql =  "SELECT m " +
                        "FROM Section01Menu as m " +
                        "WHERE m.categoryCode " +
                        "IN (11,12) ";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);

        List<Menu> resultMenuListInCategoryCode = query.getResultList();

        return resultMenuListInCategoryCode;
    }

    /* "마늘"이라는 문자열이 메뉴명에 포함되는 메뉴 목록 조회 */
    public List<Menu> selectUsigLike() {
        String jpql = "SELECT m FROM Section01Menu as m WHERE m.menuName Like '%마늘%' ";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);

        List<Menu> resultMenuListLikeMenuName = query.getResultList();

        return resultMenuListLikeMenuName;
    }


}
