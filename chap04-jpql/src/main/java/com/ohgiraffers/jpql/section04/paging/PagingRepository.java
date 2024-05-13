package com.ohgiraffers.jpql.section04.paging;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PagingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Menu> usingPagingAPI(int offset, int limit) {

        /* 엔티티의 필드명에 따라서 정렬을 한다는 뜻. */
        String jpql = "SELECT m FROM Section04Menu m ORDER BY m.menuCode DESC";

        List<Menu> pagingMenuList = entityManager.createQuery(jpql, Menu.class)
                /* 어디서부터 조회를 할거냐 */
                .setFirstResult(offset)

                /* 어디까지 조회를 할거냐(최대 몇 개 까지 조회를 할거냐) */
                .setMaxResults(limit)

                /* 여러 행을 나타내기 위해 */
                .getResultList();

        return pagingMenuList;
    }
}
