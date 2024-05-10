package com.ohgiraffers.jpql.section03.projection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /* 1. 엔티티 프로젝션 */
    public List<Menu> singleEntityProjection() {
        String jpql = "SELECT m FROM Section03Menu m";

        return entityManager.createQuery(jpql, Menu.class).getResultList();
    }

    /* 2. 임베디드(embedded) 프로젝션 */
    public List<MenuInfo> embeddedTypeProjection() {

        /* EmbeddedMenu 안에 있는 menuInfo의 모든 정보를 달라는거라서
        * menuInfo 안에 있는 필드들을 나타내 달라고 하는거임 */
        /* 엔티티의 필드명을 넣을 순 있지만 4번 같은 경우는 그럴 수가 없어서
        * 클래스의 풀네임을 써 주는 것 */
        String jpql = "SELECT m.menuInfo FROM EmbeddedMenu m";

        return entityManager.createQuery(jpql, MenuInfo.class).getResultList();
    }

    /* 3. 스칼라 타입 프로젝션 */
    public List<Object[]> scalarTypeProjection() {
        /* c.categoryCode과 c.categoryName 같은 특정 타입이 아닌 애매한 타입으로 나타내주기가 쉽지 않다. */
        String jpql =   "SELECT c.categoryCode, " +
                               "c.categoryName " +
                        "FROM Section03Category c";

        return entityManager.createQuery(jpql).getResultList();
    }

    /* 4. new 명령어를 활용한 프로젝션
    * 스칼라 처럼 Object 타입으로 받는게 아니라 클래스 자체로 받아버리고 싶으면
    * 클래스를 프로젝션 하면 된다. */
    public List<CategoryInfo> newCommandProjection() {
        /* 클래스 타입에다가 매핑을 하고 있다. */
        String jpql = "SELECT new com.ohgiraffers.jpql.section03.projection.CategoryInfo(c.categoryCode, c.categoryName)" +
                "FROM Section03Category c";

        return entityManager.createQuery(jpql, CategoryInfo.class).getResultList();
    }
}
