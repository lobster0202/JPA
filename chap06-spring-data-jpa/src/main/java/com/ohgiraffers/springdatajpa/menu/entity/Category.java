package com.ohgiraffers.springdatajpa.menu.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
/* 기본생성자 만들어주는 어노테이션으로써
기본생정자의 접근제한자를 PROTECTED로 설정해주겠다는 뜻 */
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;
}
