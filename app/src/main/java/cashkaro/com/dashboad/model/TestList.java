package cashkaro.com.dashboad.model;

import java.util.List;
import java.util.Map;

/**
 * Created by yasar on 30/8/17.
 */

public class TestList {

    private int id;

    private Map<Integer, List<CountTest>> integerListMap;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, List<CountTest>> getIntegerListMap() {
        return integerListMap;
    }

    public void setIntegerListMap(Map<Integer, List<CountTest>> integerListMap) {
        this.integerListMap = integerListMap;
    }
}
