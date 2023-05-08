package com.volta.project.common;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class PageUtils implements Serializable {
    private static final long serialVersionUID=1L;

    //总记录数
    private int totalCount;

    //每页记录数
    private int pageSize;

    //总页数
    private int totalPage;

    //当前第几页
    private int currPage;

    //列表数据
    private List<?> list;

    public PageUtils(int totalCount, int pageSize, int totalPage, int currPage, List<?> list) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.currPage = currPage;
        this.list = list;
    }

    /**
     * 分页（使用）
     * @param page
     */
    public PageUtils(IPage<?> page) {
        this.list=page.getRecords();
        this.totalCount= (int) page.getTotal();
        this.pageSize = (int)page.getSize();
        this.currPage = (int)page.getCurrent();
        this.totalPage = (int)page.getPages();
    }
}