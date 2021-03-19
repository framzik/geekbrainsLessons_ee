package ru.geekbrains.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import ru.geekbrains.DtoEntities.UserRepr;
import ru.geekbrains.UserService;
import ru.geekbrains.entity.User;
import ru.geekbrains.repository.UserRepository;

@Stateless
public class UserServiceImpl implements UserService {

  @EJB
  private UserRepository userRepository;

  @Override
  public List<UserRepr> findAll() {
    return userRepository.findAll().stream().map(UserRepr::new).collect(Collectors.toList());
  }

  @Override
  public UserRepr findById(Long id) {
    User user = userRepository.findById(id);
    if(user!=null){
      return new UserRepr(user);
    }
    return null;
  }

  @Override
  @TransactionAttribute
  public void saveOrUpdate(UserRepr user) {
    User saved = userRepository.saveOrUpdate(new User(user));
    user.setId(saved.getId());
  }

  @Override
  @TransactionAttribute
  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }

  @Override
  public Long countAll() {
    return userRepository.getCount();
  }

  @TransactionAttribute
  public List<UserRepr> getAllUsers() {
    return userRepository.findAll().stream()
            .map(UserRepr::new)
            .collect(Collectors.toList());
  }
}
