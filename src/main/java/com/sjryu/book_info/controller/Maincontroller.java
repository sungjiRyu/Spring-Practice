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
    public String getMain(Model model, HttpSession session){
        model.addAttribute("info", "model Info");
        session.setAttribute("info2", "session Info");
        System.out.println("getMain() ");
        return "/index";
    }
    @GetMapping("/index2")
    public String getMain2(){
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
    // session => 사용자의 정보를 저장(사용자마자 따로 존재) // 한번설정하면 계속 유지(URL이 바뀌더라도 = 한번 설정해놓으면 다른맵핑에서도 유지됨)  // session = 전역적, model = 지엽적 // 따라서 로그인정보를 session에 넣음(한번 로그인하면 다른페이지로 넘어가도 로그인상태 유지)
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
    @GetMapping("/logout")
    public String getLogout(HttpSession session){
        // session.invalidate(); // 세션정보 모두 삭제(세션 무효화)
        session.setAttribute("loginUser", null); // 로그인 정보만 삭제
        return "redirect:/";
    }
}
