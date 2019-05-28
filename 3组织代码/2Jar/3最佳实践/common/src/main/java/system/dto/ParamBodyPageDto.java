package system.dto;

/**
 * @author : zhenyun.su
 * @since : 2019/1/18
 * @Commit: 统一请求参数，请求body，分页Page
 * 适用于将请求参数，作为Http的body来传输，若将使用http参数，作为请求参数，推荐使用BodyPageDto
 */

public class ParamBodyPageDto<J,T,K> extends BodyPageDto<T,K> {
    private J param;

    public J getParam() {
        return param;
    }

    public void setParam(J param) {
        this.param = param;
    }
}
