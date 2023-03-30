package BDDInterraction;


import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


@Repository
public interface InterfaceBeans extends CrudRepository<Beans, Integer> {

    @Override
    Optional<Beans> findById(Integer integer);

    @Override
    Iterable<Beans> findAll();

}