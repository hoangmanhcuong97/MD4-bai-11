package com.demobai11.service;

import com.demobai11.model.SmartPhone;

import java.util.Optional;

public interface ISmartPhoneSV {
    Iterable<SmartPhone> showAll();
    Optional<SmartPhone> findById(Long id);
    void delete(Long id);
    SmartPhone save(SmartPhone smartPhone);
    Iterable<SmartPhone> findAllByProducerContaining(String producer);
}
