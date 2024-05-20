package com.ohgiraffers.springdatajpa.menu.service;

import com.ohgiraffers.springdatajpa.menu.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.menu.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.entity.Category;
import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import com.ohgiraffers.springdatajpa.menu.repository.CategoryRepository;
import com.ohgiraffers.springdatajpa.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    /* 이번 시간에는 JPARepository중 하나인 CRUDRepository에 대해서 배운다. */

    private final MenuRepository menuRepository;

    private final ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;

    /* 1. findById */
    public MenuDTO findMenuByMenuCode(int menuCode) {
        /* findById : CrudRepository의 메소드다.
         * Optional 타입 : 널포인트 익셉션 방지하는 타입
         * orElseThrow : Optional이 가지는 속성 중 하나 */
        Menu foundeMenu = menuRepository.findById(menuCode).orElseThrow(IllegalArgumentException::new);

        /* map : Object source 를 넣으면 Class<D> destinationType으로 변환시킨다는 뜻이다.
         * 예를 들어서 foundeMenu라는 엔티티를 MenuDTO 타입으로 가공해줄 수 있다. */
        return modelMapper.map(foundeMenu, MenuDTO.class);

    }


    /* 2. findAll : Sort
     * findAll(Sort sort) : 내가 원하는 기준으로 정렬할 수 있다.
     * Sort.by() 라는 메소드를 통해 정렬을 한다.
     * descending() 메소드를 통해 내림차순으로 할 수 있다. */
    public List<MenuDTO> findMenuList() {
        List<Menu> menuList = menuRepository.findAll(Sort.by("menuCode").descending());

        /* (11:48) List 타입으로 바꾸는 것은 아까 위에 있던 메소드처럼 간편하지가 않다.
         * stream 메소드 : List 타입으로 바꿔주기 위해서 써줘야 한다.
         * 순서 : steam()을 통해 Menu로 바꿔주고
         * map을 통해서 Menu를 MenuDTO로 바꾸고
         * 컬렉션 뭐시기 뭐시기 */

        return menuList.stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .toList();
    }

    /* 3. findAll : Pageable
     * getPageNumber : 몇개씩 조회하고 싶은지
     * getPageSize : 몇개를 나타내느냐
     *  <= 0 ? 0 : pageable.getPageNumber() - 1 : pageable는 0부터 시작함
     * findAll(Pageable) 메소드는 Page로 반환이 됨
     * Page 클래스에서 map이라는 메소드를 자체 제공한다. */
    public Page<MenuDTO> findMenuList(Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,  // offset
                pageable.getPageSize(),   // limit
                Sort.by("menuCode").descending()
        );
        Page<Menu> menuList = menuRepository.findAll(pageable);

        return menuList.map(menu -> modelMapper.map(menu, MenuDTO.class));

    }


    /* 4. Query Method*/
    public List<MenuDTO> findMenuPrice(Integer menuPrice) {


//        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThanOrderByMenuPrice(menuPrice);

        /* 위에 메소드가 너무나 길어서 간단하게 하고 싶다면
         * 정렬 파라미터를 넣어서 정렬을 시켜주는 방법도 있다. */
        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThan(
                menuPrice,
                Sort.by("menuPrice").descending()
        );

        return menuList.stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .toList();
    }

    /* 5. JPQL or Native Query */
    public List<CategoryDTO> findAllCategory() {

        List<Category> categoryList = categoryRepository.findAllCategory();

        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();
    }

    /* 6. save(insert) */
    @Transactional
    public void registMenu(MenuDTO menuDTO) {

        menuRepository.save(modelMapper.map(menuDTO, Menu.class));

    }

    /* 7. modify(update) */
    @Transactional
    public void modifyMenu(MenuDTO menuDTO) {

        /* 프로젝트를 만들 때 오류가 날텐데 그에 해당하는 익셉션을 처리하는 구문을 잘 작성해야한다. */
        Menu foundMenu = menuRepository.findById(menuDTO.getMenuCode())
                .orElseThrow(IllegalArgumentException::new);

        /* setter 사용 지양, 기능에 맞는 메소드 정의해서 사용 */
        foundMenu.modifyMenuName(menuDTO.getMenuName());

    }


    /* 8. deleteById */
    @Transactional
    public void deleteMenu(Integer menuCode) {

        menuRepository.deleteById(menuCode);

    }
}
