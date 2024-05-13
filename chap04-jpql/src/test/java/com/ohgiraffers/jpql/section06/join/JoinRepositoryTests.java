package com.ohgiraffers.jpql.section06.join;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JoinRepositoryTests {

    @Autowired
    public JoinRepository joinRepository;

    @DisplayName("내부 조인 테스트")
    @Test
    void testSelectByInnerJoin() {

        List<Menu> menuList = joinRepository.selectByInnerJoin();

        assertNotNull(menuList);
    }

    @DisplayName("외부 조인 테스트")
    @Test
    void testSelectByOuterJoin() {

        List<Object[]> menuList = joinRepository.selectByOuterJoin();

        assertNotNull(menuList);

        menuList.forEach(
                row -> {
                    for (Object column : row) {
                        System.out.print(column + " ");
                    }
                    System.out.println();
                }
        );

    }

    @DisplayName("컬렉션 조인 테스트")
    @Test
    void testSelectByCollectionJoin() {

        List<Object[]> categoryList = joinRepository.selectByCollectionJoin();

        assertNotNull(categoryList);

        categoryList.forEach(
                row -> {
                    for (Object column : row) {
                        System.out.print(column + " ");
                    }
                    System.out.println();
                }
        );

    }

    @DisplayName("세타 조인 테스트")
    @Test
    void testSelectByThetaJoin() {

        List<Object[]> categoryList = joinRepository.selectByThetaJoin();

        assertNotNull(categoryList);

        categoryList.forEach(
                row -> {
                    for (Object column : row) {
                        System.out.print(column + " ");
                    }
                    System.out.println();
                }
        );

    }

    @DisplayName("패치 조인 테스트")
    @Test
    void testSelectByFetchJoin() {

        List<Menu> menuList = joinRepository.selectByFetchJoin();

        assertNotNull(menuList);
    }
}
