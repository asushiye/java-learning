package system.dto;

import java.io.Serializable;

/**
 * @author : zhenyun.su
 * @since : 2019/1/18
 * @Commit: 统一请求body, 可单独使用，也可以被BodyPageDto和ParamBodyPageDto继承使用
 * 单独使用有人想为什么？，直接使用T作为Body。
 * 推荐使用，保持body统一格式，有利统一验证，转换数据及日志记录
 */

public class BodyDto<T> implements Serializable {
    private T body;

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

}
