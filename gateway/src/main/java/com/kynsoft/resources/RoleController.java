package com.kynsoft.resources;

import com.kynsoft.service.IKeycloakService;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private IKeycloakService keycloakService;

    @PreAuthorize("permitAll()")
    @GetMapping("")
    public Mono<ResponseEntity<List<RoleRepresentation>>> getALL() {
        List<RoleRepresentation> response = keycloakService.findAllRoles();
        return Mono.just(ResponseEntity.ok(response));
    }

}
