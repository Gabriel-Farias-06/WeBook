@RestController
public class MainController {
    @GetMapping("/")
    public String health() {
        return "UP";
    }
}
