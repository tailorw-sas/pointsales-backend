package com.kynsoft.gateway.controller;

import com.kynsoft.gateway.application.dto.role.RoleRequest;
import com.kynsoft.gateway.application.service.IKeycloakService;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/role")
@PreAuthorize("hasRole('ADMIN_CLIENT')")
public class RoleController {
    @Autowired
    private IKeycloakService keycloakService;

    @PreAuthorize("permitAll()")
    @GetMapping("")
    public Mono<ResponseEntity<List<RoleRepresentation>>> getALL() {
        List<RoleRepresentation> response = keycloakService.findAllRoles();
        return Mono.just(ResponseEntity.ok(response));
    }

    @PostMapping("")
    public Mono<ResponseEntity<?>> createRole(@RequestBody RoleRequest registerDTO) throws URISyntaxException {
        String response = keycloakService.createRole(registerDTO);
        return Mono.justOrEmpty(ResponseEntity.created(new URI("/role")).body(response));
    }

}
