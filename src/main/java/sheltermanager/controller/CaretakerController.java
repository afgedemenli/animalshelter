package sheltermanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sheltermanager.bean.CaretakerFormBean;
import sheltermanager.repository.CaretakerRepository;
import sheltermanager.service.CaretakerService;

@Controller
@RequestMapping(path = "/caretakers")
public class CaretakerController {

	private CaretakerService caretakerService;

	private CaretakerRepository caretakerRepository;

	public CaretakerController(CaretakerService caretakerService,
														 CaretakerRepository caretakerRepository) {
		this.caretakerService = caretakerService;
		this.caretakerRepository = caretakerRepository;
	}

	@PostMapping
	public String listCaretakers(Model model) {
		model.addAttribute("caretakers", caretakerRepository.getAll());
		return "listCaretakers";
	}

	@PostMapping(path = "/ranked")
	public String listRanked(Model model) {
		model.addAttribute("caretakers", caretakerService.getCaretakersRanked());
		return "listCaretakers";
	}

	@PostMapping(path = "/add")
	public String addCaretaker(CaretakerFormBean formBean, Model model) {
		caretakerRepository.addCaretaker((caretakerRepository.getNewId() != null ? caretakerRepository.getNewId() : 0) + 1,
																		 formBean.getName(), formBean.getSurname(), "");
		return listCaretakers(model);
	}

	@PostMapping(path = "/delete")
	public String deleteCaretaker(Integer id, Model model) {
		caretakerRepository.deleteCaretaker(id);
		return listCaretakers(model);
	}

	@PostMapping(path = "/profile")
	public String profile(Integer id, Model model) {
		model.addAttribute("caretaker", caretakerRepository.getCaretakerById(id));
		return "caretakerProfile";
	}

	@PostMapping(path = "/update/name")
	public String updateName(Integer id, String name, Model model) {
		caretakerRepository.updateName(id, name);
		return profile(id, model);
	}

	@PostMapping(path = "/update/surname")
	public String updateSurname(Integer id, String surname, Model model) {
		caretakerRepository.updateSurname(id, surname);
		return profile(id, model);
	}
}
