package com.albydaa.equinefarm.service;

import com.albydaa.equinefarm.dtos.DoctorDTO;
import com.albydaa.equinefarm.dtos.HorseDTO;
import com.albydaa.equinefarm.model.Doctor;
import com.albydaa.equinefarm.model.Horse;
import com.albydaa.equinefarm.repository.DoctorRepo;
import com.albydaa.equinefarm.repository.HorseRepo;
import com.albydaa.equinefarm.service.impl.DoctorServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.print.Doc;
import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {
    @Mock
    private DoctorRepo doctorRepository;
    @Mock
    private HorseRepo horseRepo;
    @InjectMocks
    private DoctorServiceImpl doctorService;
    private Doctor d1;
    private Doctor d2;
    private Horse horse;

    @BeforeEach
    public void init(){
        // Arrange
        d1 = Doctor.builder()
                .id(1L)
                .firstName("test1")
                .build();
        d2 = Doctor.builder()
                .id(2L)
                .firstName("test2")
                .build();
        horse = Mockito.mock(Horse.class);
    }

    @Test
    public void DoctorService_FindAllBySpecialization_ReturnDoctorDTOList(){
        // Arrange
        when(doctorRepository.findDoctorBySpecialization(Doctor.Specialization.SURGEON))
                .thenReturn(Arrays.asList(d1, d2));
        // Act
        List<DoctorDTO> result = doctorService.findAllDoctorsWithSpecialization(Doctor.Specialization.SURGEON);
        // Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
    @Test
    public void DoctorService_FindAllBySpecialization_ReturnDoctorEmptyList(){
        // Arrange
        when(doctorRepository.findDoctorBySpecialization(Doctor.Specialization.TECHNICIAN))
                .thenReturn(Collections.emptyList());
        // Act
        List<DoctorDTO> result = doctorService.findAllDoctorsWithSpecialization(Doctor.Specialization.TECHNICIAN);
        // Assert
        Assertions.assertThat(result).isEmpty();
        Assertions.assertThat(result.size()).isEqualTo(0);
    }
    @Test
    public void DoctorService_findHorsesManagedByDoctor_ReturnHorseDTOList(){
        // Arrange
        Set<Horse> horseSet = new HashSet<>();
        horseSet.add(horse);
        d1.setManagedHorses(horseSet);
        doctorRepository.save(d1);
        when(doctorRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(d1));
        // Act
        List<HorseDTO> result = doctorService.findAllHorsesManagedByDoctor(d1.getId());
        // Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(horseSet.size());
    }
    @Test
    public void DoctorService_findHorsesManagedByDoctor_ReturnEmptyList(){
        // Arrange
        when(doctorRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(d1));
        // Act
        List<HorseDTO> result = doctorService.findAllHorsesManagedByDoctor(d1.getId());
        // Assert
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void DoctorService_FindAll_ReturnDoctorDTOList(){
        // Arrange
        when(doctorRepository.findAll()).thenReturn(Arrays.asList(d1,d2));
        // Act
        List<DoctorDTO> result = doctorService.findAllDoctors();
        // Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
    @Test
    public void DoctorService_FindById_ReturnDoctorDTO(){
        // Arrange
        when(doctorRepository.findById(1L)).thenReturn(Optional.ofNullable(d1));
        // Act
        DoctorDTO result = doctorService.findDoctorById(1L);
        // Assert
        Assertions.assertThat(result.getFirstName()).isEqualTo(d1.getFirstName());
        Assertions.assertThat(result.getId()).isEqualTo(d1.getId());
    }
    @Test
    public void DoctorService_Save_ReturnDoctorDTO(){
        // Arrange
        DoctorDTO doctorDTO = DoctorDTO.builder()
                .firstName("sub")
                .salary(500D)
                .build();
        when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(d1);
        // Act
        DoctorDTO result = doctorService.saveDoctor(doctorDTO);
        // Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(1L);
        Assertions.assertThat(result.getFirstName()).isEqualTo(d1.getFirstName());
    }
    @Test
    public void DoctorService_UpdateDoctor_ReturnDoctorDTO(){
        // Arrange
        DoctorDTO doctorDTO = DoctorDTO.builder()
                .id(1L)
                .firstName("sub")
                .salary(500D)
                .build();
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(d1));
        when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(d1);
        // Act
        DoctorDTO result = doctorService.updateDoctor(doctorDTO);
        //Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(d1.getId());
    }

}
