package sheltermanager.service;

import java.util.ArrayList;
import java.util.Collections;
import org.springframework.stereotype.Service;
import sheltermanager.entity.Caretaker;
import sheltermanager.repository.CaretakerRepository;

@Service
public class CaretakerService {

	private CaretakerRepository caretakerRepository;

	public CaretakerService(CaretakerRepository caretakerRepository) {
		this.caretakerRepository = caretakerRepository;
	}

	public void addAnimal(Integer caretakerId, Integer animalId) {
		String ids = caretakerRepository.getAnimals(caretakerId);
		caretakerRepository.updateAnimals(caretakerId, ids + " " + animalId);
	}

	public void deleteAnimal(Integer caretaker_id, Integer animalId) {
		String[] animals = caretakerRepository.getCaretakerById(caretaker_id).getAnimals().split("\\s+");
		String newAnimals = "";
		for (String animal : animals) {
			if (animal.length() > 0 && Integer.parseInt(animal) != animalId) {
				newAnimals = newAnimals + animal;
			}
		}
		caretakerRepository.updateAnimals(caretaker_id, newAnimals);
	}

	public ArrayList<Caretaker> getCaretakersRanked() {
		ArrayList<Caretaker> caretakers = (ArrayList<Caretaker>) caretakerRepository.getAll();
		Collections.sort(caretakers);
		return caretakers;
	}
}
