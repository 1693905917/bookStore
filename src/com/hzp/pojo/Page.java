package com.hzp.pojo;

import java.util.List;
import java.util.Objects;

/**
 * @author ASUS
 * @projectName book
 * @description: 分页
 * @date 2022-01-27 16:09
 */

/**
 * Page是分页的模型对象
 * @param <T>  具体的模块的javaBean类
 */
public class Page<T> {
    public static final Integer PAGE_SIZE=4;
    //当前页面
    private Integer pageNo;
    //总页码
    private Integer pageTotal;
    //当前页显示的数量
    private Integer pageSize=PAGE_SIZE;
    //总记录数
    private Integer pageTotalCount;
    //当前页数据
    private List<T> items;
    //分页条的请求地址
    private  String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Page(){

    }

    public Page(Integer pageNo, Integer pageTotal, Integer pageSize, Integer pageTotalCount, List<T> items) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.pageTotalCount = pageTotalCount;
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page<?> page = (Page<?>) o;
        return Objects.equals(pageNo, page.pageNo) && Objects.equals(pageTotal, page.pageTotal) && Objects.equals(pageSize, page.pageSize) && Objects.equals(pageTotalCount, page.pageTotalCount) && Objects.equals(items, page.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNo, pageTotal, pageSize, pageTotalCount, items);
    }



    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        /* 数据边界的有效检查 */
        if(pageNo<=0){
            pageNo=1;
        }
        if(pageNo > pageTotal){
            pageNo=pageTotal;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
