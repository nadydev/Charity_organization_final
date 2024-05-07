package com.example.CharityOrganization.service;
import java.util.List;

public interface CrudService<T> {
    void add(T entity);
    List<T> getAll();
    T getById(Integer id);
    String delete(Integer id);
}

