package at.technikumwien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(method = RequestMethod.GET, value = "resources/cars")
public class CarResource {
    @Autowired
    private CarRepository carRepository;

    @GetMapping("/{id}") //id nach welcher sucht werden soll in der DB
    public Car retrieve(
            @PathVariable long id
    ){
        return carRepository.findById(id).get();
    }


    @GetMapping
    public List<Car> retrieveAll(@RequestParam(defaultValue = "false") boolean all){
        if(all){
            return carRepository.findAll();
        }
        else {
            return carRepository.findAllByRegisteredTrue();
        }
    }
}
