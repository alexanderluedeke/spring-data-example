package company;

import company.people.User;
import company.people.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private UserRepository users;

  @Test
  public void shouldFindByLastName() {
    User user = new User("first", "last");
    entityManager.persist(user);

    List<User> findByLastName = users.findByLastName(user.getLastName());

    assertThat(findByLastName).extracting(User::getLastName).containsOnly(user.getLastName());
  }
}