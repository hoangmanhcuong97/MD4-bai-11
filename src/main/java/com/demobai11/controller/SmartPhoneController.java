package com.demobai11.controller;

import com.demobai11.dto.ResponseMessage;
import com.demobai11.model.SmartPhone;
import com.demobai11.service.ISmartPhoneSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/phone")
public class SmartPhoneController {
    @Autowired
    private ISmartPhoneSV iSmartPhoneSV;

    @PostMapping
    public ResponseEntity<?> createSmartPhone(@RequestBody SmartPhone smartPhone){
        iSmartPhoneSV.save(smartPhone);
        return new ResponseEntity<>(new ResponseMessage("create success"), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Iterable<SmartPhone>> showList() {
        return new ResponseEntity<>(iSmartPhoneSV.showAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SmartPhone> detailSmartPhone(@PathVariable Long id){
        Optional<SmartPhone> smartPhone = iSmartPhoneSV.findById(id);
        if(!smartPhone.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(smartPhone.get(),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSmartPhone(@PathVariable("id") Optional<SmartPhone> smartPhone){
        if(!smartPhone.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iSmartPhoneSV.delete(smartPhone.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete Success"),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSmartPhone(@PathVariable("id") Optional<SmartPhone> smt, @RequestBody SmartPhone smartPhone){
        if(!smt.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        smt.get().setProducer(smartPhone.getProducer());
//        smt.get().setModel(smartPhone.getModel());
//        smt.get().setPrice(smartPhone.getPrice());
//        iSmartPhoneSV.save(smt.get());
        smartPhone.setId(smt.get().getId());
        iSmartPhoneSV.save(smartPhone);
        return new ResponseEntity<>(new ResponseMessage("Update Success"), HttpStatus.OK);
    }
//    cach 1
//    @GetMapping("/search")
//    public ResponseEntity<?> findAllByProducer(@RequestParam("producer") String producer){
//        return new ResponseEntity<>(iSmartPhoneSV.findAllByProducerContaining(producer),HttpStatus.OK);
//    }
//    cach 2
    @GetMapping("/search/{producer}")
    public ResponseEntity<?> findAllByProducer(@PathVariable String producer){
        return new ResponseEntity<>(iSmartPhoneSV.findAllByProducerContaining(producer),HttpStatus.OK);
}
}
