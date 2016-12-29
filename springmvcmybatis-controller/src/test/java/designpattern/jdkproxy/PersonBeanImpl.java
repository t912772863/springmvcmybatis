package designpattern.jdkproxy;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
public class PersonBeanImpl implements PersonBean {
    String name;
    String gender;
    String interests;
    int rating;
    int ratingCount = 0;

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getInterests() {
        return interests;
    }

    public int getHotOrNotRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public void setHotOrRating(int rating) {
        this.rating = rating;
        ratingCount++;
    }

    @Override
    public String toString() {
        return "PersonBeanImpl{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", interests='" + interests + '\'' +
                ", rating=" + rating +
                ", ratingCount=" + ratingCount +
                '}';
    }
}
