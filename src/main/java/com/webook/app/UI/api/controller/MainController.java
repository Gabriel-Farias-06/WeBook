@RestController
public class HealthController {
    @GetMapping("/")
    public String health() {
        return "UP";
    }
}
