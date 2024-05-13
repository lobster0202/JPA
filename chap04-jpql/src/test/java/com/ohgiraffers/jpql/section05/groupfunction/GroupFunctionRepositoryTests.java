package com.ohgiraffers.jpql.section05.groupfunction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GroupFunctionRepositoryTests {

    @Autowired
    private GroupFunctionRepository groupFunctionRepository;

    @DisplayName("특정 카테고리에 등록 된 메뉴 수 조회")
    @Test
    void testCountMenuOfCategory() {
        int categoryCode = 555;
        long countOfMenu = groupFunctionRepository.countMenuOfCategory(categoryCode);

        assertTrue(countOfMenu >= 0);

        /* 없는 카테고리 코드를 넣어도 Count는 문제가 없다. */
        System.out.println("countOfMenu = " + countOfMenu);
    }

    @DisplayName("COUNT 외의 다른 그룹 함수 조회 결과가 없는 경우 테스트")
    @Test
    void testOtherWithNoResult() {
        int categoryCode = 555;
//        long sumOfMenu = groupFunctionRepository.otherWithNoResult(categoryCode);
//        assertTrue(sumOfMenu >= 0);

        assertDoesNotThrow(
                () -> {
                    /* 기본 자료형으로는 안돼서 Wrapper 클래스로 바꿔서 나타내줌  */
                    Long suumOfMenu = groupFunctionRepository.otherWithNoResult(categoryCode);
                    System.out.println("sumOfMenu = " + suumOfMenu);
                }
        );
        /* 하지만 다른 그룹 함수는 없는 코드를 넣었을 때 NULL 값이 반환되서 오류가 생긴다. */
//        System.out.println("메뉴의 합계 : " + sumOfMenu);
    }

    @DisplayName("HAVING절 조회 테스트")
    @Test
    void testSelectByGroupByHaving() {
        // int minPrice = 50000;
        long minPrice = 50000L;
        List<Object[]> sumPriceOfCategoryList = groupFunctionRepository.selectByGroupByHaving(minPrice);
        assertNotNull(sumPriceOfCategoryList);

        sumPriceOfCategoryList.forEach(System.out::println);
    }
}
