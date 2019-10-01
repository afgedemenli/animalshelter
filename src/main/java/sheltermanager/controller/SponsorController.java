package sheltermanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sheltermanager.bean.SponsorFormBean;
import sheltermanager.repository.SponsorRepository;

@Controller
@RequestMapping(path = "/sponsors")
public class SponsorController {

	private SponsorRepository sponsorRepository;

	public SponsorController(SponsorRepository sponsorRepository) {
		this.sponsorRepository = sponsorRepository;
	}

	@PostMapping
	public String listSponsors(Model model) {
		model.addAttribute("sponsors", sponsorRepository.getAll());
		return "listSponsors";
	}

	@PostMapping(path = "/add")
	public String addSponsor(SponsorFormBean formBean, Model model) {
		sponsorRepository.addSponsor((sponsorRepository.getNewId() == null ? 0 : sponsorRepository.getNewId()) + 1,
																 formBean.getName(), formBean.getSurname(),
																 formBean.getPhone(), formBean.getAnimals());
		return listSponsors(model);
	}

	@PostMapping(path = "/delete")
	public String deleteSponsor(Integer id, Model model) {
		sponsorRepository.deleteSponsor(id);
		return listSponsors(model);
	}

	@PostMapping(path = "/profile")
	public String profile(Integer id, Model model) {
		model.addAttribute("sponsor", sponsorRepository.getSponsorById(id));
		return "sponsorProfile";
	}

	@PostMapping(path = "/update/name")
	public String updateName(Integer id, String name, Model model) {
		sponsorRepository.updateName(id, name);
		return profile(id, model);
	}

	@PostMapping(path = "/update/surname")
	public String updateSurname(Integer id, String surname, Model model) {
		sponsorRepository.updateSurname(id, surname);
		return profile(id, model);
	}

	@PostMapping(path = "/update/phone")
	public String updatePhone(Integer id, String phone, Model model) {
		sponsorRepository.updatePhone(id, phone);
		return profile(id, model);
	}

	@PostMapping(path = "/update/animals")
	public String updateAnimals(Integer id, String animals, Model model) {
		sponsorRepository.updateAnimals(id, animals);
		return profile(id, model);
	}
}
