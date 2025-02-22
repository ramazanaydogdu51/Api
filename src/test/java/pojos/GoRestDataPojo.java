package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

//dont forget ignoreUnknown = true
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoRestDataPojo {
    // Create Variables every key
    private String name;
    private String email;
    private String gender;
    private String status;


    // Create Constructor with all and without


    public GoRestDataPojo(String name, String email, String gender, String status) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }

    public GoRestDataPojo() {
    }

    //Create getters and setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

        // Create toString();


    @Override
    public String toString() {
        return "GoRestDataPojo{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}


