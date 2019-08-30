package headfirst.proxy.jdk;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/30
 */

public class PersonBeanImpl implements PersonBean {
    String name;
    String gender;
    Integer rating=0;
    Integer ratingCount=0;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public Integer getHotOrNotRating() {
        if (ratingCount ==0){return 0;}
        return rating/ratingCount;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void setHotOrNotRating(Integer rating) {
        this.rating+= rating;
        this.ratingCount++;
    }

}
