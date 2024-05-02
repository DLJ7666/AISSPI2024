package aiss.videominer.controller;


import aiss.videominer.model.Channel;
import aiss.videominer.repository.ChannelRepository;
import aiss.videominer.repository.VideoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/controllers")
public class ChannelController {

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    VideoRepository videoRepository;

    //Crear un canal
    //POST http://localhost:8080/videominer/channels

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Channel create(@Valid @RequestBody Channel channel){
        return channelRepository.save(new Channel(channel.getName(), channel.getDescription(),
                channel.getCreatedTime()));
    }

    //Obtener un canal con su id
    //GET http://localhost:8080/videominer/channels/:id
    @GetMapping("/{id}")
    public Channel findOne(@PathVariable Long id){
        Optional<Channel> channel = channelRepository.findById(id);
        return channel.get();
    }

    //Obtener todos los canales
    //GET http://localhost:8080/videominer/channels

    @GetMapping
    public List<Channel> findAll(){return channelRepository.findAll();}
}
