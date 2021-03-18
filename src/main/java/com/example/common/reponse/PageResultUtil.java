package com.example.common.reponse;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2021/1/26 20:14
 */
public class PageResultUtil<T> {
    private PageResultUtil(){

    }
    public static<T>   CommonPage<T> fromIPage(IPage<T> page){
        CommonPage<T> commonPage = new CommonPage<>();
        commonPage.setList(page.getRecords());
        commonPage.setPageSize(page.getSize());
        commonPage.setPageNum(page.getCurrent());
        commonPage.setTotal(page.getTotal());
        return commonPage;
    }
}
