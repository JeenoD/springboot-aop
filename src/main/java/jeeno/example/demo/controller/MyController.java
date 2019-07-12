package jeeno.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Jeeno
 * @version: 0.0.1
 * @since: 2019/7/12 15:21
 */
@RestController
@RequestMapping("")
public class MyController {

    @GetMapping("/hi")
    public String hi(){
        return "hello world";
    }

    @GetMapping("/hi/{name}")
    public String hello(@PathVariable("name") String name){
        return "hello, " + name;
    }

    @GetMapping("/err")
    public String exception() throws Exception{
        throw new Exception("ERROR");
    }

}
