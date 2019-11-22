package fr.gtm.hellomvc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.nom = ?1")
	User getByNom(String nom);
	
	@Query(value = "SELECT digest from users WHERE user = ?1", nativeQuery = true)
	String getValues(String nom);
}
