package com.example.helloworld.db;

import com.example.helloworld.core.Person;
import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;

public class PersonDAO extends AbstractDAO<Person> {
    @Inject
    @Named("HibernateBundle")
    public PersonDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Person create(Person person) {
        return persist(person);
    }

    public List<Person> findAll() {
        return list(namedTypedQuery("com.example.helloworld.core.Person.findAll"));
    }
}
