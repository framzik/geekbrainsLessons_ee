package ru.geekbrains.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.User;

@Named
@ApplicationScoped
public class UserRepository implements MainRepo<User> {

  private final Map<Long, User> userMap = new ConcurrentHashMap<>();

  private final AtomicLong identity = new AtomicLong(0);

  @PostConstruct
  public void init() {
    this.saveOrUpdate(new User(null, "Vasiliy", "+7(999) 999 99 99", "fr@ya.ru"));
    this.saveOrUpdate(new User(null, "Petr", "+7(999) 999 99 98", "fr1@ya.ru"));
    this.saveOrUpdate(new User(null, "Борис", "+7(999) 999 99 97", "fr2@ya.ru"));
  }


  @Override
  public ArrayList<User> findAll() {
    return new ArrayList<>(userMap.values());
  }

  @Override
  public User findById(Long id) {
    return userMap.get(id);
  }

  @Override
  public void saveOrUpdate(User user) {
    if (user.getId() == null) {
      Long id = identity.getAndIncrement();
      user.setId(id);
    }
    userMap.put(user.getId(), user);
  }

  @Override
  public void deleteById(Long id) {
    userMap.remove(id);
  }
}
