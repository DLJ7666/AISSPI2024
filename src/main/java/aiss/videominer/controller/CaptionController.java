package aiss.videominer.controller;

import aiss.videominer.exceptions.CaptionNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.repository.CaptionRepository;
import aiss.videominer.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/captions")
public class CaptionController {

    @Autowired
    CaptionRepository captionRepository;

    @Autowired
    VideoRepository videoRepository;

    //Obtener un subtítulo con su id
    //GET http://localhost:8080/videominer/captions/:id
    @GetMapping("/{id}")
    public Caption findOne(@PathVariable Long id) throws CaptionNotFoundException {
        Optional<Caption> caption = captionRepository.findById(id);
        if (caption.isEmpty()) {throw new CaptionNotFoundException(); }
        return caption.get();
    }

    //Obtener todos los subtítulos
    //GET http://localhost:8080/videominer/captions

    @GetMapping
    public List<Caption> findAll(){return captionRepository.findAll();}
}
