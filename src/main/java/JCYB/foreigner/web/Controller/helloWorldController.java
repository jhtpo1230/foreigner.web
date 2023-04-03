package JCYB.foreigner.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class helloWorldController {

    @GetMapping("/")
    public String helloWorld(Model model) { //3rd commit
        model.addAttribute("name", "Mustache");
        return "index";
        //test commit

    }
}
