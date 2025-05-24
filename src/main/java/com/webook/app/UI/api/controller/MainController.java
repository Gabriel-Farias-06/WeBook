import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class MainController {
    @GetMapping("/")
    public String health() {
        return "UP";
    }
}
