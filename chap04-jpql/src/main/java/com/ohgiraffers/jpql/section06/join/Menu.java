package com.ohgiraffers.jpql.section06.join;

import jakarta.persistence.*;

@Entity(name = "Section06Menu")
@Table(name = "tbl_menu")
public class Menu {

    @Id
    private int menuCode;
    private String menuName;
    private int menuPrice;
    @ManyToOne
    @JoinColumn(name = "categoryCode")
    private Category category;
    private String orderableStatus;
    public Menu() {

    }

}
