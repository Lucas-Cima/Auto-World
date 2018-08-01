package com.autoworld.autoworld.models.data;

import com.autoworld.autoworld.models.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CarDao extends CrudRepository<Car, Integer> {
}
