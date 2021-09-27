package com.demobai11.service;

import com.demobai11.model.SmartPhone;
import com.demobai11.repository.ISmartPhoneRP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SmartPhoneSv implements ISmartPhoneSV{
    @Autowired
    private ISmartPhoneRP iSmartPhoneRP;
    @Override
    public Iterable<SmartPhone> showAll() {
        return iSmartPhoneRP.findAll();
    }

    @Override
    public Optional<SmartPhone> findById(Long id) {
        return iSmartPhoneRP.findById(id);
    }

    @Override
    public void delete(Long id) {
        iSmartPhoneRP.deleteById(id);
    }

    @Override
    public SmartPhone save(SmartPhone smartPhone) {
        return iSmartPhoneRP.save(smartPhone);
    }

    @Override
    public Iterable<SmartPhone> findAllByProducerContaining(String producer) {
        return iSmartPhoneRP.findAllByProducerContaining(producer);
    }
}
