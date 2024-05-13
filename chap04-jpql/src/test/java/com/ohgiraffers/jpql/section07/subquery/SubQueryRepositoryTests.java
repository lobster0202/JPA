package com.ohgiraffers.jpql.section07.subquery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SubQueryRepositoryTests {

    @Autowired
    private SubQueryRepository subQueryRepository;

    @DisplayName("서브쿼리 조회 테스트")
    @Test
    void testSelectWithSubQuery() {
        String categoryName = "한식";

        List<Menu> menuList = subQueryRepository.selectWithSubQuery(categoryName);

        assertNotNull(menuList);

        menuList.forEach(System.out::println);
    }
}
