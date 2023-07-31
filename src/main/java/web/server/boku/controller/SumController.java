package web.server.boku.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import web.server.boku.dto.InputDto;
import web.server.boku.service.SumService;

@RestController
public class SumController {

    private final SumService sumService;

    public SumController(SumService sumService) {
        this.sumService = sumService;
    }

    @PostMapping("/")
    public String handleRequest(@RequestBody InputDto inputdto) {
        return sumService.handleRequest(inputdto);
    }

}
