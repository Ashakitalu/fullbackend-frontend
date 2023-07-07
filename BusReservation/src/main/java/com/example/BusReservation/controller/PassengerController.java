package com.example.BusReservation.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.BusReservation.Repository.PassengerRepository;
import com.example.BusReservation.model.Passenger;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class PassengerController {

    //link the repository and controller
    @Autowired
    public PassengerRepository passengerRepository;
    
    //API for adding data
    @PostMapping("/addpassenger")
    public ResponseEntity<?> addpassenger(@RequestBody Passenger passenger){
        try{
            Passenger passenger2 =passengerRepository.save(passenger);
            return new ResponseEntity<>("Added",HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Not added",HttpStatus.NOT_MODIFIED);
        }

    }
    @GetMapping("/allPassenger")
    public ResponseEntity<?> getallPassenger(){
        try{
            List<Passenger> passengerList=passengerRepository.findAll();
             if(passengerList.isEmpty()){
                return new ResponseEntity<>("No data found",HttpStatus.NOT_FOUND);
             }else{
                return new ResponseEntity<>(passengerList,HttpStatus.OK);
             }
        }catch(Exception exception){
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        } 
    }
    //delete data by id
    @DeleteMapping("/erase/{PassengerId}")
    public ResponseEntity<?> erasePassenger(@PathVariable int PassengerId){
      try{
        passengerRepository.deleteById(PassengerId);
        return new ResponseEntity<>("Erased",HttpStatus.OK);
      }catch (Exception exception){
        return new ResponseEntity<>("No data present",HttpStatus.CONFLICT);
      }  
    }
    //Get data by Id
    @GetMapping("/getbyId/{PassengerId}")
    public ResponseEntity<?>getbyIdEntity(@PathVariable int PassengerId){
        try{
            Optional<Passenger> optionalPassenger =passengerRepository.findById(PassengerId);
            if(optionalPassenger.isPresent()){
                return new ResponseEntity<>(optionalPassenger,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Empty",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Error",HttpStatus.CONFLICT);
        }
    }
    
    //Update the present data
    @PutMapping("/change/{passengerId}")
    public ResponseEntity<String> updatePassenger(@PathVariable("passengerId") Integer passengerId, @RequestBody Passenger passenger) {
        Optional<Passenger> optionalPassenger = passengerRepository.findById(passengerId);
        if (optionalPassenger.isPresent()) {
            Passenger existingPassenger = optionalPassenger.get();
            existingPassenger.setPassengerAddress(passenger.getPassengerAddress());
            existingPassenger.setPassengerEmail(passenger.getPassengerEmail());
            existingPassenger.setPassengerName(passenger.getPassengerName());
            existingPassenger.setPassengerPhone(passenger.getPassengerPhone());
            passengerRepository.save(existingPassenger);
            return new ResponseEntity<>("Passenger updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Passenger not found", HttpStatus.NOT_FOUND);
        }
    }
    
    // public ResponseEntity<?> updatePassenger(@PathVariable int id,@RequestBody Passenger passenger){
    //     try{
    //         if(passengerRepository.findById(id).isPresent()){
    //             Passenger updatePassenger=passengerRepository.findById(passenger.getPassengerId()).get();
                

    //             updatePassenger.setPassengerAddress(passenger.getPassengerAddress());
    //             updatePassenger.setPassengerEmail(passenger.getPassengerEmail());
    //             updatePassenger.setPassengerName(passenger.getPassengerName());
    //             updatePassenger.setPassengerPhone(passenger.getPassengerPhone());

    //             Passenger updatepassenger= passengerRepository.save(updatePassenger);
    //             return new ResponseEntity<>("changed",HttpStatus.OK);
    //         }else{
    //             return new ResponseEntity<>("Not changed",HttpStatus.CONFLICT);
    //         }
    //     }catch(Exception exception){
    //         return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
    //     }
    // }


    
}
