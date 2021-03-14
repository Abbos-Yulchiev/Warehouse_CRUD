package uz.pdp.appjpawarehouse.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpawarehouse.entity.User;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.payload.UserDTO;
import uz.pdp.appjpawarehouse.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/upload")
    public Result addUser(@RequestBody UserDTO userDTO) {

        Result result = userService.addUser(userDTO);
        return result;
    }

    @GetMapping(value = "/get")
    public Page<User> getUserPage(@RequestParam Integer page) {

        Page<User> userPage = userService.getUserPage(page);
        return userPage;
    }

    @PutMapping(value = "/edit/{userId}")
    public Result editUser(@PathVariable Integer userId, @RequestBody UserDTO userDTO) {

        Result result = userService.editUser(userId, userDTO);
        return result;
    }

    @DeleteMapping(value = "/delete/{userId}")
    public Result deleteUser(@PathVariable Integer userId){

        Result result = userService.deleteUser(userId);
        return result;
    }
}
