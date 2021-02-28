package ru.geekbrains.repository;


import java.util.List;

public interface MainRepo<T> {

  List<T> findAll();

  T findById(Long id);

  void saveOrUpdate(T obj);

  void deleteById(Long id);
}

