package system.page;

/**
 * @author : zhenyun.su
 * @since : 2019/1/8
 * @Commit: 返回分页数据
 *  content:  分页内容
 *  paged:    分页标识
 *  sorted:   排序标识
 *  first:    第一页标识
 *  last:     最后一页标识
 *  realPage:   实际页码
 *  realSize:   实际当前页记录数
 *  totalPage:  实际总页数
 *  totalSize:  实际总记录数
 */

public class ResponsePageEx<T> extends ResponsePage<T>{
    private boolean paged;
    private boolean sorted;
    private boolean first;
    private boolean last;
    private int realPage;
    private int totalPage;

    public boolean isPaged() {
        return paged;
    }

    public void setPaged(boolean paged) {
        this.paged = paged;
    }

    public boolean isSorted() {
        return sorted;
    }

    public void setSorted(boolean sorted) {
        this.sorted = sorted;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getRealPage() {
        return realPage;
    }

    public void setRealPage(int realPage) {
        this.realPage = realPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
