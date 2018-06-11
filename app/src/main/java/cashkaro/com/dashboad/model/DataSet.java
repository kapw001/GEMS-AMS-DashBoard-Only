
package cashkaro.com.dashboad.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataSet implements Serializable
{

    @SerializedName("visitor_type")
    @Expose
    private String visitorType;
    @SerializedName("check_in")
    @Expose
    private String checkIn;
    @SerializedName("check_out")
    @Expose
    private Object checkOut;
    private final static long serialVersionUID = -412943511594300567L;

    public String getVisitorType() {
        return visitorType;
    }

    public void setVisitorType(String visitorType) {
        this.visitorType = visitorType;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public Object getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Object checkOut) {
        this.checkOut = checkOut;
    }

}
