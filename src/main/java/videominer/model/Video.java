package videominer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    @Column(name="name")
    @NotEmpty(message = "Video name cannot be empty")
    private String name;

    @JsonProperty("description")
    @Column(columnDefinition="TEXT")
    private String description;

    @JsonProperty("releaseTime")
    @Column(name="releaseTime")
    @NotEmpty(message = "Video release time cannot be empty")
    private String releaseTime;

    @JsonProperty("comments")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "videoId")
    @NotNull(message = "Video comments cannot be null")
    private List<Comment> comments;

    @JsonProperty("captions")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "videoId")
    @NotNull(message = "Video captions cannot be null")
    private List<Caption> captions;

    public Video(Long id, String name, String description, String releaseTime) {
        this.name = name;
        this.description = description;
        this.releaseTime = releaseTime;
        this.comments = new ArrayList<>();
        this.captions = new ArrayList<>();
    }

    public Video() {
        this.comments = new ArrayList<>();
        this.captions = new ArrayList<>();
    }

    public Video(Long id, String name, String description, String releaseTime, List<Comment> comments,
                 List<Caption> captions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseTime = releaseTime;
        this.comments = comments;
        this.captions = captions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {this.comments.add(comment);}

    public List<Caption> getCaptions() {
        return captions;
    }

    public void setCaptions(List<Caption> captions) {
        this.captions = captions;
    }

    public void addCaption(Caption caption) {this.captions.add(caption);}

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", comments=" + comments +
                ", captions=" + captions +
                '}';
    }
}
