package system.page;

import java.io.Serializable;

/**
 * @author : zhenyun.su
 * @since : 2019/1/7
 * @Commit: 请求分页,可单独使用，也可以被DTO继承使用
 *  paged:    分页标识
 *  pageNum:  分页索引
 *  pageSize: 每页大小
 */

public class RequestPage implements Serializable {
    protected Integer page;
    protected Integer size;
    protected boolean paged;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public boolean isPaged() {
        return paged;
    }

    public void setPaged(boolean paged) {
        this.paged = paged;
    }
}
