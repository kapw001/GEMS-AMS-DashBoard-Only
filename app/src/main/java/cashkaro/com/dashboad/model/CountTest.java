package cashkaro.com.dashboad.model;

/**
 * Created by yasar on 30/8/17.
 */

public class CountTest {

    private String name;

    private int count;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CountTest{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
