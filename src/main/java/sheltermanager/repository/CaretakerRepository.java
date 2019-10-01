package sheltermanager.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sheltermanager.entity.Caretaker;
import org.springframework.stereotype.Repository;

@Repository
public interface CaretakerRepository extends JpaRepository<Caretaker, Integer> {

	@Query(value = "SELECT * FROM caretaker", nativeQuery = true)
	Collection<Caretaker> getAll();

	@Query(value = "SELECT * FROM caretaker where id=:id", nativeQuery = true)
	Caretaker getCaretakerById(@Param("id") int id);

	@Query(value = "SELECT id FROM caretaker ORDER BY id DESC LIMIT 1", nativeQuery = true)
	Integer getNewId();

	@Query(value = "SELECT animals FROM caretaker WHERE id=:caretakerId", nativeQuery = true)
	String getAnimals(@Param("caretakerId") Integer caretakerId);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO caretaker VALUES(:id, :name, :surname, :animals)", nativeQuery = true)
	void addCaretaker(@Param("id") int id, @Param("name") String name, @Param("surname") String surname, @Param("animals") String animals);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM caretaker WHERE id=:id", nativeQuery = true)
	void deleteCaretaker(@Param("id") int id);

	@Transactional
	@Modifying
	@Query(value = "UPDATE caretaker c set c.animals = :animals where c.id = :id", nativeQuery = true)
	void updateAnimals(@Param("id") int id, @Param("animals") String animals);

	@Transactional
	@Modifying
	@Query(value = "UPDATE caretaker c set c.name = :name where c.id = :id", nativeQuery = true)
	void updateName(@Param("id") Integer id, @Param("name") String name);

	@Transactional
	@Modifying
	@Query(value = "UPDATE caretaker c set c.surname = :surname where c.id = :id", nativeQuery = true)
	void updateSurname(@Param("id") Integer id, @Param("surname") String surname);
}
