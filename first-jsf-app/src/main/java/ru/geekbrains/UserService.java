package ru.geekbrains;

import java.util.List;
import javax.ejb.Local;

import ru.geekbrains.DtoEntities.UserRepr;

@Local
public interface UserService {

  List<UserRepr> findAll();

  UserRepr findById(Long id);

  void saveOrUpdate(UserRepr user);

  void deleteById(Long id);

  Long countAll();
}
