
package cashkaro.com.dashboad.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VisitorList implements Serializable
{

    @SerializedName("data_set")
    @Expose
    private List<DataSet> dataSet = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    private final static long serialVersionUID = 2011448443631013648L;

    public List<DataSet> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<DataSet> dataSet) {
        this.dataSet = dataSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
