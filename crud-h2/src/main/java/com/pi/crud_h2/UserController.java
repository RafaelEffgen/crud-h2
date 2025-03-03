package com.pi.crud_h2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository repository;

    @PostMapping
    public ResponseEntity<Costumer> register (@RequestBody Costumer data){
        Costumer registeredData = repository.save(data);
        return ResponseEntity.status(201).body(registeredData);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Costumer> searchById(@PathVariable Integer id){
        return ResponseEntity.of(repository.findById(id));
    }

    @GetMapping
    private ResponseEntity<List<Costumer>> returnAllUsers(){
        return ResponseEntity.status(200).body(repository.findAll());
    }

    @PutMapping("/{id}")
    private ResponseEntity<Costumer> update (@PathVariable Integer id, @RequestBody Costumer dataToChanged){
        Optional<Costumer>possibleUser = repository.findById(id);
        if(repository.existsById(id)){
            dataToChanged.setId(id);
            Costumer changedData = repository.save(dataToChanged);
            return ResponseEntity.status(200).body(changedData);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Costumer> delete (@PathVariable Integer id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }
}
