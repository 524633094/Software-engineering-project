package com.nwnu.greencloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class visualController {
    @GetMapping("/visual/{apikey}/{devname}")
    public String getVisualPage(@PathVariable String apikey, @PathVariable String devname, Model model){
        model.addAttribute("apikey",apikey);
        model.addAttribute("devname",devname);
        return "visual";
    }
}
