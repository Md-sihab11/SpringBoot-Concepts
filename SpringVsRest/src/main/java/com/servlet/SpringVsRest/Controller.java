package com.servlet.SpringVsRest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/take")
    POJOModel show()
    {
        return new POJOModel("Rahat", 23);
    }
}
