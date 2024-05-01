package aiss.videominer.controller;

import aiss.videominer.model.Comment;
import aiss.videominer.repository.CommentRepository;
import aiss.videominer.repository.UserRepository;
import aiss.videominer.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    VideoRepository  videoRepository;

    @Autowired
    UserRepository userRepository;

    //Obtener un comentario con su id
    //GET http://localhost:8080/videominer/comments/:id
    @GetMapping("/{id}")
    public Comment findOne(@PathVariable Long id){
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.get();
    }

    //Obtener todos los comentarios
    //GET http://localhost:8080/videominer/comments

    @GetMapping
    public List<Comment> findAll(){return commentRepository.findAll();}
}
