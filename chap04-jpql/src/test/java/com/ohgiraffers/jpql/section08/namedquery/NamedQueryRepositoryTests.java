package com.ohgiraffers.jpql.section08.namedquery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class NamedQueryRepositoryTests {

    @Autowired
    private NamedQueryRepository namedQueryRepository;

    @DisplayName("동적쿼리를 이용한 조회 테스트")
    @Test
    public void testSelectByDynamicQuery() {
        String searchName = "null";
        int searchCode = 4;

        List<Menu> menuList = namedQueryRepository.selectByDynamicQuery(searchName, searchCode);

        assertNotNull(menuList);

        menuList.forEach(System.out::println);

    }
    @DisplayName("NamedQuery를 이용한 조회 테스트")
    @Test
    public void testSelectNamedQuery() {
        List<Menu> menuList = namedQueryRepository.selectByNamedQuery();

        assertNotNull(menuList);
        menuList.forEach(System.out::println);

    }

    @DisplayName("NamedQuery XML 파일을 이용한 조회 테스트")
    @Test
    public void testSelectNamedQueryWithXml() {
        int menuCode = 3;
        List<Menu> menuList = namedQueryRepository.selectByNamedQueryWithXml(menuCode);

        assertNotNull(menuList);
        menuList.forEach(System.out::println);

    }
}
