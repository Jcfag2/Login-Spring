package fr.gtm.hellomvc;

import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.nom = ?1")
	User getByNom(String nom);
	
	@Query(value = "SELECT digest from users WHERE user = ?1", nativeQuery = true)
	String getValues(String nom);
	
	@Modifying
	@Query(value = "INSERT INTO users (id, user, password, role, digest) VALUES (0, ?1, ?2, ?3, ?4)", nativeQuery = true)
	@Transactional
	void createUser( String nom, String pw, String role, String sha);
}
