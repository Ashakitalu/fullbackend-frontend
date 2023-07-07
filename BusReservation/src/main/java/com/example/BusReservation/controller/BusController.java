package com.example.BusReservation.controller;


import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BusReservation.Repository.BusRepository;
import com.example.BusReservation.model.Bus;
@RestController
@RequestMapping("/api/v1")


public class BusController {
    @Autowired
//Link the repository and Controller
    public BusRepository busRepository;

    @PostMapping("/addbus")
    public ResponseEntity<?> addbus(@RequestBody Bus bus){
        try{
            Bus bus2 = busRepository.save(bus);
            return new ResponseEntity<>("Entered",HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Not enterede",HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping("/allBus")
    public ResponseEntity<?> allBus(){
        try{
            List<Bus> buslList=busRepository.findAll();
            if(buslList.isEmpty()){
                return new ResponseEntity<>("Emty",HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(buslList,HttpStatus.OK);
            }
        }catch(Exception exception){
            return new ResponseEntity<>("Network Error",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{BusId}")
    public ResponseEntity<?> deleteBusEntity(@PathVariable int BusId){
        try{
            busRepository.deleteById(BusId);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<>("emty data",HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/byID/{id}")
    public ResponseEntity<?> getbyIdEntity(@PathVariable int id){
        try{
            Optional<Bus> optionalBus = busRepository.findById(id);
            if (optionalBus.isPresent()){
                return new ResponseEntity<>(optionalBus,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("No data",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Error",HttpStatus.CONFLICT);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?>updateBus(@PathVariable int id,@RequestBody Bus bus){
        try{
            if (busRepository.findById(id).isPresent()){
                Bus bus2 =busRepository.save(bus);

                bus2.setBusNumber(bus.getBusNumber());
                bus2.setBusCapacity(bus.getBusCapacity());
                bus2.setBusId(bus.getBusId());
                bus2.setBusDriver(bus.getBusDriver());

                Bus updateBus = busRepository.save(bus2);
                return new ResponseEntity<>("updated",HttpStatus.OK);

            }else{
                return new ResponseEntity<>("not updated",HttpStatus.CONFLICT);
            }
        }catch(Exception exception){
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }
    }

    

}
