package com.autoworld.autoworld.models.data;

import com.autoworld.autoworld.models.Tech;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TechDao extends CrudRepository<Tech, Integer> {
}
