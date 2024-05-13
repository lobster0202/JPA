package com.ohgiraffers.jpql.section08.namedquery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NamedQueryRepository {

    /* 이렇게 코드를 하나하나 작성하는건 JPQL의 불편한 과정이고
    * 내가 나중에 필요하면 찾아서 쓰는 식으로 하면 괜춘할듯
    * 그리고 프로젝트 작업할 때 마이바티스랑 연동해서도 가능
    * 동적 쿼리를 작성하는데 있어서는 부족함이 너무나 많다.
    * 따라서 라이브러리로 좀 어캐어캐 잘 해줘야 한다. */

    @PersistenceContext
    private EntityManager entityManager;

    public List<Menu> selectByDynamicQuery(String searchName, int searchCode) {

        StringBuilder jpql = new StringBuilder("SELECT m FROM Section08Menu m ");

        if(searchName != null && !searchName.isEmpty() && searchCode > 0) {
            jpql.append("WHERE ");
            jpql.append("m.menuName LIKE '%' || :menuName || '%'");
            jpql.append("AND ");
            jpql.append("m.categoryCode = :categoryCode");

        } else {

            if(searchName != null && !searchName.isEmpty()) {
                jpql.append("WHERE ");
                jpql.append("m.menuName LIKE '%' || :menuName || '%'");

            } else if(searchCode > 0) {
                jpql.append("WHERE ");
                jpql.append("m.categoryCode = :categoryCode");
            }
        }

        TypedQuery<Menu> query = entityManager.createQuery(jpql.toString(), Menu.class);

        if(searchName != null && !searchName.isEmpty() && searchCode > 0) {
            query.setParameter("menuName", searchName);
            query.setParameter("categoryCode", searchCode);

        } else {

            if(searchName != null && !searchName.isEmpty()) {
                query.setParameter("menuName", searchName);

            } else if(searchCode > 0) {
                query.setParameter("categoryCode", searchCode);
            }
        }

        List<Menu> menuList = query.getResultList();

        return menuList;
    }


    /* NamedQuery를 이용한 동적 쿼리 */
    public List<Menu> selectByNamedQuery() {
        /* NamedQuery를 사용할때는 메소드가 살짝 다름
        * Section08Menu.selectMenuList : Menu에서 클래스에서 쓴 어노테이션에 적힌 이름 넣어주면 됨. */
        List<Menu> menuList = entityManager.createNamedQuery("Section08Menu.selectMenuList", Menu.class)
                .getResultList();
        return menuList;

        /* 야믈 같은 경우는 -(대시) 쓰고 경로 적고가 가능하다. */
        /* 순서 */
    }

    public List<Menu> selectByNamedQueryWithXml(int menuCode) {
        List<Menu> menuList = entityManager.createNamedQuery("Section08Menu.selectMenuByCode", Menu.class)
                .setParameter("menuCode",menuCode)
                .getResultList();
        return menuList;

        /* 순서 : menu-query.xml 파일 생성 -> 레퍼지토리에서 메소드 작성 -> 테스트 메소드 작성
        * 의미 : xml 파일에 SQL문을 작성해두고 NamedQuery를 이용해서 또 다른 정적 쿼리를 사용하는 것. */

    }






}
