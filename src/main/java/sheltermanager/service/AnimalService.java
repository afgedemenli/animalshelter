package sheltermanager.service;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Service;
import sheltermanager.bean.AnimalFormBean;
import sheltermanager.entity.Animal;
import sheltermanager.entity.Caretaker;
import sheltermanager.repository.AnimalRepository;
import sheltermanager.repository.CaretakerRepository;
import sheltermanager.repository.SponsorRepository;

@Service
public class AnimalService {

	private AnimalRepository animalRepository;

	private CaretakerRepository caretakerRepository;

	private SponsorRepository sponsorRepository;

	public AnimalService(AnimalRepository animalRepository,
											 CaretakerRepository caretakerRepository,
											 SponsorRepository sponsorRepository) {
		this.animalRepository = animalRepository;
		this.caretakerRepository = caretakerRepository;
		this.sponsorRepository = sponsorRepository;
	}

	public ArrayList<Animal> getAnimalsWithSpecies(String species) {
		Collection<Animal> all = animalRepository.getAll();
		ArrayList<Animal> animals = new ArrayList<>();
		for (Animal animal : all) {
			if (animal.getSpecies().equals(species)) {
				animals.add(animal);
			}
		}
		return animals;
	}

	public ArrayList<Animal> getAnimalsWithNoSponsor() {
		Collection<Animal> all = animalRepository.getAll();
		ArrayList<Animal> animals = new ArrayList<>();
		for (Animal animal : all) {
			if (animal.getSponsor_id()==null || animal.getSponsor_id()<=0) {
				animals.add(animal);
			}
		}
		return animals;
	}

	public Integer addAnimal(AnimalFormBean formBean) {
		Integer caretakerId = caretakerIdSupplier();
		animalRepository.addAnimal((animalRepository.getNewId() == null ? 0 : animalRepository.getNewId()) + 1,
															 formBean.getName(), formBean.getSpecies(), formBean.getAge(), caretakerId,
															 formBean.getSponsor_id());
		if (formBean.getSponsor_id() != null && formBean.getSponsor_id() > 0) {
			sponsorRepository.updateAnimals(formBean.getSponsor_id(),
																			sponsorRepository.getSponsorById(formBean.getSponsor_id()).getAnimals()
																			+ " "
																			+ animalRepository.getNewId());
		}
		return caretakerId;
	}

	public void deleteAnimal(Integer id) {
		Integer sponsorId = animalRepository.getAnimalById(id).getSponsor_id();
		if (sponsorId != null) {
			Collection<Animal> animals = animalRepository.getAll();
			Integer maxAge = Integer.MIN_VALUE;
			Integer oldestId = Integer.MIN_VALUE;
			for (Animal animal : animals) {
				if (animal.getSponsor_id() == null && animal.getAge() > maxAge) {
					maxAge = animal.getAge();
					oldestId = animal.getId();
				}
			}
			String[] animalIds = sponsorRepository.getSponsorById(sponsorId).getAnimals().split("\\s+");
			String animalsNew = "";
			for (String animalId : animalIds) {
				if (animalId.length() > 0 && Integer.parseInt(animalId) != id) {
					animalsNew = animalsNew + " " + animalId;
				}
			}
			if (maxAge != Integer.MIN_VALUE) {
				animalRepository.updateSponsorId(oldestId, sponsorId);
				animalsNew = animalsNew + " " + oldestId;
			}
			sponsorRepository.updateAnimals(sponsorId, animalsNew);
		}
		animalRepository.deleteAnimal(id);
	}

	private Integer caretakerIdSupplier() {
		Integer caretakerId = 1;
		Integer minAnimalNumber = Integer.MAX_VALUE;
		Collection<Caretaker> caretakers = caretakerRepository.getAll();
		for (Caretaker caretaker : caretakers) {
			String animals = caretaker.getAnimals();
			if (animals == null) {
				caretakerId = caretaker.getId();
				break;
			}
			Integer numberOfAnimals = animals.split("\\s+").length;
			if (numberOfAnimals < minAnimalNumber) {
				minAnimalNumber = numberOfAnimals;
				caretakerId = caretaker.getId();
			}
		}
		return caretakerId;
	}
}
