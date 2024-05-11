package videominer.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import videominer.exceptions.ChannelNotFoundException;
import videominer.exceptions.VideoNotFoundException;
import videominer.model.Video;
import videominer.repository.VideoRepository;

import java.util.List;
import java.util.Optional;

// Uri: http://localhost:42000/api/videominer/videos

@RestController
@RequestMapping("/api/videominer/videos")
public class AllVideoController {

    @Autowired
    VideoRepository videoRepository;


    @GetMapping("{videoId}")
    public Video readVideo(@PathVariable String videoId) throws VideoNotFoundException {
        Video res = null;
        Optional<Video> video = videoRepository.findById(videoId);
        if (video.isEmpty()) {throw new VideoNotFoundException(); }
        res = video.get();
        return res;
    }

    @GetMapping
    public List<Video> readVideos() { return videoRepository.findAll(); }
}

