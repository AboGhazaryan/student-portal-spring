package com.example.studentportalspring.controller;


import com.example.studentportalspring.model.User;
import com.example.studentportalspring.service.UserService;
import com.example.studentportalspring.service.security.SpringUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class MainController {
    @Value("${student.portal.image.directory.path}")
    private String imageDirectoryPath;

    private final UserService userService;

    @GetMapping("/")
    public String mainPage(@AuthenticationPrincipal SpringUser userDetails, ModelMap modelMap) {
        if (userDetails != null) {
            modelMap.addAttribute("user", userDetails.getUser());
        }
         return "index";
    }

    @GetMapping("/loginPage")
    public String loginPage(@RequestParam(required = false) String msg, ModelMap modelMap) {
        modelMap.addAttribute("msg", msg);
        return "loginPage";
    }

    @GetMapping("/registerPage")
    public String registerPage(@RequestParam(required = false) String msg, ModelMap modelMap) {
        modelMap.addAttribute("msg", msg);
        return "registerPage";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        if(userService.findByUserName(user.getUsername()).isPresent()){
            return "redirect:/registerPage?msg=Username already exists!";
        }
        userService.save(user);
        return "redirect:/loginPage?msg=Registered Successfully! Please login again!";
    }

    @GetMapping("/image/get")
    public @ResponseBody byte[] getImage(@RequestParam("picture") String pictureName) {
        File file = new File(imageDirectoryPath + pictureName);
        if (file.exists() && file.isFile()) {
            try {
                return FileUtils.readFileToByteArray(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
