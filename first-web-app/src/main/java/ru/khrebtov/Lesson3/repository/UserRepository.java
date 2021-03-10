package ru.khrebtov.Lesson3.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import ru.khrebtov.Lesson3.entity.User;

public class UserRepository implements MainRepo<User> {

  private final Map<Long, User> userMap = new ConcurrentHashMap<>();

  private final AtomicLong identity = new AtomicLong(0);

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
