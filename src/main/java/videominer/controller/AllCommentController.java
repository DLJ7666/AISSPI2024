package videominer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import videominer.exceptions.CommentNotFoundException;
import videominer.model.Comment;
import videominer.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

// Uri: http://localhost:42000/api/videominer/comments

@RestController
@RequestMapping("/api/videominer/comments")
public class AllCommentController {

    @Autowired
    CommentRepository commentRepository;


    @GetMapping("{commentId}")
    public Comment readComment(@PathVariable String commentId) throws CommentNotFoundException {
        Comment res = null;
        Optional<Comment> video = commentRepository.findById(commentId);
        if (video.isEmpty()) {throw new CommentNotFoundException(); }
        res = video.get();
        return res;
    }

    @GetMapping
    public List<Comment> readComments() { return commentRepository.findAll(); }
}

