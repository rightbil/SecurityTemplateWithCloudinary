package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    //TODO: Home
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "index";
    }

    //TODO: Register
    @GetMapping("/register")
    public String registerUsers(Model model) {
        model.addAttribute("role", roleRepository.findAll());
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUsers(@Valid @ModelAttribute("user") User user,
                                BindingResult result, Model model) {

        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "register";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Created");

        }
        return "index";
    }

    //TODO: Login

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    //TODO: AddPosts
    @GetMapping(value = "/addPost")
    public String addPost(Model model) {
        model.addAttribute("post", new Post());
        return "postform";
    }

    @PostMapping(value = "/addPost")
    public String addPost(@ModelAttribute Post post, Model model) {
        postRepository.save(post);
        return "redirect:/";
    }

    //TODO: DetailUser

    @RequestMapping("/detailUser")
    public String listCars(Model model) {
        model.addAttribute("user", userRepository.findAll());
        return "userdetail";
    }

    //TODO: DeletePosts
    @RequestMapping("/deletePost/{id}")
    public String deleteCar(@PathVariable("id") long id) {
        postRepository.deleteById(id);
        return "redirect:/";
    }

    //TODO: UpdatePosts
    @RequestMapping("/updatePost/{id}")
    public String upateCar(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userRepository.findById(id).get());
        model.addAttribute("post", postRepository.findAll());
        return "postform";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

}
