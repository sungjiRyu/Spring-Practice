package com.sjryu.book_info.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjryu.book_info.VO.AdminAccountinfoVO;
import com.sjryu.book_info.entity.AdminAccountEntity;
import com.sjryu.book_info.repository.AdminAccountRepository;

import jakarta.annotation.Nullable;

@Controller
// http://localhost:8080/member
@RequestMapping("/member")
public class MemberController {
    String blank_pattern = "/[\s]/g";
    
    @Autowired AdminAccountRepository adminAccountRepository;
    @GetMapping("/join") // @RequestMapping 기준
    public String getMemberJoin(){
        return "/member/join"; 
        // application.properties prefix, suffix 기준 (@RequestMapping 과 상관없음)
    }   
    @PostMapping("/join") // @RequestMapping 기준
    public String postMemberJoin(String id, String pwd, String name, Model model){
        // String kor_pattern = ".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*";
        String eng_pattern = "^[a-zA-Z0-9]*$"; // 영문 및 숫자만 가능
        Pattern p = Pattern.compile(eng_pattern);
        if(!p.matcher(id).matches()){
            model.addAttribute("status", "invalidId");
            return "/member/join";
        }
        // System.out.println(p.matcher(id).matches());
        // String trimId = id.trim(); // 문자열 앞 뒤의 공백문자 모두 제거
        // String replacedId = id.replace(" ",""); // 공백문자를 제거
        // if(id.length()!=replacedId.length()){ // 공백이 제거되면 원본과 변환후 문자열 길이가 달라짐.
        //     model.addAttribute("status","whitespaceId"); // 따라서 공백이 포함된 문자가 들어온것.
        //     model.addAttribute("id", id);
        //     return "/member/join";
        // }

        // String replacedPwd = pwd.replaceAll(" ","");
        // if(pwd.length() != replacedPwd.length()){
        //     model.addAttribute("status", "whitespacePwd");
        //     model.addAttribute("id", id);
        //     return "/member/join";
        // }
        String pwd_pattern = "^[a-zA-Z0-9`*$`~!@#$%^&*()-_=]{6,20}$";
        p = Pattern.compile(pwd_pattern);
        if(!p.matcher(pwd).matches()){
            model.addAttribute("status", "invalidPwd");
            return "/member/join";
        }
        if(id.equals("") || id == null){
            model.addAttribute("status", "emtyId");
            model.addAttribute("name", name);
            return "/member/join";
        }
        
        if(pwd.equals("") || pwd == null){
            model.addAttribute("status", "emtyPwd");
            model.addAttribute("id", id);
            model.addAttribute("name", name);
            return "/member/join";
        }
        if(name.equals("") || name == null){
            model.addAttribute("status", "emtyName");
            model.addAttribute("id", id);
            model.addAttribute("name", name);
            return "/member/join";
        }
        // 중복아이디검사
        if(adminAccountRepository.countByAiId(id) > 0){
            model.addAttribute("id", id);
            model.addAttribute("name", name);
            model.addAttribute("status", "duplicated");
            return "/member/join";
        }

        else{
            adminAccountRepository.save(AdminAccountEntity.builder().aiId(id).aiPwd(pwd).aiName(name).build());
            // application.properties prefix, suffix 기준 (@RequestMapping 과 상관없음)
            return "/login"; 
        }
    }   
    // 자바의 영속성 구조를 깨뜨리기 위해 DTO/VO를 만듬(테이블에 귀속되어 있지 않기 때문에 마음대로 써도 테이블에 영향x)
    @GetMapping("/list")
    public String memberList(Model model, 
    Pageable pageable){
    // @RequestParam @Nullable Integer page,
    // @RequestParam @Nullable Integer size)
        // if(page == null) page = 0;
        // if(size == null) size = 10;
        Page<AdminAccountEntity> page = adminAccountRepository.findAll(pageable);
        List<AdminAccountinfoVO> accountList = new ArrayList<AdminAccountinfoVO>();
        for(AdminAccountEntity a : page.getContent()){
            accountList.add(new AdminAccountinfoVO(a));
        }
        // http://localhost:8080/member/list?page=0&size=10&sort=aiSeq,desc
        model.addAttribute("accountList", accountList);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalCount", page.getTotalElements());
        model.addAttribute("currentPage", page.getNumber());
        return "/member/list";
    }
    
    // 하이퍼링크는 GetMapping(클릭하면 새페이지로 이동 => 주소창에 페이지주소입력)
    // 상태 바꾸는 기능 (사용정지 <-> 사용가능)
    @GetMapping("status")
    public String getMemberStatusUpdate(@RequestParam Long seq, @RequestParam Integer status){
    AdminAccountEntity entity = adminAccountRepository.findByAiSeq(seq); // select 하고
    entity.setAiStatus(status); // ai_status 값을 바꾸고
    adminAccountRepository.save(entity); // ctrl + s
    return "redirect:/member/list"; 
}
    // 삭제하기
    @GetMapping("delete")
    public String getMemberStatusDelete(@RequestParam Long seq){
    AdminAccountEntity entity = adminAccountRepository.findByAiSeq(seq); // select 하고
    adminAccountRepository.delete(entity); // 삭제
    return "redirect:/member/list"; 
}
}