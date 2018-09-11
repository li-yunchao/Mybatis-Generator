
package com.test.sealing.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="分页信息")
public class PageVO {
    /**
     * 总共记录数
     */
    @ApiModelProperty(value="总共记录数",name="total")
    private int total;
    /**
     * 总共页数
     */
    @ApiModelProperty(value="总共页数",name="totalPage")
    private int totalPage;
    /**
     * 当前页，默认为1
     */
    @ApiModelProperty(value="当前页",name="current")
    private int current = 1;
    /**
     * 页的大小(默认显示10条)
     */
    @ApiModelProperty(value="页的大小",name="pageSize")
    private int pageSize = 10;
    /**
     * 当前页的第一条记录的编号
     */
    @ApiModelProperty(value="当前页的第一条记录的编号",name="startRow")
    private int startRow = 0;
    private boolean hasPrevious;
    private boolean hasNext;
    private boolean bof;
    private boolean eof;

    /**
     * 构造方法
     * 
     * @param totalRow 总记录数
     */
    public PageVO(int totalRow) {
        this.total = totalRow;
        // 根据页大小和总记录数计算出总页数
        this.totalPage = countTotalPage(this.pageSize, this.total);
        setCurrent(this.current);
        // 获取当前页的第一条记录的编号
        if (total != 0) {
            this.startRow = getStartRow(this.pageSize, this.current);
        }
        // System.out.println("startRow:"+startRow);
        // 修正当前页

        init();
    }

    /**
     * 构造方法
     * 
     * @param totalRow 总记录数
     * @param pageSize 页的大小
     * @param page 页码
     */
    public PageVO(int totalRow, int pageSize, int page) {
        this.total = totalRow;
        this.pageSize = pageSize;
        // 根据页大小和总记录数计算出总页数
        this.totalPage = countTotalPage(this.pageSize, this.total);
        setCurrent(page);
        // 获取当前页的第一条记录的编号
        if (total != 0) {
            this.startRow = getStartRow(this.pageSize, this.current);
        }
        // System.out.println("startRow:"+startRow);
        // 修正当前页

        init();
    }

    /**
     * 获取当前页的第一条记录的编号
     * 
     * @param pageSize(页的大小)
     * @param page（页码）
     * @return
     */
    public int getStartRow(int pageSize, int page) {

        return pageSize * (page - 1);
    }

    /**
     * the total to get.
     * 
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * the total to set.
     * 
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public void setBof(boolean bof) {
        this.bof = bof;
    }

    public void setEof(boolean eof) {
        this.eof = eof;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getCurrent() {

        return this.current;
    }

    // 修正计算当前页
    public void setCurrent(int currentPage) {
        if (currentPage > this.totalPage) {
            this.current = this.totalPage;
        } else if (currentPage < 1) {
            this.current = 1;
        } else {
            this.current = currentPage;
        }
    }
    // 获取分页大小

    public int getPageSize() {
        return pageSize;
    }

    // 设置分页大小
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    // 获取当前页记录的开始索引
    public int getBeginIndex() {
        int beginIndex = (current - 1) * pageSize; // 索引下标从0开始
        if (beginIndex < 0) {
            return 0;
        }
        return beginIndex;
    }

    /**
     * 计算总页数
     * 
     * @param pageSize
     * @param totalRow
     * @return
     */
    public int countTotalPage(int pageSize, int totalRow) {
        int totalPage = totalRow % pageSize == 0 ? (totalRow / pageSize) : (totalRow / pageSize) + 1;
        return totalPage;
    }

    /**
     * 返回下一页的页码
     * 
     * @return
     */
    public int getNextPage() {

        if (current + 1 >= this.totalPage) { // 如果当前页已经是最后页 则返回最大页
            return this.totalPage;
        }
        return current + 1;
    }
    // 返回前一页的页码

    public int getPreviousPage() {
        if (current - 1 <= 1) {
            return 1;
        } else {
            return current - 1;
        }
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public boolean isBof() {
        return bof;
    }

    public boolean isEof() {
        return eof;
    }

    public boolean hasNext() {
        return current < this.totalPage;
    }

    public boolean hasPrevious() {
        return current > 1;
    }

    public boolean isFirst() {
        return current == 1;
    }

    public boolean isLast() {
        return current >= this.totalPage;
    }
    // 初始化信息

    private void init() {

        this.hasNext = hasNext();

        this.hasPrevious = hasPrevious();

        this.bof = isFirst();

        this.eof = isLast();

    }
}
