package at.technikumwien;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false, length = 50)
    private String manufacturer;

    @Column(nullable = false, length = 50)
    private String model;

    @Column(nullable = false, length = 50)
    private LocalDate fabricationdate;

    private boolean registered;

    public String getDetails(){
        if(manufacturer == null || manufacturer.isBlank()){
            throw new IllegalArgumentException("manufacturer is null or empty");
        }
        if(model == null || model.isBlank()){
            throw new IllegalArgumentException("model is null or empty");
        }
        return manufacturer + " " + model;
    }

}
