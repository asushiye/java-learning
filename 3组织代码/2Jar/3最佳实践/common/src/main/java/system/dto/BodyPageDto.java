package system.dto;

/**
 * @author : zhenyun.su
 * @since : 2019/1/18
 * @Commit: 统一请求body和分页Page
 */

public class BodyPageDto<T,K> extends BodyDto<T> {
    private K page;

    public K getPage() {
        return page;
    }

    public void setPage(K page) {
        this.page = page;
    }
}
