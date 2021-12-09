package com.example.demo.controllers;
import com.example.demo.services.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/updater")
public class UpdaterController {
     Updater updater;

     @Autowired
     public UpdaterController(Updater updater) {
         this.updater = updater;
     }

    @GetMapping
    public ResponseEntity<String> updateDataBase() {
        if (!updater.updateDb()) {
            return new ResponseEntity<>("Database could not be updated, because no movies to update were found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Database successfully updated.", HttpStatus.OK);
    }
}
