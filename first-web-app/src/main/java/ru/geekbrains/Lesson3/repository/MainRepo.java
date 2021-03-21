package ru.geekbrains.Lesson3.repository;


import java.util.ArrayList;

public interface MainRepo<T> {

  ArrayList<T> findAll();

  T findById(Long id);

  void saveOrUpdate(T obj);

  void deleteById(Long id);
}

