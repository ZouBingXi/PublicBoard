package cn.szuer.publicboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/yes")
public class UserController {
    
    @GetMapping
    public String getAll(){

        return "springboot yes!" ;
    }
}
