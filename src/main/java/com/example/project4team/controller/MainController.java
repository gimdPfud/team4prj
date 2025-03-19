package com.example.project4team.controller;

import com.example.project4team.dto.MembersDTO;
import com.example.project4team.service.MembersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MainController {

    private final MembersService membersService;

    @GetMapping("/signUp")
    public String signUpGet(){
        return "signUp";
    }
    @PostMapping("/signUp")
    public String signUpPost(@Valid MembersDTO membersDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("회원가입 포스트 : "+membersDTO);
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(log::info);
            return "redirect:/signUp";
        }
        try{
            String name = membersService.insertMembers(membersDTO);
            redirectAttributes.addFlashAttribute("name", name);
            return "redirect:/login";
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/signUp";
        }
    }
    @GetMapping("/login")
    public String loginGet(){
        return "login";
    }
    @GetMapping({"/main","/"})
    public String mainGet(){
        return "main";
    }
}
