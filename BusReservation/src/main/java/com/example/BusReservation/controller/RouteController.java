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

import com.example.BusReservation.Repository.RouteRepository;
import com.example.BusReservation.model.Route;

@RestController
@RequestMapping("/api/v1")
public class RouteController {
    //connecting repository and controller
    @Autowired
    public RouteRepository routeRepository;

    @PostMapping("/addRoute")
    public ResponseEntity<?> addRoute(@RequestBody Route route){
        try{
            Route rroute2=routeRepository.save(route);
            return new ResponseEntity<>("data imeongezwa",HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<>("Data haijaongezwa",HttpStatus.NOT_MODIFIED);
        }
    }
    @GetMapping("/allRoute")
    public ResponseEntity<?> getRoute(){
        try{
            List<Route> routeList=routeRepository.findAll();
            if (routeList.isEmpty()){
                return new ResponseEntity<>("Empty",HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(routeList,HttpStatus.OK);
            }
        }catch(Exception exception){
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deletedata/{RouteId}")
    public ResponseEntity<?> deleteRoute(@PathVariable int RouteId){
        try{
            routeRepository.deleteById(RouteId);
            return new ResponseEntity<>("data deleted",HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<>("absent",HttpStatus.CONFLICT);
        }
    }
    @PutMapping("/reverse/{RouteId}")
    public ResponseEntity<?> reverseRoute(@PathVariable int RouteId,@RequestBody Route route){
        try{
            if(routeRepository.findById(RouteId).isPresent()){
                Route route2=routeRepository.save(route);

                route2.setRouteId(route.getRouteId());
                route2.setTravelDate(route.getTravelDate());
                route2.setPrice(route.getPrice());
                route2.setOrigin(route.getOrigin());
                route.setTravelTime(route.getTravelTime());
                route2.setDestination(route.getDestination());
                
                Route updateRoute= routeRepository.save(route);
                return new ResponseEntity<>("updated",HttpStatus.OK);
            }else{
                return new ResponseEntity<>("not updated",HttpStatus.CONFLICT);

            }
        }catch (Exception exception){
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/takebyId/{RouteId}")
    public ResponseEntity<?>takebyIdEntity(@PathVariable int RouteId){
        try{
            Optional<Route> optionalRoute =routeRepository.findById(RouteId);
            if(optionalRoute.isPresent()){
                return new ResponseEntity<>(optionalRoute,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Empty",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Error",HttpStatus.CONFLICT);
        }
    }
    
}
