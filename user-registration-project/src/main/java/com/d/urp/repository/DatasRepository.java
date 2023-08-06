package com.d.urp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d.urp.entity.Datas;

public interface DatasRepository extends JpaRepository<Datas, Integer>{

	Optional<Datas> findByKey(String key);

}
