package videominer.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import videominer.exceptions.CaptionNotFoundException;
import videominer.exceptions.ChannelNotFoundException;
import videominer.exceptions.VideoNotFoundException;
import videominer.model.Caption;
import videominer.model.Channel;
import videominer.model.Video;
import videominer.repository.CaptionRepository;
import videominer.repository.ChannelRepository;
import videominer.repository.CommentRepository;
import videominer.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

// Uri: http://localhost:8080/api/videominer/channels/{channelId}/videos

@RestController
@RequestMapping("/api/videominer/channels")
public class VideoController {

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    VideoRepository videoRepository;


    @GetMapping("/{channelId}/videos/{videoId}")
    public Video readVideo(@PathVariable Long channelId, @PathVariable Long videoId)
            throws ChannelNotFoundException, VideoNotFoundException {
        Video res = null;
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            Optional<Video> video = videoRepository.findById(videoId);
            if (video.isEmpty()) {throw new VideoNotFoundException(); }
                res = video.get();
        } else { throw new ChannelNotFoundException(); }
        return res;
    }

    @GetMapping("/{channelId}/videos")
    public List<Video> readVideos(@PathVariable Long channelId)
            throws ChannelNotFoundException {
        List<Video> res = null;
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            res = channel.get().getVideos();
        } else { throw new ChannelNotFoundException(); }
        return res;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{channelId}/videos")
    public Video createVideo(@PathVariable Long channelId,
                                 @Valid @RequestBody Video video) throws ChannelNotFoundException {
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            channel.get().addVideo(video);
            return videoRepository.save(video);
        } else {throw new ChannelNotFoundException();}

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{channelId}/videos/{videoId}")
    public void deleteVideo(@PathVariable Long channelId, @PathVariable Long videoId)
            throws ChannelNotFoundException, VideoNotFoundException{
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            if (videoRepository.existsById(videoId)){
                videoRepository.deleteById(videoId);
            } else { throw new VideoNotFoundException(); }
        } else { throw new ChannelNotFoundException(); }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{channelId}/videos/{videoId}")
    public void updateVideo(@PathVariable Long channelId, @PathVariable Long videoId,
                              @Valid @RequestBody Video updatedVideo)
            throws ChannelNotFoundException, VideoNotFoundException, CaptionNotFoundException {
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            Optional<Video> toUpdateVideo = videoRepository.findById(videoId);
            if (toUpdateVideo.isPresent()){
                Video updatingVideo = toUpdateVideo.get();
                updatingVideo.setName(updatedVideo.getName());
                updatingVideo.setDescription(updatedVideo.getDescription());
                updatingVideo.setReleaseTime(updatedVideo.getReleaseTime());
                updatingVideo.setComments(updatedVideo.getComments());
                updatingVideo.setCaptions(updatedVideo.getCaptions());

            } else { throw new VideoNotFoundException(); }
        } else { throw new ChannelNotFoundException(); }
    }

}

