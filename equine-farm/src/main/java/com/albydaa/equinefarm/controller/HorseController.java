package com.albydaa.equinefarm.controller;

import com.albydaa.equinefarm.dtos.HorseDTO;
import com.albydaa.equinefarm.model.Horse;
import com.albydaa.equinefarm.service.HorseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/horse")
@RequiredArgsConstructor
public class HorseController {
    private final HorseService horseService;

    // basic get insert update delete methods
    @GetMapping("/{id}")
    public HorseDTO getHorseWithId(@PathVariable long id){
        return horseService.findHorseById(id);
    }
    @GetMapping
    public List<HorseDTO> getAllHorses(){
        return horseService.findAllHorses();
    }
    @PostMapping
    public HorseDTO createHorse(@RequestBody HorseDTO horseDTO){
        return horseService.saveHorse(horseDTO);
    }
    @PutMapping
    public HorseDTO updateHorse(@RequestBody HorseDTO horseDTO){
        return horseService.saveHorse(horseDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteHorseWithId(@PathVariable long id){
        horseService.deleteObjectById(id);
    }
    // ***************************************************************************************
    // customized methods
    @PostMapping("/add-parent")
    public HorseDTO addParentToChildHorse(@RequestParam("childId") long childId,
                                       @RequestParam("parentId") long parentId){
        return horseService.addParentToHorse(childId, parentId);
    }
    @PostMapping("/add-doctor-responsible")
    public HorseDTO addDoctorInChargeToHorse(@RequestParam("doctorId") long doctorId,
                                             @RequestParam("horseId") long horseId){
        return horseService.giveResponsibilityToDoctor(doctorId, horseId);
    }
    @GetMapping("/{id}/children")
    public List<HorseDTO> getChildrenOfHorse(@PathVariable long id){
        return horseService.findChildrenOfHorse(id);
    }


}
