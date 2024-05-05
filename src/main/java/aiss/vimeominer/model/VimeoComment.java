package aiss.vimeominer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VimeoComment {

    @JsonProperty("text")
    private String text;

    @JsonProperty("created_on")
    private String createdOn;

    @JsonProperty("user")
    private VimeoUser user;

    public VimeoComment(String text, String createdOn, VimeoUser user) {
        this.text = text;
        this.createdOn = createdOn;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public VimeoUser getUser() {
        return user;
    }

    public void setUser(VimeoUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "VimeoComment{" +
                "text='" + text + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", user=" + user +
                '}';
    }
}
