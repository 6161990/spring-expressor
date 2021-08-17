package com.sp.fc.web.controller;

import com.sp.fc.web.config.CustomSecurityTag;
import com.sp.fc.web.service.Paper;
import com.sp.fc.web.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/paper")
@RestController
public class PaperController {

    @Autowired
    private PaperService paperService;

//    @PreAuthorize("isStudent()")
//    @PostFilter("filterObject.state != T(com.sp.fc.web.service.Paper.State).PREPARE")
//    filterObject은 List<Paper>안에 들어있는 paper하나를 뜻함. 준비중인 시험지는 접근하지 못함.
//    @PostFilter("notPrepareState(filterObject) && filterObject.studentIds.contains(#user.username)")
    @PostFilter("notPrepareState(filterObject)")
    @GetMapping("/mypapers")
    public List<Paper> myPapers(@AuthenticationPrincipal User user){
        return paperService.getMyPapers(user.getUsername());
    }

//    @PreAuthorize("hasPermission(#paperId,'paper','read')")
    //paperId로 paper에 read할 수 있는 permission만들기
    @PostAuthorize("returnObject.studentIds.contains(principal.username)")
    @GetMapping("/get/{paperId}")
    public Paper getPaper(@AuthenticationPrincipal User user, @PathVariable Long paperId){
        return paperService.getPaper(paperId);
    }

  //  @Secured({"SCHOOL_PRIMARY"})
    @CustomSecurityTag("SCHOOL_PRIMARY")
    @GetMapping("/getPapersByPrimary")
    public List<Paper> getPapersByPrimary(){
        return paperService.getAllPapers();
    }
}
