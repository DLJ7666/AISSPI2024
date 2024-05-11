package videominer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("text")
    @Column(columnDefinition="TEXT")
    private String text;

    @Column(name = "createdOn")
    @JsonProperty("createdOn")
    private String createdOn;

    @JsonProperty("author")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "userId")
    @NotNull(message = "Comment author cannot be null")
    private User author;


    public Comment(String text, String createdOn, User author) {
        this.text = text;
        this.createdOn = createdOn;
        this.author = author;
    }

    public Comment() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", author=" + author +
                '}';
    }
}
