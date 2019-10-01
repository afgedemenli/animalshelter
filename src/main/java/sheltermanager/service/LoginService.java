package sheltermanager.service;

import org.springframework.stereotype.Service;
import sheltermanager.bean.UserFormBean;
import sheltermanager.repository.UserRepository;

@Service
public class LoginService {

	private UserRepository userRepository;

	public LoginService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public boolean login(UserFormBean formBean) {
		String username = formBean.getUsername();
		String password = formBean.getPassword();
		return password.equals(userRepository.getPw(username));
	}
}
