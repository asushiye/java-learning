package com.morange.system.page;

import org.springframework.data.domain.Page;

/**
 * @author : zhenyun.su
 * @since : 2019/1/8
 * @commit: return ResponsePage or ResponsePageEx; converter Spring Page
 */

public class ResponsePageUtils {

    public static ResponsePage of() { return new ResponsePage();
    }

    public static ResponsePage of(Page page) {
        ResponsePage responsePage = new ResponsePage();
        responsePage.setContent(page.getContent());
        responsePage.setRealSize(page.getNumberOfElements());
        responsePage.setTotalSize(page.getTotalElements());
        return responsePage;
    }

    public static ResponsePageEx ofEx() { return new ResponsePageEx();
    }


    public static ResponsePageEx ofEx(Page page) {
        ResponsePageEx responsePage = new ResponsePageEx();
        responsePage.setContent(page.getContent());
        responsePage.setFirst(page.isFirst());
        responsePage.setLast(page.isLast());
        responsePage.setPaged(page.getPageable().isPaged());
        responsePage.setSorted(page.getPageable().getSort().isSorted());
        responsePage.setRealPage(page.getNumber());
        responsePage.setRealSize(page.getNumberOfElements());
        responsePage.setTotalPage(page.getTotalPages());
        responsePage.setTotalSize(page.getTotalElements());
        return responsePage;
    }
}
