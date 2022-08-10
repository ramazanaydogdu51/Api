package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

//dont forget
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoRestPojo {
    //Create variables key
    private String meta;
    private GoRestDataPojo data;

    //Create with cons and without cons

    public GoRestPojo(String meta, GoRestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    public GoRestPojo() {
    }

    //Create getters and setters

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public GoRestDataPojo getData() {
        return data;
    }

    public void setData(GoRestDataPojo data) {
        this.data = data;
    }

    // Create toString() method


    @Override
    public String toString() {
        return "GoRestPojo{" +
                "meta='" + meta + '\'' +
                ", data=" + data +
                '}';
    }
}
