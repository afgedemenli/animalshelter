package sheltermanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sheltermanager.bean.UserFormBean;
import sheltermanager.repository.UserRepository;

@Controller
@RequestMapping(path = "/managers")
public class ManagerController {

	private UserRepository userRepository;

	public ManagerController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping
	public String listUsers(Model model) {
		model.addAttribute("managers", userRepository.getAll());
		return "listManagers";
	}

	@PostMapping(path = "/add")
	public String addManager(UserFormBean formBean, Model model) {
		userRepository.addManager(userRepository.getNewId() + 1, formBean.getUsername(), formBean.getPassword());
		return listUsers(model);
	}

	@PostMapping(path = "/delete")
	public String deleteManager(Integer id, Model model) {
		userRepository.deleteUser(id);
		return listUsers(model);
	}

	@PostMapping(path = "/profile")
	public String profile(Integer id, Model model) {
		model.addAttribute("manager", userRepository.getUserById(id));
		return "managerProfile";
	}

	@PostMapping(path = "/update/username")
	public String updateUsername(Integer id, String username, Model model) {
		userRepository.updateUsername(id, username);
		return profile(id, model);
	}

	@PostMapping(path = "/update/password")
	public String updatePassword(Integer id, String password, Model model) {
		userRepository.updatePassword(id, password);
		return profile(id, model);
	}
}
