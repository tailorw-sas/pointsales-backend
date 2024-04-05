package com.kynsof.identity.application.command.userPermisionBusiness.update;

import com.kynsof.identity.domain.dto.UserPermissionBusinessDto;
import com.kynsof.identity.domain.interfaces.IUserSystemService;
import com.kynsof.identity.domain.interfaces.service.IBusinessService;
import com.kynsof.identity.domain.interfaces.service.IPermissionService;
import com.kynsof.identity.domain.interfaces.service.IUserPermissionBusinessService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserPermissionBusinessCommandHandler implements ICommandHandler<UpdateUserPermissionBusinessCommand> {

    private final IUserPermissionBusinessService service;

    private final IPermissionService permissionService;

    private final IBusinessService businessService;

    private final IUserSystemService userSystemService;

    public UpdateUserPermissionBusinessCommandHandler(IUserPermissionBusinessService service,
                                                      IPermissionService permissionService,
                                                      IBusinessService businessService,
                                                      IUserSystemService userSystemService) {
        this.service = service;
        this.permissionService = permissionService;
        this.businessService = businessService;
        this.userSystemService = userSystemService;
    }

    @Override
    public void handle(UpdateUserPermissionBusinessCommand command) {
//        for (UserRoleBusinessUpdateRequest updateRequest : request.getPayload()) {
//            // Validar y recuperar las entidades involucradas
//            UserSystemDto userSystemDto = userSystemService.findById(updateRequest.getUserId());
//            BusinessDto businessDto = businessService.findById(updateRequest.getBusinessId());
//
//            // Recuperar el estado actual
//            Set<UserPermissionBusiness> currentPermissions = service.findByUserAndBusiness(userSystemDto.getId(), businessDto.getId());
//
//            Set<UUID> newPermissionIds = new HashSet<>(updateRequest.getPermissionIds());
//            Set<UUID> currentPermissionIds = currentPermissions.stream()
//                    .map(permission -> permission.getPermission().getId())
//                    .collect(Collectors.toSet());
//
//            // Determinar cambios necesarios
//            Set<UUID> permissionsToAdd = new HashSet<>(newPermissionIds);
//            permissionsToAdd.removeAll(currentPermissionIds);
//
//            Set<UUID> permissionsToRemove = new HashSet<>(currentPermissionIds);
//            permissionsToRemove.removeAll(newPermissionIds);
//
//            // Ejecutar cambios
//            for (UUID permissionIdToAdd : permissionsToAdd) {
//                PermissionDto permissionDto = permissionService.findById(permissionIdToAdd);
//                UserPermissionBusiness newUserPermissionBusiness = new UserPermissionBusiness();
//                // Aquí se asume la existencia de constructores o métodos adecuados para convertir DTOs a entidades
//                newUserPermissionBusiness.setUser(new UserSystem(userSystemDto)); // Ajustar según tu implementación
//                newUserPermissionBusiness.setPermission(new Permission(permissionDto)); // Ajustar según tu implementación
//                newUserPermissionBusiness.setBusiness(new Business(businessDto)); // Ajustar según tu implementación
//                userPermissionBusinessRepository.save(newUserPermissionBusiness);
//            }
//
//            for (UUID permissionIdToRemove : permissionsToRemove) {
//                UserPermissionBusiness permissionToRemove = currentPermissions.stream()
//                        .filter(p -> p.getPermission().getId().equals(permissionIdToRemove))
//                        .findFirst()
//                        .orElse(null);
//                if (permissionToRemove != null) {
//                    userPermissionBusinessRepository.delete(permissionToRemove);
//                }
//            }
//        }
    }


    private boolean validate(UserPermissionBusinessDto payloadUpdate, UserPermissionBusinessDto toUpdate) {
        return !(payloadUpdate.getBusiness().getId().equals(toUpdate.getBusiness().getId()) &&
                payloadUpdate.getPermission().getId().equals(toUpdate.getPermission().getId()) &&
                payloadUpdate.getUser().getId().equals(toUpdate.getUser().getId()));
    }
}