package com.sjryu.book_info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sjryu.book_info.VO.LoginUserVO;
import com.sjryu.book_info.entity.AdminAccountEntity;
import com.sjryu.book_info.repository.AdminAccountRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class Maincontroller {
    @Autowired AdminAccountRepository adminAccountRepository;
    @GetMapping("/")
    public String getMain(){
        System.out.println("⫸⫸⫸⫸⫸⫸⫸getMain() ");
        return "/index";
    }
    
    @GetMapping("/login")
    public String getLogin(HttpServletResponse response){
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 새로고침했을때 이전정보 삭제(헤더 설정)
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        return "/login";
    }
    @PostMapping("/login")
    public String postLogin(String id, String pwd, Model model, HttpSession session){
        System.out.println(id);
        System.out.println(pwd);
        AdminAccountEntity loginUser = adminAccountRepository.findByAiIdAndAiPwd(id, pwd);
        if(loginUser == null){
            model.addAttribute("loginStatus", "failed");
            model.addAttribute("message", "아이디 또는 비밀번호 오류입니다.");
            return "/login";
        }
        session.setAttribute("loginUser", new LoginUserVO(loginUser));
        
        // redirect - 파일경로가 아니라, 매핑 경로 기준.
        // 즉 redirect:/ ==> ("/")로 돌아감(=>getMain)
        return "redirect:/";
    }
}
