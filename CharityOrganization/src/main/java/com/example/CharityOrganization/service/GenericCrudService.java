package com.example.CharityOrganization.service;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class GenericCrudService<T> implements CrudService<T> {

    protected abstract JpaRepository<T, Integer> getRepository();

    

}

