package aiss.vimeominer.service;

import aiss.vimeominer.model.VimeoChannel;
import aiss.vimeominer.model.VimeoVideo;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpHeaders;

@Service
public class VimeoChannelService {

    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "87879038305545edc0a789f4d4733f6b";

    public VimeoChannel getVimeoChannel(String id) {
        VimeoChannel res = null;
        String uri = String.format("https://api.vimeo.com/channels/%s", id);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<VimeoChannel> request = new HttpEntity<>(null, headers);

        ResponseEntity<VimeoChannel> response = restTemplate.exchange(uri, HttpMethod.GET, request, VimeoChannel.class);

        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }
}
