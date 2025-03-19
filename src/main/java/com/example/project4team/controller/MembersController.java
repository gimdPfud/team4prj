package com.example.project4team.controller;

import com.example.project4team.dto.MembersDTO;
import com.example.project4team.service.MembersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/members")
public class MembersController {
    private final MembersService membersService;

    @GetMapping("/read")
    public String read(Principal principal, Model model){
        String email = principal.getName();
        MembersDTO membersDTO = membersService.readMembers(email);
        model.addAttribute("membersDTO",membersDTO);
        return "membersRead";
    }

    @GetMapping("/modify")
    public String modifyGet(Principal principal, Model model){
        String email = principal.getName();
        MembersDTO membersDTO = membersService.readMembers(email);
        model.addAttribute("membersDTO",membersDTO);
        return "memberModify";
    }
    @PostMapping("/modify")
    public String modifyPost(@Valid MembersDTO membersDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(log::info);
            return "memberModify";
        }
        membersService.modifyMembers(membersDTO);
        return "redirect:/members/read";
    }
    @GetMapping("/modify/password")
    public String modifyPwGet(){
        return "memberPasswordModify";
    }

    @PostMapping("/modify/password")
    public String modifyPwpost(Principal principal, MembersDTO membersDTO, RedirectAttributes redirectAttributes){
        if(principal.getName().equals(membersDTO.getEmail())){
            boolean result = membersService.changePassword(membersDTO);
            if(result){
                return "redirect:/members/read";
            }else{
                redirectAttributes.addFlashAttribute("error", "다시 확인해주세요.");
                return "redirect:/members/modify/password";
            }}
        else{
            return "redirect:/logout";
        }
    }
}
