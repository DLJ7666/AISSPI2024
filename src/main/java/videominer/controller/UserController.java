package videominer.controller;

import videominer.repository.CommentRepository;
import videominer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/videominer/users")
public class  UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;
}
