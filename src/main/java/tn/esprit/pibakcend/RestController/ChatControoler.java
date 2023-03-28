package tn.esprit.pibakcend.RestController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ChatControoler {
    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }
}
