package at.technikumwien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.util.List;

@Configuration
@Profile("test")
public class TestDBInitializer {
    @Autowired
    private CarRepository carRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void handleApplicationEvent(){
        carRepository.saveAll(List.of(
                new Car(
                        null,
                        "Green",
                        "VW",
                        "Golf",
                        LocalDate.of(2010,2,20),
                        false
                ),
                new Car(
                        null,
                        "Yellow",
                        "VW",
                        "Golf",
                        LocalDate.of(2010,2,10),
                        true
                )
        ));
    }
}
