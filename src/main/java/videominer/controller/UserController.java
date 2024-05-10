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

/// Uri: http://localhost:8080/api/videominer/users
@RestController
@RequestMapping("/api/videominer/users")
public class UserController {

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{userId}")
    public User readUser(@PathVariable Long userId) throws UserNotFoundException {
        User res = null;
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {throw new UserNotFoundException(); }
        res = user.get();
        return res;
    }

    @GetMapping
    public List<User> readUsers() {
        return userRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        if (userRepository.existsById(userId)) { userRepository.deleteById(userId); }
        else {throw new UserNotFoundException(); }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{userId}")
    public void updateUser(@PathVariable Long userId, @Valid @RequestBody User updatedUser) throws UserNotFoundException {
        Optional<User> toUpdateUser = userRepository.findById(userId);
        if (toUpdateUser.isPresent()) {
            User updatingUser = toUpdateUser.get();
            updatingUser.setName(updatedUser.getName());
            updatingUser.setUser_link(updatedUser.getUser_link());
            updatingUser.setPicture_link(updatedUser.getPicture_link());
        } else { throw new UserNotFoundException(); }
    }
}

