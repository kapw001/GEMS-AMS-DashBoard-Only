
package cashkaro.com.dashboad.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Serializable {


    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("school_list")
    @Expose
    private List<SchoolList> schoolList = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<SchoolList> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<SchoolList> schoolList) {
        this.schoolList = schoolList;
    }


    @SerializedName("todate")
    @Expose
    private String todate;
    @SerializedName("fromdate")
    @Expose
    private String fromdate;
    @SerializedName("university_id")
    @Expose
    private List<Integer> universityId = null;
    @SerializedName("visitor_list")
    @Expose
    private List<VisitorList> visitorList = null;
    private final static long serialVersionUID = 8219352986256738575L;

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public List<Integer> getUniversityId() {
        return universityId;
    }

    public void setUniversityId(List<Integer> universityId) {
        this.universityId = universityId;
    }

    public List<VisitorList> getVisitorList() {
        return visitorList;
    }

    public void setVisitorList(List<VisitorList> visitorList) {
        this.visitorList = visitorList;
    }

}
