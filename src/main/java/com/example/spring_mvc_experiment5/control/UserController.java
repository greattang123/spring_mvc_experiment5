package com.example.spring_mvc_experiment5.control;

import com.example.spring_mvc_experiment5.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    private static List<User> users=create();
         //asList()产生的容器类型不可变！！！
            /*= Arrays.<User>asList(
            new User(1, "GT", "1234", "101"),
            new User(2, "Sun", "5678", "101")
    );*/
    private static List<User> create(){
        List<User> users =new ArrayList<>();
        User user1=new User(1, "GT", "1234", "101");
        User user =new User(2, "Sun", "5678", "101");
        users.add(user1);
        users.add(user);
        return users;
    }

    @GetMapping("/users")
    public Map getUsers() {
        return Map.of("users", users);
    }

    @PostMapping("/users")
    public Map postUser(@RequestBody User user) {
        users.add(user);
        return Map.of("users", user);
    }

    @GetMapping("/users/{uid}")
    public Map getUser(@PathVariable int uid) {
        log.debug("{}", uid);
        User user = users.stream().filter(u ->
                u.getId() == uid).findFirst().orElse(null);
        return Optional.ofNullable(user).map(u -> Map.of("user", u)).orElse(Map.of());
    }

    public void display(){}
}
