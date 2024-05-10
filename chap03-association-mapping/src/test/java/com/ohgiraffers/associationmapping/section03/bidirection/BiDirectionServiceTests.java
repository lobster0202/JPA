package com.ohgiraffers.associationmapping.section03.bidirection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BiDirectionServiceTests {
    @Autowired
    private BiDirectionService biDirectionService;

    /* (석현) 진짜 연관관계는 처음부터 Join이 되면서 즉시 로딩이 되는것을 볼 수 있음 (ManyToOne)
    *  가짜 연관관계는 기본적으로 지연 로딩이 일어났다. (OneToMany)
    *  즉 FK를 가지고 있냐 없냐에 따라서 즉시 로딩과 지연 로딩이 갈리는 것을 알 수 있다. */

    @DisplayName("양방향 연관 관계 매핑 조회 테스트(연관 관계의 진짜 주인)")
    @Test
    void biDirectionFindTest1() {
        // given
        int menuCode = 10;

        // when
        // 진짜 연관 관계 : 처음 조회 시부터 조인한 결과를 인출해 온다. (즉시 로딩)
        Menu foundMenu = biDirectionService.findMenu(menuCode);

        // then
        assertEquals(menuCode, foundMenu.getMenuCode());
    }

    @DisplayName("양방향 연관 관계 매핑 조회 테스트(연관 관계의 가짜 주인)")
    @Test
    void biDirectionFindTest2() {
        // given
        int categoryCode = 10;

        // when
        // 가짜 연관 관계 : 해당 엔티티를 조회하고 필요시 연관 관계 엔티티를 조회하는 쿼리를 실행 (지연 로딩)
        Category foundCategory = biDirectionService.findCategory(categoryCode);

        // then
        assertEquals(categoryCode, foundCategory.getCategoryCode());
    }

    /* 이제 코드를 좀 고쳐 볼 예정
    * Category 와 Menu 클래스에서 ToString을 둘 다 써주면 오류가 난다.
    * 그 이유는 우로보로스 처럼 서로가 계속 참조하고 참조해서 StackOverflow가 발생한다. */


}
