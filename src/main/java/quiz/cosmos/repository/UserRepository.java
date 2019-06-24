package quiz.cosmos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import quiz.cosmos.model.User;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {

}
