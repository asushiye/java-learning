package headfirst.proxy.jdk;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/30
 */

public interface PersonBean {
    String getName();
    String getGender();
    Integer getHotOrNotRating();

    void setName(String name);
    void setGender(String gender);
    void setHotOrNotRating(Integer rating);
}
