package system.page;

import java.io.Serializable;

/**
 * @author : zhenyun.su
 * @since : 2019/1/8
 * @Commit: 返回分页数据，可单独使用，也被更多分页类继承使用
 *  content:    分页内容
 *  realSize:   页实际大小
 *  totalSize:  实际总记录数
 */

public class ResponsePage<T> implements Serializable {
    private T content;
    private int realSize;
    private long totalSize;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public int getRealSize() {
        return realSize;
    }

    public void setRealSize(int realSize) {
        this.realSize = realSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }
}
