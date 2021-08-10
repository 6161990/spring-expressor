package kim.board.controller;

import kim.board.model.PostEntity;
import kim.board.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/boards")
public class PostController {

    @Autowired
    private final PostService postService;

    @GetMapping("")
    public String list(@Validated Model model){
        List<PostEntity> postList = postService.findAll();
        model.addAttribute("postList", postList);
        return "list";
    }

    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("post", new PostEntity());
        return "addForm";
    }

    @PostMapping("/add")
    public String addForm(@Validated @ModelAttribute("post") PostEntity postRequest,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "/boards/addForm";
        }

        PostEntity postSave = postService.add(postRequest);
        redirectAttributes.addAttribute("postId", postSave.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/boards/{postId}";
    }

    @GetMapping("{postId}")
    public String getOne(@PathVariable long postId,
                         Model model){
        PostEntity postEntity = postService.searchById(postId);
        if(postEntity != null){
            postService.countPlus(postId);
        }
        model.addAttribute("post", postEntity);
        return "post";
    }

    @GetMapping("/{postId}/edit")
    public String editPost(@PathVariable Long postId, Model model){
        PostEntity post = postService.searchById(postId);
        model.addAttribute("post", post);
        return "editPost";
    }

    @PostMapping("/{postId}/edit")
    public String editPost(@PathVariable Long postId, @ModelAttribute PostEntity updatePost){
        PostEntity post = postService.updateById(postId, updatePost);
        return "redirect:/boards/{postId}";
    }

    @RequestMapping("/{postId}/delete")
    public String deleteOne(@PathVariable Long postId){
        postService.deleteById(postId);
        return "redirect:/boards";
    }

}
