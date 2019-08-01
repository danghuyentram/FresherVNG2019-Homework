package rockPaperScissors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rockPaperScissors.model.Turn;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TurnRepository extends JpaRepository<Turn, Integer> {

}