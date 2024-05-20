package com.ohgiraffers.springdatajpa.menu.controller;

import com.ohgiraffers.springdatajpa.common.Pagenation;
import com.ohgiraffers.springdatajpa.common.PagingButton;
import com.ohgiraffers.springdatajpa.menu.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.menu.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import com.ohgiraffers.springdatajpa.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/{menuCode}")
    public String findMenuByCode(@PathVariable int menuCode, Model model) {

        MenuDTO resultMenu = menuService.findMenuByMenuCode(menuCode);

        model.addAttribute("menu", resultMenu);

        return "menu/detail";
    }

    @GetMapping("/list")
    public String findMenuList(Model model, @PageableDefault Pageable pageable) {

        /* 페이징 처리 이전 */
//        List<MenuDTO> menuList = menuService.findMenuList();
//
//        model.addAttribute("menuList", menuList);

        /* 페이징 처리 이후 */
        /* @Slf4j : 를 사용하면 log 라는 객체를 사용할 수 있음 */
        /* @PageableDefault : 페이지 처리의 기본 설정
        @PageableDefault의 기본값을 확인해 보기 위해 사용 */
        log.info("pageable : {}", pageable);

        Page<MenuDTO> menuList = menuService.findMenuList(pageable);

        /* 1247 */
        log.info("{}", menuList.getContent()); // 그 페이지의 내용 알려달라
        log.info("{}", menuList.getTotalPages()); // 전체 페이지의 수 알려달라
        log.info("{}", menuList.getTotalElements()); // 전체 행의 수 알려달라
        log.info("{}", menuList.getSize()); // 몇개씩 보여주고 있는지 알려달라
        log.info("{}", menuList.getNumberOfElements()); // 실제로 표현되는 페이지의 갯수
        log.info("{}", menuList.isFirst()); // 첫번째 페이지가 맞냐 아니냐
        log.info("{}", menuList.isLast());  // 마지막 페이지가 맞냐 아니냐
        log.info("{}", menuList.getSort()); // 정렬 방식 알려달라
        log.info("{}", menuList.getNumber()); // 인덱스 알려달라

        PagingButton paging = Pagenation.getPagingButtonInfo(menuList);

        model.addAttribute("menuList", menuList);
        model.addAttribute("paging", paging);


        return "menu/list";

    }

    @GetMapping("/querymethod")
    public void querymehtodPage() {

    }

    @GetMapping("/search")
    public String findByMenuPrice(@RequestParam Integer menuPrice, Model model) {

        List<MenuDTO> menuList = menuService.findMenuPrice(menuPrice);

        model.addAttribute("menuList", menuList);

        return "menu/searchResult";
    }

    @GetMapping("/regist")
    public void registPage() {}

    @GetMapping("/category")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {
        return menuService.findAllCategory();
    }

    @PostMapping("/regist")
    public String registMenu(@ModelAttribute MenuDTO menuDTO) {

        menuService.registMenu(menuDTO);

        return "redirect:/menu/list";

    }

    @GetMapping("/modify")
    public void modifyPage() {

    }

    @PostMapping("/modify")
    public String modifyMenu(@ModelAttribute MenuDTO menuDTO, Model model) {

        menuService.modifyMenu(menuDTO);

        /* 수정한 메뉴 코드에 해당하는 메뉴의 번호로 리다이렉트 한다는 뜻. */
        return "redirect:/menu/" + menuDTO.getMenuCode();

    }

    @GetMapping("/delete")
    public void deletePage() {

    }

    @PostMapping("/delete")
    public String deleteMenu(@RequestParam Integer menuCode) {

        menuService.deleteMenu(menuCode);

        return "redirect:/menu/list";

    }
}
