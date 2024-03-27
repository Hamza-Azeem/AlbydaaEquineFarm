package com.albydaa.equinefarm.repostiroy;

import com.albydaa.equinefarm.model.Horse;
import com.albydaa.equinefarm.repository.HorseRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class HorseRepoTest {
    @Autowired
    private HorseRepo horseRepository;

    @Test
    public void HorseRepository_AddParentToHorse_NumOfRows(){
        // Arrange
        Horse h1 = Horse.builder()
                .id(1L)
                .name("horse")
                .price(2401D)
                .breed("Arabian")
                .age(15)
                .build();
        Horse h2 = Horse.builder()
                .id(2L)
                .name("horse")
                .price(2401D)
                .breed("Arabian")
                .age(15)
                .build();
        h1 = horseRepository.save(h1);
        h2 = horseRepository.save(h2);
        // Act
        int result = horseRepository.addParentToHorse(h1.getId(), h2.getId());
        // Arrange
        Assertions.assertThat(result).isEqualTo(1);
    }

}
