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

import com.example.BusReservation.Repository.ReservationRepository;
import com.example.BusReservation.model.Reservation;

@RestController
@RequestMapping("/api/v1")
public class ReservationController {

    @Autowired

    public ReservationRepository reservationRepository;

    @PostMapping("/addReserv")
    public ResponseEntity<?> addReserva (@RequestBody Reservation reservation){
        try{
            Reservation reservation2=reservationRepository.save(reservation);
            return new ResponseEntity<>("added",HttpStatus.OK);
        }catch (Exception exception){
            return  new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/allReservation")
    public ResponseEntity<?> getReserva(){
        try{
            List<Reservation> reservationList=reservationRepository.findAll();
            if (reservationList.isEmpty()){
                return new ResponseEntity<>("None",HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(reservationList,HttpStatus.OK);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/ReservbyId/{ReservationID}")
    public ResponseEntity<?> getbyIdEntity(@PathVariable int ReservationID){
        try{
            Optional<Reservation> optionalReservation = reservationRepository.findById(ReservationID);
            if (optionalReservation.isPresent()){
                return new ResponseEntity<>(optionalReservation,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Hakuna",HttpStatus.NOT_FOUND);
            }
        }catch(Exception exception){
            return new ResponseEntity<>("problem",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteentity/{ReservationId}")
    public ResponseEntity<?> deleteRoute(@PathVariable int ReservationId){
        try{
            reservationRepository.deleteById(ReservationId);
            return new ResponseEntity<>("data deleted",HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<>("absent",HttpStatus.CONFLICT);
        }
    }
    @PutMapping("/makechanges/{ReservationId}")
    public ResponseEntity<?> changeReserv(@PathVariable int ReservationId,@RequestBody Reservation reservation){
        try {
            if(reservationRepository.findById(ReservationId).isPresent()){
                Reservation reservation2=reservationRepository.save(reservation);

                reservation2.setReservationDate(reservation.getReservationDate());
                reservation2.setReservationId(reservation.getReservationId());
                reservation2.setReservationTime(reservation.getReservationTime());
                reservation2.setSeatNumber(reservation.getSeatNumber());

                Reservation updateReservation=reservationRepository.save(reservation);
                return new ResponseEntity<>("updated",HttpStatus.OK);
            }else{
                return new ResponseEntity<>("not updated",HttpStatus.CONFLICT);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }
    }
}
