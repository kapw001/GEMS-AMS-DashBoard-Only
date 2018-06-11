package cashkaro.com.dashboad.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yasar on 29/8/17.
 */

public class SchoolResponse implements Serializable {

    @SerializedName("jsonrpc")
    @Expose
    private String jsonrpc;
    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("result")
    @Expose
    private Result result;
    private final static long serialVersionUID = 3749195771593704479L;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}