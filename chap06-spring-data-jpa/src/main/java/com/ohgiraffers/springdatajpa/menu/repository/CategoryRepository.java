package com.ohgiraffers.springdatajpa.menu.repository;

import com.ohgiraffers.springdatajpa.menu.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    /* Value라는 속성으로 넣어주면 이 메소드를 호출할 때  401*/
    /* findAll 메소드를 */
//    @Query("SELECT c FROM Category c ORDER BY c.categoryCode")
    /* 쿼리를 아래처럼 쓰고 싶으면 nativeQuery = true를 사용해줘야 인식한다. */
    @Query(value = "SELECT category_code, category_name, ref_category_code " +
            "FROM tbl_category ORDER BY category_code", nativeQuery = true)
    List<Category> findAllCategory();

}
