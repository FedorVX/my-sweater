package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;
    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }


    @GetMapping("/main")
    public String main(@RequestParam(required = false ) String filter, Model model){
        Iterable<Message> messages = messageRepo.findAll();
        if(filter!=null && !filter.isEmpty()){
            messages =  messageRepo.findByTag(filter);
        }else{
            messages = messageRepo.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }


    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user, @RequestParam String text, @RequestParam String tag, Map<String, Object> model){
        Message message = new Message(text, tag, user);
        messageRepo.save(message);


        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);


        return "main";
    }




    @GetMapping("/login")
    public String loginPage(Model model, CsrfToken token) {
        return "login";
    }


//    @ModelAttribute
//    public void addCsrfToken(CsrfToken token, Map<String, Object> model) {
//        model.put("_csrf", token);
//    }
}
