package ite.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.security.oauth2.jwt.Jwt;

@RestController
@RequestMapping("/api")
public class ApiController {
    private static final Logger LOG = LoggerFactory.getLogger(ApiController.class);

    @GetMapping(path = "/hello")
    // Declarative access control
    // TODO-4: Define access control according requirement
    public String helloWorld(){
        LOG.info("Processing GET /hello request");
  //      logToken();
        return "Hello World...";
    }

    @GetMapping(path = "/admin/hello")
    // Declarative access control
    // TODO-4: Define access control according requirement
    public String adminHelloWorld(){
        LOG.info("Processing GET /admin/hello request");
//        logToken();
        return "Hello Admin World...";
    }

//    private void logToken() {
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//          Jwt token = (Jwt)((JwtAuthenticationToken) authentication).getPrincipal();
//          LOG.info(token.getTokenValue());
//          LOG.info(token.getClaims().toString());
//        }
//      }
    
}
