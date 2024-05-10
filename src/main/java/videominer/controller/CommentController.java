package videominer.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import videominer.exceptions.ChannelNotFoundException;
import videominer.exceptions.CommentNotFoundException;
import videominer.exceptions.UserNotFoundException;
import videominer.exceptions.VideoNotFoundException;
import videominer.model.Channel;
import videominer.model.Comment;
import videominer.model.User;
import videominer.model.Video;
import videominer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

// Uri: http://localhost:8080/api/videominer/channels/{channelId}/videos/{videoId}/comments
@RestController
@RequestMapping("/api/videominer/channels")
public class CommentController {

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{channelId}/videos/{videoId}/comments/{commentId}")
    public Comment readComment(@PathVariable Long channelId, @PathVariable Long videoId, @PathVariable Long commentId)
            throws CommentNotFoundException, ChannelNotFoundException, VideoNotFoundException {
        Comment res = null;
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            Optional<Video> video = videoRepository.findById(videoId);
            if (video.isPresent()) {
                Optional<Comment> comment = commentRepository.findById(commentId);
                if (comment.isEmpty()) {throw new CommentNotFoundException(); }
                res = comment.get();
            } else { throw new VideoNotFoundException(); }
        } else { throw new ChannelNotFoundException(); }
        return res;
    }

    @GetMapping("/{channelId}/videos/{videoId}/comments")
    public List<Comment> readComments(@PathVariable Long channelId, @PathVariable Long videoId)
            throws ChannelNotFoundException, VideoNotFoundException {
        List<Comment> res = null;
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            Optional<Video> video = videoRepository.findById(videoId);
            if (video.isPresent()) {
                res = video.get().getComments();
            } else { throw new VideoNotFoundException(); }
        } else { throw new ChannelNotFoundException(); }
        return res;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{channelId}/videos/{videoId}/comments")
    public Comment createComment(@PathVariable Long channelId, @PathVariable Long videoId,
                                 @Valid @RequestBody Comment comment, @RequestParam("authorId") String userId)
            throws ChannelNotFoundException, VideoNotFoundException, UserNotFoundException {
        Optional<User> user = userRepository.findById(Long.valueOf(userId));
        if(user.isEmpty()) {
            throw new UserNotFoundException();
        } else {
            comment.setAuthor(user.get());
        }
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            Optional<Video> video = videoRepository.findById(videoId);
            if (video.isPresent()) {
                video.get().addComment(comment);
                return commentRepository.save(comment);
            } else { throw new VideoNotFoundException(); }
        } else { throw new ChannelNotFoundException(); }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{channelId}/videos/{videoId}/comments/{commentId}")
    public void deleteComment(@PathVariable Long channelId, @PathVariable Long videoId, @PathVariable Long commentId)
            throws ChannelNotFoundException, VideoNotFoundException, CommentNotFoundException {
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            Optional<Video> video = videoRepository.findById(videoId);
            if (video.isPresent()) {
                if (commentRepository.existsById(commentId)){
                    commentRepository.deleteById(commentId);
                } else {throw new CommentNotFoundException(); }
            } else { throw new VideoNotFoundException(); }
        } else { throw new ChannelNotFoundException(); }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{channelId}/videos/{videoId}/comments/{commentId}")
    public void updateComment(@PathVariable Long channelId, @PathVariable Long videoId, @PathVariable Long commentId,
                              @Valid @RequestBody Comment updatedComment)
            throws ChannelNotFoundException, VideoNotFoundException, CommentNotFoundException {
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            Optional<Video> video = videoRepository.findById(videoId);
            if (video.isPresent()) {
                Optional<Comment> toUpdateComment =commentRepository.findById(commentId);
                if (toUpdateComment.isPresent()){
                    Comment updatingComment = toUpdateComment.get();
                    updatingComment.setText(updatedComment.getText());
                    updatingComment.setCreatedOn(updatedComment.getCreatedOn());
                    updatingComment.setAuthor(updatedComment.getAuthor());
                } else {throw new CommentNotFoundException(); }
            } else { throw new VideoNotFoundException(); }
        } else { throw new ChannelNotFoundException(); }
    }

}
