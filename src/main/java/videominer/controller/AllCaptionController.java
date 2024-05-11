package videominer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import videominer.exceptions.CaptionNotFoundException;
import videominer.model.Caption;
import videominer.repository.CaptionRepository;

import java.util.List;
import java.util.Optional;

// Uri: http://localhost:42000/api/videominer/captions

@RestController
@RequestMapping("/api/videominer/captions")
public class AllCaptionController {

    @Autowired
    CaptionRepository captionRepository;


    @GetMapping("{captionId}")
    public Caption readCaption(@PathVariable String captionId) throws CaptionNotFoundException {
        Caption res = null;
        Optional<Caption> video = captionRepository.findById(captionId);
        if (video.isEmpty()) {throw new CaptionNotFoundException(); }
        res = video.get();
        return res;
    }

    @GetMapping
    public List<Caption> readCaptions() { return captionRepository.findAll(); }
}

