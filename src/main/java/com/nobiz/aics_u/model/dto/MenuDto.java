package com.nobiz.aics_u.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuDto {
    private int menuId;
    private String menuText;
    private String link;

    List<MenuDto> subMenus;
}
