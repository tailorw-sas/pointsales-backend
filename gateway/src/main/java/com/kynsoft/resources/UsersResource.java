package com.kynsoft.resources;


import com.kynsoft.dto.LoginDTO;
import com.kynsoft.dto.RegisterDTO;
import com.kynsoft.service.IKeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('ADMIN_CLIENT')")
public class UsersResource {

    @Autowired
    private IKeycloakService keycloakService;


    @PreAuthorize("permitAll()")
    @PostMapping("/authenticate")
    public Mono<String> loginUser(@RequestBody LoginDTO loginDTO) throws URISyntaxException {
    	return keycloakService.authenticate(loginDTO);
               
    }
    
    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public Mono<ResponseEntity<?>> registerUser(@RequestBody RegisterDTO registerDTO) throws URISyntaxException {
        String response = keycloakService.registerUser(registerDTO);
        return Mono.justOrEmpty(ResponseEntity.created(new URI("/users/register")).body(response));
    }
    
    @GetMapping("/list")
    public Mono<ResponseEntity<?>> findAllUsers(){
        return Mono.justOrEmpty(ResponseEntity.ok(keycloakService.findAllUsers()));
    }


    @GetMapping("/find/{username}")
    public Mono<ResponseEntity<?>> searchUserByUsername(@PathVariable String username){
         return Mono.justOrEmpty(ResponseEntity.ok(keycloakService.searchUserByUsername(username)));
    }

    @GetMapping("/count")
    public Mono<ResponseEntity<?>> count(){
         return Mono.justOrEmpty(ResponseEntity.ok(keycloakService.countUsers()));
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<?>> updateUser(@PathVariable String id, @RequestBody RegisterDTO registerDTO){
        keycloakService.updateUser(id, registerDTO);
        return Mono.justOrEmpty(ResponseEntity.ok("User updated successfully"));
    }


    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<?>> deleteUser(@PathVariable String id){
        keycloakService.deleteUser(id);
        return Mono.justOrEmpty(ResponseEntity.ok("User deleted successfully"));
    }
}
