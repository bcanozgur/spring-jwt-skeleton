package indv.bozgur.spring.security.base.web.rest;
import indv.bozgur.spring.security.base.model.AuthRequest;
import indv.bozgur.spring.security.base.service.CustomUserDetailsService;
import indv.bozgur.spring.security.base.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationResource {

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService userDetailsService;

    public AuthenticationResource(JwtUtil jwtUtil, AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @ApiOperation(value = "This method is used to get jwt token.")
    @PostMapping("/login")
    public String creteToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorret username or password.", ex);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        return jwtUtil.generateToken(userDetails);
    }
}
