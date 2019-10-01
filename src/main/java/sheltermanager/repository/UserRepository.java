package sheltermanager.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sheltermanager.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  @Query(value = "SELECT * FROM USER", nativeQuery = true)
  Collection<User> getAll();

  @Query(value = "SELECT * FROM USER where id=:id", nativeQuery = true)
  User getUserById(@Param("id") int id);

  @Query(value = "SELECT id FROM USER ORDER BY id DESC LIMIT 1", nativeQuery = true)
  Integer getNewId();

  @Query(value = "SELECT password FROM USER WHERE username=:username", nativeQuery = true)
  String getPw(String username);

  @Transactional
  @Modifying
  @Query(value = "INSERT INTO user VALUES(:id, :username, :password)", nativeQuery = true)
  void addManager(@Param("id") int id, @Param("username") String username, @Param("password") String password);

  @Transactional
  @Modifying
  @Query(value = "DELETE FROM user WHERE id=:id", nativeQuery = true)
  void deleteUser(@Param("id") int id);

  @Transactional
  @Modifying
  @Query(value = "UPDATE user u set u.username = :username where u.id = :id", nativeQuery = true)
  void updateUsername(@Param("id") Integer id, @Param("username") String username);

  @Transactional
  @Modifying
  @Query(value = "UPDATE user u set u.password = :password where u.id = :id", nativeQuery = true)
  void updatePassword(@Param("id") Integer id, @Param("password") String password);
}
