package cashkaro.com.dashboad.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yasar on 28/8/17.
 */

public class PassValuesTwoActivity implements Serializable {

    private String startDate, enddate, schoolselectedcount;

    public String getSchoolselectedcount() {
        return schoolselectedcount;
    }

    public void setSchoolselectedcount(String schoolselectedcount) {
        this.schoolselectedcount = schoolselectedcount;
    }

    private int monthorday;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public int getMonthorday() {
        return monthorday;
    }

    public void setMonthorday(int monthorday) {
        this.monthorday = monthorday;
    }

    public List<String> getSchoolListList() {
        return schoolListList;
    }

    public void setSchoolListList(List<String> schoolListList) {
        this.schoolListList = schoolListList;
    }

    private List<String> schoolListList;

    public List<SchoolList> getListofSchools() {
        return listofSchools;
    }

    public void setListofSchools(List<SchoolList> listofSchools) {
        this.listofSchools = listofSchools;
    }

    private List<SchoolList> listofSchools;

    public List<SchoolList> getSchoolSelectedList() {
        return schoolSelectedList;
    }

    public void setSchoolSelectedList(List<SchoolList> schoolSelectedList) {
        this.schoolSelectedList = schoolSelectedList;
    }

    private List<SchoolList> schoolSelectedList;



}
