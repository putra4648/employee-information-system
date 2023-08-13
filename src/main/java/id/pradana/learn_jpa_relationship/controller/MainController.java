package id.pradana.learn_jpa_relationship.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

  @GetMapping("/")
  public ModelAndView main(ModelMap model) {
    return new ModelAndView("index", model);
  }
}
