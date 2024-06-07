package mz.org.csaude.mentoring.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.model.BaseModel;


public class SimpleValue extends BaseModel implements Listble, Comparable<SimpleValue> {

    private Integer id;

    private String description;

    private String code;

    private String qty;

    private String extraInfo;

    public SimpleValue(String description) {
        this.description = description;
    }

    public SimpleValue(String description, String extraInfo) {
        this.description = description;
        this.extraInfo = extraInfo;
    }

    public SimpleValue() {
    }

    public SimpleValue(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public SimpleValue(Integer id) {
        this.id = id;
    }

    public SimpleValue(Integer id, String description, String extraInfo) {
        this.id = id;
        this.description = description;
        this.extraInfo = extraInfo;
    }

    @Override
    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Integer getId() {
        return id;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static SimpleValue fastCreate(String description){
        return new SimpleValue(description);
    }

    public static SimpleValue fastCreate(Integer id, String description){
        return new SimpleValue(id, description);
    }

    public static SimpleValue fastCreate(Integer id, String description, String extraInfo){
        return new SimpleValue(id, description, extraInfo);
    }

    public static SimpleValue fastCreate(Integer id){
        return new SimpleValue(id);
    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof SimpleValue)) return false;
        SimpleValue that = (SimpleValue) o;

        if (that == null || (that.id == null && !Utilities.stringHasValue(that.description))) return false;

        if (id != null && that.id != null) return id == that.id;

        if (Utilities.stringHasValue(this.description)) return this.description.equals(that.description);

        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return description;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void setListPosition(int listPosition) {
        super.setListPosition(listPosition);
    }
    @Override
    public int getDrawable() {
        return 0;
    }


    @Override
    public int compareTo(SimpleValue other) {
        if (this.id != null && other.id != null) {
            return this.id.compareTo(other.id);
        } else if (this.description != null && other.description != null) {
            return this.description.compareTo(other.description);
        }
        return 0; // or appropriate default behavior
    }
}
