package com.example.studentportalspring.controller;


import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;

@Controller
public class MainController {
    @Value("${student.portal.image.directory.path}")
    private String imageDirectoryPath;

    @GetMapping("/")
    public String mainPage(@AuthenticationPrincipal UserDetails userDetails, ModelMap modelMap) {
        if (userDetails != null) {
            modelMap.addAttribute("userName", userDetails.getUsername());
        }
         return "index";
    }

    @GetMapping("/loginPage")
    public String loginPage(){
        return "login";
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
