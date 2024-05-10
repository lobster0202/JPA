package com.ohgiraffers.associationmapping.section01.manytoone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ManyToOneRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Menu find(int menuCode) {
        /* find : 특정 메뉴 코드를 가진 메뉴 엔티티를 찾아온다. */
        return entityManager.find(Menu.class, menuCode);
    }

    public String findCategoryName(int menuCode) {
        String jpql = "SELECT c.categoryName " +

                /* menu_and_category는 menu 엔티티를 뜻한다. */
                      "FROM menu_and_category m " +

                /* JOIN의 기준은 테이블이 아니라 필드를 기준으로 조인한다. */
                      "JOIN m.category c " +

                /* .setParameter("menuCode" 에 있는 menuCode를 쓴다.  */
                      "WHERE m.menuCode = :menuCode";

        return entityManager.createQuery(jpql, String.class)
                .setParameter("menuCode", menuCode)
                .getSingleResult();
    }

    public void regist(Menu menu) {
        entityManager.persist(menu);
    }
}
