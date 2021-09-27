package com.demobai11.repository;

import com.demobai11.model.SmartPhone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISmartPhoneRP extends CrudRepository<SmartPhone,Long> {
    Iterable<SmartPhone> findAllByProducerContaining(String producer);

}
