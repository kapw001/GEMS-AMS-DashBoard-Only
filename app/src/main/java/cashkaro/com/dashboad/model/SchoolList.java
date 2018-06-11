package cashkaro.com.dashboad.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yasar on 23/8/17.
 */

public class SchoolList implements Serializable {
    public SchoolList(String lastName, Integer id, String name,String color) {
        this.color = color;
        this.lastName = lastName;
        this.id = id;
        this.name = name;
    }

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    private final static long serialVersionUID = -7260850520730275386L;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}