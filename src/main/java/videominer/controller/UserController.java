package videominer.controller;

import videominer.repository.CommentRepository;
import videominer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Uri: http://localhost:8080/api/videominer/users
@RestController
@RequestMapping("/api/videominer/users")
public class  UserController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;
}
