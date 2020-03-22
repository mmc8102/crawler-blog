package cn.mmc8102.blog.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mmc
 * 后台系统菜单实体类
 */
@Getter@Setter
public class Menu {
    private Long id;
    private String text;
    private String iconCls;
    private Boolean checked;
    private String state;
    private String attributes;
    private String function;
    private List<Menu> children = new ArrayList<>();
}