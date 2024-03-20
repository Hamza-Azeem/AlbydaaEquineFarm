package com.albydaa.equinefarm.controller;

import com.albydaa.equinefarm.model.Horse;
import com.albydaa.equinefarm.service.HorseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/horse")
@RequiredArgsConstructor
public class HorseController {
    private final HorseService horseService;

    // basic get insert update delete methods
    @GetMapping("/{id}")
    public Optional<Horse> getHorseWithId(@PathVariable long id){
        return horseService.findHorseById(id);
    }
    @GetMapping
    public List<Horse> getAllHorses(){
        return horseService.findAllHorses();
    }
    @PostMapping
    public Horse createhorse(@RequestBody Horse horse){
        return horseService.addHorse(horse);
    }
    @PutMapping
    public Horse updatehorse(@RequestBody Horse horse){
        return horseService.updateExistingHorse(horse);
    }
    @DeleteMapping("/{id}")
    public void deleteHorseWithId(@PathVariable long id){
        horseService.deleteHorseById(id);
    }
    // ***************************************************************************************
    // customized methods
    @PostMapping("/add-parent")
    public Horse addParentToChildHorse(@RequestParam("childId") long childId,
                                       @RequestParam("parentId") long parentId){
        return horseService.addParentToHorse(childId, parentId);
    }
    @PostMapping("/add-doctor-responsible")
    public Horse addDoctorInChargeToHorse(@RequestParam("doctorId") long doctorId,
                                          @RequestParam("horseId") long horseId){
        return horseService.giveResponsibilityToDoctor(doctorId, horseId);
    }

}
