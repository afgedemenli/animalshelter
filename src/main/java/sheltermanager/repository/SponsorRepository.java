package sheltermanager.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sheltermanager.entity.Sponsor;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Integer> {

	@Query(value = "SELECT * FROM Sponsor", nativeQuery = true)
	Collection<Sponsor> getAll();

	@Query(value = "SELECT * FROM Sponsor where id=:id", nativeQuery = true)
	Sponsor getSponsorById(@Param("id") int id);

	@Query(value = "SELECT id FROM Sponsor ORDER BY id DESC LIMIT 1", nativeQuery = true)
	Integer getNewId();

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO Sponsor VALUES(:id, :name, :surname, :phone, :animals)", nativeQuery = true)
	void addSponsor(@Param("id") int id, @Param("name") String name, @Param("surname") String surname,
									@Param("phone") String phone, @Param("animals") String animals);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Sponsor WHERE id=:id", nativeQuery = true)
	void deleteSponsor(@Param("id") int id);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Sponsor s set s.name = :name where s.id = :id", nativeQuery = true)
	void updateName(@Param("id") Integer id, @Param("name") String name);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Sponsor s set s.surname = :surname where s.id = :id", nativeQuery = true)
	void updateSurname(@Param("id") Integer id, @Param("surname") String surname);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Sponsor s set s.phone = :phone where s.id = :id", nativeQuery = true)
	void updatePhone(@Param("id") Integer id, @Param("phone") String phone);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Sponsor s set s.animals = :animals where s.id = :id", nativeQuery = true)
	void updateAnimals(@Param("id") Integer id, @Param("animals") String animals);
}
