package com.webserviceocorrencia.ws.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jeanderson Almeida   <jeandymalmeida@gmail.com>
 */
@RestController
public class IndexController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "Endpoint inicial";
    }
    
}
