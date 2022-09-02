package nc.apps.controllers.facadecontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.apps.dto.ErrorObject;
import nc.apps.dto.ResponseObject;
import nc.apps.propertyholder.SmartAdderPropertyHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/book/")
public class SmartAdderFacadeController {

    private final SmartAdderPropertyHolder smartAdderPropertyHolder;

    @PostMapping("/addsmart/")
    public ResponseEntity<ResponseObject> addBookWithSmartAdder(@RequestParam(required = false) String title,
                                                                @RequestParam(required = false) String author) {
        RestTemplate restTemplate = new RestTemplate();
        log.info("Smart adder request for book by title:" + title + " and author:" + author);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("title", title);
        map.add("author", author);

        String url = "http://" + smartAdderPropertyHolder.getDomain() + ":"
                + smartAdderPropertyHolder.getPort() + "/smart-adder-service/book/addsmart";
        ResponseEntity<ResponseObject> responseEntity = null;
        responseEntity = restTemplate.postForEntity(url, map, ResponseObject.class);
        return responseEntity;

    }
}
