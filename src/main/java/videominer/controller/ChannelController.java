package videominer.controller;

import videominer.exceptions.ChannelNotFoundException;
import videominer.model.Channel;
import videominer.repository.ChannelRepository;
import videominer.repository.VideoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Uri: http://localhost:8080/api/videominer/channels
@RestController
@RequestMapping("/api/videominer/channels")
public class ChannelController {

    @Autowired
    ChannelRepository channelRepository;

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
    public Channel findOne(@PathVariable Long id) throws ChannelNotFoundException {
        Optional<Channel> channel = channelRepository.findById(id);
        if (channel.isEmpty()) {throw new ChannelNotFoundException();}
        return channel.get();
    }

    //Obtener todos los canales
    //GET http://localhost:8080/videominer/channels

    @GetMapping
    public List<Channel> findAll(){return channelRepository.findAll();}
}
