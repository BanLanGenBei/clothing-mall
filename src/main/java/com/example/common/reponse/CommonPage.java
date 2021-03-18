package com.example.common.reponse;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @Description:
 *  分页封装
 * @Author: chenchong
 * @Date: 2021/1/26 20:04
 */
@Data
public class CommonPage<T> implements Serializable {
    private static final long serialVersionUID = -3318774074145199632L;
    /**
     * 当前页码
     */
    private Long pageNum = 1L;
    /**
     * 每页记录数
     */
    private Long pageSize = 20L;

    /**
     * 总数
     */
    private Long total = 0L;
    /**
     * 查询数据列表
     */
    private List<T> list = Collections.emptyList();
}
