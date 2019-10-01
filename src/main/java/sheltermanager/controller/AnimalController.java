package sheltermanager.controller;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sheltermanager.bean.AnimalFormBean;
import sheltermanager.entity.Animal;
import sheltermanager.repository.AnimalRepository;
import sheltermanager.service.AnimalService;
import sheltermanager.service.CaretakerService;

@Controller
@RequestMapping(path = "/animals")
public class AnimalController {

	private AnimalRepository animalRepository;

	private AnimalService animalService;

	private CaretakerService caretakerService;

	public AnimalController(AnimalRepository animalRepository, AnimalService animalService,
													CaretakerService caretakerService) {
		this.animalRepository = animalRepository;
		this.animalService = animalService;
		this.caretakerService = caretakerService;
	}

	@PostMapping
	public String listAnimals(Model model) {
		model.addAttribute("animals", animalRepository.getAll());
		return "listAnimals";
	}

	@PostMapping(path = "/species")
	public String listAnimalsBySpecies(String species, Model model) {
		model.addAttribute("animals", animalRepository.getAnimalsBySpecies(species));
		return "listAnimals";
	}

	@PostMapping(path = "/noSponsor")
	public String animalsWithNoSponsor(Model model) {
		model.addAttribute("animals", animalService.getAnimalsWithNoSponsor());
		return "listAnimals";
	}

	@PostMapping(path = "/add")
	public String addAnimal(AnimalFormBean formBean, Model model) {
		Integer caretakerId = animalService.addAnimal(formBean);
		caretakerService.addAnimal(caretakerId, animalRepository.getNewId());
		return listAnimals(model);
	}

	@PostMapping(path = "/delete")
	public String deleteAnimal(Integer id, Model model) {
		caretakerService.deleteAnimal(animalRepository.getAnimalById(id).getCaretaker_id(), id);
		animalService.deleteAnimal(id);
		return listAnimals(model);
	}

	@PostMapping(path = "/profile")
	public String profile(Integer id, Model model) {
		model.addAttribute("animal", animalRepository.getAnimalById(id));
		return "animalProfile";
	}

	@PostMapping(path = "/update/name")
	public String updateName(Integer id, String name, Model model) {
		animalRepository.updateName(id, name);
		return profile(id, model);
	}

	@PostMapping(path = "/update/species")
	public String updateSpecies(Integer id, String species, Model model) {
		animalRepository.updateSpecies(id, species);
		return profile(id, model);
	}

	@PostMapping(path = "/update/age")
	public String updateAge(Integer id, Integer age, Model model) {
		animalRepository.updateAge(id, age);
		return profile(id, model);
	}

	@PostMapping(path = "/update/caretaker_id")
	public String updateCaretakerId(Integer id, Integer caretakerId, Model model) {
		animalRepository.updateCaretakerId(id, caretakerId);
		return profile(id, model);
	}

	@PostMapping(path = "/update/sponsor_id")
	public String updateSponsorId(Integer id, Integer sponsorId, Model model) {
		animalRepository.updateSponsorId(id, sponsorId);
		return profile(id, model);
	}
}
