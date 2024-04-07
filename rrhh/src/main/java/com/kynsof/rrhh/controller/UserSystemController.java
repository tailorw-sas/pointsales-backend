package com.kynsof.rrhh.controller;

import com.kynsof.rrhh.application.command.registerFingerprint.RegisterFingerprintRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class UserSystemController {

    @PostMapping("register-fingerprint")
    public Mono<ResponseEntity<?>> registerFingerprint(@RequestBody RegisterFingerprintRequest request){
        return Mono.just(ResponseEntity.ok("Keycloak with ADMIN CLIENT ROLE"));
    }

    //TODO
    // Dado una cedula devolver el usuario
    // Dado el id de un equipo devolver los usuarios que pertenecen a la empresa del equipo pasado
    // Crear Crud para administrar equipos Servicio(ip,SerieId,businessId)
}
