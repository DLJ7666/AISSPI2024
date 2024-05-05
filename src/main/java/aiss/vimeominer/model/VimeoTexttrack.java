package aiss.vimeominer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VimeoTexttrack {

    @JsonProperty("name")
    private String name;

    @JsonProperty("language")
    private String language;

    public VimeoTexttrack(String name, String language) {
        this.name = name;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "VimeoTexttrack{" +
                "name='" + name + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
