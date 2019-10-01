package sheltermanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sheltermanager.bean.UserFormBean;
import sheltermanager.service.LoginService;

@Controller
@RequestMapping
public class MainController {

  private LoginService loginService;

  public MainController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping(path = "/login")
  public String login(UserFormBean formBean) {
    return loginService.login(formBean) ? "menu" : "error";
  }
}
