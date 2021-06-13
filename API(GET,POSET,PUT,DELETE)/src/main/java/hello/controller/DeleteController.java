package hello.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delete-api")
public class DeleteController {

    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable String userId, @RequestParam String account) {
        System.out.println(userId);
        System.out.println(account);
    }
}
