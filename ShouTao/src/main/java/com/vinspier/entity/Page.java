package com.vinspier.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/12/9 0009.
 */
public class Page<T> {
    private List<T> data;//当前页数据
    private int pageNumber;//当前页 有页面传递
    private int totalRecord;//总条数
    private int pageSize;//每页显示条数
    private int totalPage;//总页数

    public Page(int pageNumber,int pageSize){
        super();
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return (int)Math.ceil(totalRecord*1.0/pageSize);
    }

    public int getStartIndex(){
        return (pageNumber-1)*pageSize;
    }


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
