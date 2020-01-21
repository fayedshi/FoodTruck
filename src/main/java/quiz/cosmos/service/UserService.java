package quiz.cosmos.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import quiz.cosmos.model.User;
import quiz.cosmos.repository.UserRepository;
import quiz.cosmos.util.HotelAspect;
import quiz.cosmos.util.MyAnnotation;

@Component
public class UserService {
	static ExampleMatcher matcher = ExampleMatcher.matching()
			  .withMatcher("name", match -> match.exact())
			  .withMatcher("phoneNo", match -> match.exact())
			  .withIgnorePaths("id");
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserRepository userRepository;

	public List<User> save(List<User> users) {
		return userRepository.save(users);
	}
	
	@Transactional
	public User saveUser(User u) throws Exception {
		Example<User> example = Example.of(u,matcher);
		if(userRepository.findOne(example) == null)
			return userRepository.save(u);
		throw new Exception("User exists already");
	}
	
	@MyAnnotation
	public List<User> findAll() {
		logger.info("running findAll() ");
		return userRepository.findAll();
	}
	
	public User findOne(int id) {
		return userRepository.findOne(id);
	}
}
