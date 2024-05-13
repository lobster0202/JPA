package com.ohgiraffers.nativequery.section02.namedquery;

import jakarta.persistence.*;

@SqlResultSetMapping(name = "categoryCountAutoMapping2",
        entities = {@EntityResult(entityClass = Category.class)},
        columns = {@ColumnResult(name = "menu_count")}
)
@NamedNativeQueries(
        value = {
                @NamedNativeQuery(
                        name = "Category.menuCountOfCategory",
                        query = "SELECT a.category_code, a.category_name, a.ref_category_code," +
                        " COALESCE(v.menu_count, 0) menu_count" +
                        " FROM tbl_category a" +
                        " LEFT JOIN (SELECT COUNT(*) AS menu_count, b.category_code" +
                        " FROM tbl_menu b" +
                        " GROUP BY b.category_code) v ON (a.category_code = v.category_code)" +
                        " ORDER BY 1",
                        /* resultSetMapping을 위에 어노테이션과 연결시키겠다는 뜻
                        * 이름이 겹치면 안돼서 끝에 2를 넣음  */
                        resultSetMapping = "categoryCountAutoMapping2"
                )
        }
)
@Entity(name = "Section02Category")
@Table(name = "tbl_category")
public class Category {

    @Id
    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;

    @Override
    public String toString() {
        return "Category{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                '}';
    }
}
