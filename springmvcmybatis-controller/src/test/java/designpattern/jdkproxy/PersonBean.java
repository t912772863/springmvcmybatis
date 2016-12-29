package designpattern.jdkproxy;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
public interface PersonBean {
    String getName();
    String getGender();
    String getInterests();
    int getHotOrNotRating();

    void setName(String name);
    void setGender(String gender);
    void setInterests(String interests);
    void setHotOrRating(int rating);

}
