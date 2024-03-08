package com.kynsoft.gateway.controller;


import com.kynsoft.gateway.application.dto.RegisterDTO;
import com.kynsoft.gateway.application.dto.user.ChangeStatusRequest;
import com.kynsoft.gateway.domain.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('ADMIN_CLIENT')")
public class UsersController {

    private final IUserService userService;

    @Autowired
    public UsersController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public Mono<ResponseEntity<?>> findAllUsers() {
        return Mono.justOrEmpty(ResponseEntity.ok(userService.findAllUsers()));
    }


    @GetMapping("/find/{username}")
    public Mono<ResponseEntity<?>> searchUserByUsername(@PathVariable String username) {
        return Mono.justOrEmpty(ResponseEntity.ok(userService.searchUserByUsername(username)));
    }

    @PostMapping("/change_status")
    public Mono<ResponseEntity<?>> changeStatus(@RequestBody ChangeStatusRequest request) {
        userService.changeStatus(request.getUserId(), request.getStatus());
        return Mono.justOrEmpty(ResponseEntity.ok("User updated successfully"));
    }

    @PatchMapping("/update/{id}")
    public Mono<ResponseEntity<?>> updateUser(@PathVariable String id, @RequestBody RegisterDTO registerDTO) {
        userService.updateUser(id, registerDTO);
        return Mono.justOrEmpty(ResponseEntity.ok("User updated successfully"));
    }


    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<?>> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return Mono.justOrEmpty(ResponseEntity.ok("User deleted successfully"));
    }
}
