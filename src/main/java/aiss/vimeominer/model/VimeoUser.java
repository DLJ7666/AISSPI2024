package aiss.vimeominer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VimeoUser {

    @JsonProperty("name")
    private String name;

    @JsonProperty("link")
    private String userLink;

    @JsonProperty("pictures")
    private List<VimeoPicture> pictures;

    public VimeoUser(String name, String userLink, List<VimeoPicture> pictures) {
        this.name = name;
        this.userLink = userLink;
        this.pictures = pictures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserLink() {
        return userLink;
    }

    public void setUserLink(String userLink) {
        this.userLink = userLink;
    }

    public List<VimeoPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<VimeoPicture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return "VimeoUser{" +
                "name='" + name + '\'' +
                ", userLink='" + userLink + '\'' +
                ", pictures=" + pictures +
                '}';
    }
}
