package indv.bozgur.spring.security.base.web.rest;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class HelloResource {

    @ApiOperation(value = "This method is used to test jwt authentication.")
    @PostMapping("/hello")
    public ResponseEntity<String> createCustomer() {
        return ResponseEntity.ok("Greeting from authenticated resource.");
    }
}
