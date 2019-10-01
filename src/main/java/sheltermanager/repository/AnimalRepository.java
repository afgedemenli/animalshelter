package sheltermanager.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sheltermanager.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

	@Query(value = "SELECT * FROM Animal", nativeQuery = true)
	Collection<Animal> getAll();

	@Query(value = "CALL getBySpecies(:species)", nativeQuery = true)
	Collection<Animal> getAnimalsBySpecies(@Param("species") String species);

	@Query(value = "SELECT * FROM Animal where id=:id", nativeQuery = true)
	Animal getAnimalById(@Param("id") int id);

	@Query(value = "SELECT id FROM Animal ORDER BY id DESC LIMIT 1", nativeQuery = true)
	Integer getNewId();

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO Animal VALUES(:id, :age, :name, :species, :caretaker_id, :sponsor_id)",
				 nativeQuery = true)
	void addAnimal(@Param("id") int id, @Param("name") String name, @Param("species") String species,
								 @Param("age") Integer age, @Param("caretaker_id") Integer caretaker_id,
								 @Param("sponsor_id") Integer sponsor_id);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Animal WHERE id=:id", nativeQuery = true)
	void deleteAnimal(@Param("id") int id);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Animal a set a.name = :name where a.id = :id", nativeQuery = true)
	void updateName(@Param("id") Integer id, @Param("name") String name);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Animal a set a.species = :species where a.id = :id", nativeQuery = true)
	void updateSpecies(@Param("id") Integer id, @Param("species") String species);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Animal a set a.age = :age where a.id = :id", nativeQuery = true)
	void updateAge(@Param("id") Integer id, @Param("age") Integer age);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Animal a set a.caretaker_id = :caretaker_id where a.id = :id", nativeQuery = true)
	void updateCaretakerId(@Param("id") Integer id, @Param("caretaker_id") Integer caretaker_id);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Animal a set a.sponsor_id = :sponsor_id where a.id = :id", nativeQuery = true)
	void updateSponsorId(@Param("id") Integer id, @Param("sponsor_id") Integer sponsor_id);
}
