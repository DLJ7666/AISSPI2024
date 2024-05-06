package videominer.controller;

import videominer.exceptions.VideoNotFoundException;
import videominer.model.Video;
import videominer.repository.CaptionRepository;
import videominer.repository.ChannelRepository;
import videominer.repository.CommentRepository;
import videominer.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

// Uri: http://localhost:8080/api/videominer/channels/{channelId}/videos
@RestController
@RequestMapping("/api/videominer/videos")
public class  VideoController {

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    VideoRepository videoRepository;

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
