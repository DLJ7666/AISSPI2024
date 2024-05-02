package aiss.videominer.controller;

import aiss.videominer.exceptions.VideoNotFoundException;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CaptionRepository;
import aiss.videominer.repository.ChannelRepository;
import aiss.videominer.repository.CommentRepository;
import aiss.videominer.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/videos")
public class  VideoController {

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    CaptionRepository captionRepository;

    @Autowired
    CommentRepository commentRepository;

    //Obtener un vídeo con su id
    //GET http://localhost:8080/videominer/videos/:id
    @GetMapping("/{id}")
    public Video findOne(@PathVariable Long id) throws VideoNotFoundException {
        Optional<Video> video = videoRepository.findById(id);
        if(video.isEmpty()) {throw new VideoNotFoundException();}
        return video.get();
    }

    //Obtener todos los vídeos
    //GET http://localhost:8080/videominer/videos

    @GetMapping
    public List<Video> findAll(){return videoRepository.findAll();}

}
