package ru.geekbrains;

import ru.geekbrains.DtoEntities.RoleRepr;
import ru.geekbrains.DtoEntities.UserRepr;
import ru.geekbrains.entity.Role;
import ru.geekbrains.entity.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserService {

  List<UserRepr> findAll();

  UserRepr findById(Long id);

  void merge(UserRepr user);
  //void saveOrUpdate(UserRepr user);

  void deleteById(Long id);

  Long countAll();
  List<Role> getRolesForUser(UserRepr user);
}
