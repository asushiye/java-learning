import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Date;

@JsonIgnoreProperties({"field1", "field2"})
public class Transaction {
    private String type = null;
    private Date date = null;

    @JsonIgnore
    private String title= null;

    private String field1= null;
    private String field2= null;

    public String getField3() {
        return field3;
    }

    @JsonSetter("id")
    public void setField3(String field3) {
        this.field3 = field3;
    }

    private String field3= null;

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Transaction(String type, Date date) {
        this.type = type;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
