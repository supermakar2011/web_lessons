package org.makar.sweater.controller;

import org.makar.sweater.domain.Message;
import org.makar.sweater.domain.User;
import org.makar.sweater.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Value("${upload.path}")
    String uploadPath;

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String,Object> model) {

        return "greeting";
    }
    @GetMapping("/main")
    public String main(@RequestParam(required = false,defaultValue = "") String filter, Map<String,Object> model){
        Iterable<Message> messages;
        if (filter != null&&!filter.isEmpty()){
            messages= messageRepo.findByTag(filter);
        }else {
            messages = messageRepo.findAll();
        }
        model.put("messages",messages);
        model.put("filter",filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam("file") MultipartFile file,
            Map<String,Object> model) throws IOException {
        Message message = new Message(text,tag,user);
        if (file != null&&!file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" +resultFileName));
            message.setFilename(resultFileName);
        }
        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages",messages);

        return "main";
    }




}