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
import videominer.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

// Uri: http://localhost:8080/api/videominer/channels/{channelId}/videos/{videoId}/captions
@RestController
@RequestMapping("/api/videominer/channels")
public class CaptionController {

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    CaptionRepository captionRepository;

    @GetMapping("/{channelId}/videos/{videoId}/captions/{captionId}")
    public Caption readCaption(@PathVariable Long channelId, @PathVariable Long videoId, @PathVariable Long captionId)
            throws CaptionNotFoundException, ChannelNotFoundException, VideoNotFoundException {
        Caption res = null;
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            Optional<Video> video = videoRepository.findById(videoId);
            if (video.isPresent()) {
                Optional<Caption> caption = captionRepository.findById(captionId);
                if (caption.isEmpty()) {throw new CaptionNotFoundException(); }
                res = caption.get();
            } else { throw new VideoNotFoundException(); }
        } else { throw new ChannelNotFoundException(); }
        return res;
    }

    @GetMapping("/{channelId}/videos/{videoId}/captions")
    public List<Caption> readCaptions(@PathVariable Long channelId, @PathVariable Long videoId)
            throws ChannelNotFoundException, VideoNotFoundException {
        List<Caption> res = null;
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            Optional<Video> video = videoRepository.findById(videoId);
            if (video.isPresent()) {
                res = video.get().getCaptions();
            } else { throw new VideoNotFoundException(); }
        } else { throw new ChannelNotFoundException(); }
        return res;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{channelId}/videos/{videoId}/captions")
    public Caption createCaption(@PathVariable Long channelId, @PathVariable Long videoId,
                               @Valid @RequestBody Caption caption) throws ChannelNotFoundException,
            VideoNotFoundException {
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            Optional<Video> video = videoRepository.findById(videoId);
            if (video.isPresent()) {
                video.get().addCaption(caption);
                return captionRepository.save(caption);
            } else { throw new VideoNotFoundException(); }
        } else { throw new ChannelNotFoundException(); }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{channelId}/videos/{videoId}/captions/{captionId}")
    public void deleteCaption(@PathVariable Long channelId, @PathVariable Long videoId, @PathVariable Long captionId)
            throws ChannelNotFoundException, VideoNotFoundException, CaptionNotFoundException {
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            Optional<Video> video = videoRepository.findById(videoId);
            if (video.isPresent()) {
                if (captionRepository.existsById(captionId)){
                    captionRepository.deleteById(captionId);
                } else {throw new CaptionNotFoundException(); }
            } else { throw new VideoNotFoundException(); }
        } else { throw new ChannelNotFoundException(); }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{channelId}/videos/{videoId}/captions/{captionId}")
    public void updateCaption(@PathVariable Long channelId, @PathVariable Long videoId, @PathVariable Long captionId,
                              @Valid @RequestBody Caption updatedCaption)
            throws ChannelNotFoundException, VideoNotFoundException, CaptionNotFoundException {
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            Optional<Video> video = videoRepository.findById(videoId);
            if (video.isPresent()) {
                Optional<Caption> toUpdateCaption =captionRepository.findById(captionId);
                if (toUpdateCaption.isPresent()){
                    Caption updatingCaption = toUpdateCaption.get();
                    updatingCaption.setName(updatedCaption.getName());
                    updatingCaption.setLanguage(updatedCaption.getLanguage());
                } else {throw new CaptionNotFoundException(); }
            } else { throw new VideoNotFoundException(); }
        } else { throw new ChannelNotFoundException(); }
    }

}
