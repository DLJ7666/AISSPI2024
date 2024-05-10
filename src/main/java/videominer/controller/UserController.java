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
import videominer.repository.ChannelRepository;
import videominer.repository.CommentRepository;
import videominer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import videominer.repository.VideoRepository;

import java.util.List;
import java.util.Optional;

/// Uri: http://localhost:8080/api/videominer/channels/{channelId}/videos/{videoId}/comments/{commentId}/users
@RestController
@RequestMapping("/api/videominer/channels")
public class UserController {

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{channelId}/videos/{videoId}/comments/{commentId}/users/{userId}")
    public User readUser(@PathVariable Long channelId, @PathVariable Long videoId, @PathVariable Long commentId,
                            @PathVariable Long userId) throws CommentNotFoundException, ChannelNotFoundException,
                                VideoNotFoundException, UserNotFoundException {
        User res = null;
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            Optional<Video> video = videoRepository.findById(videoId);
            if (video.isPresent()) {
                Optional<Comment> comment = commentRepository.findById(commentId);
                if(comment.isPresent()){
                    Optional<User> user = userRepository.findById(userId);
                    if (user.isEmpty()) {throw new UserNotFoundException(); }
                    res = user.get();
                } else { throw new CommentNotFoundException(); }
            } else { throw new VideoNotFoundException(); }
        } else { throw new ChannelNotFoundException(); }
        return res;
    }

    //Como solo hay un usuario por comentario, no tiene sentido hacer una lista de usuarios que escribieron el comentario con id commentid

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{channelId}/videos/{videoId}/comments/{commentId}/users/")
    public User createUser(@PathVariable Long channelId, @PathVariable Long videoId, @PathVariable Long commentId,
                                 @Valid @RequestBody User user) throws ChannelNotFoundException,
                                    VideoNotFoundException, CommentNotFoundException {
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            Optional<Video> video = videoRepository.findById(videoId);
            if (video.isPresent()) {
                Optional<Comment> comment = commentRepository.findById(commentId);
                if (comment.isPresent()) {
                return userRepository.save(user);
                } else { throw new CommentNotFoundException(); }
            } else { throw new VideoNotFoundException(); }
        } else { throw new ChannelNotFoundException(); }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{channelId}/videos/{videoId}/comments/{commentId}/users/{userId}")
    public void deleteUser(@PathVariable Long channelId, @PathVariable Long videoId, @PathVariable Long commentId,@PathVariable Long userId)
            throws ChannelNotFoundException, VideoNotFoundException, CommentNotFoundException,UserNotFoundException {
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            Optional<Video> video = videoRepository.findById(videoId);
            if (video.isPresent()) {
                Optional<Comment> comment = commentRepository.findById(commentId);
                if (comment.isPresent()) {
                    if (userRepository.existsById(userId)){
                        userRepository.deleteById(userId);
                    } else {throw new UserNotFoundException(); }
                } else { throw new CommentNotFoundException(); }
            } else { throw new VideoNotFoundException(); }
        } else { throw new ChannelNotFoundException(); }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{channelId}/videos/{videoId}/captions/{commentId}/users/{userId}")
    public void updateUser(@PathVariable Long channelId, @PathVariable Long videoId, @PathVariable Long commentId,@PathVariable Long userId,
                              @Valid @RequestBody User updatedUser)
            throws ChannelNotFoundException, VideoNotFoundException, CommentNotFoundException, UserNotFoundException {
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            Optional<Video> video = videoRepository.findById(videoId);
            if (video.isPresent()) {
                Optional<Comment> comment =commentRepository.findById(commentId);
                if (comment.isPresent()){
                    Optional<User> toUpdateUser = userRepository.findById(userId);
                    if (toUpdateUser.isPresent()){
                    User updatingUser = toUpdateUser.get();
                    updatingUser.setName(updatedUser.getName());
                    updatingUser.setUser_link(updatedUser.getUser_link());
                    updatingUser.setPicture_link(updatedUser.getPicture_link());
                    } else {throw new UserNotFoundException(); }
                } else {throw new CommentNotFoundException(); }
            } else { throw new VideoNotFoundException(); }
        } else { throw new ChannelNotFoundException(); }
    }

}

