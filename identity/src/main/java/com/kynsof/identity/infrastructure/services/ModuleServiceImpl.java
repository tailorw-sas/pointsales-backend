package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.application.query.module.getbyid.ModuleResponse;
import com.kynsof.identity.domain.dto.ModuleDto;
import com.kynsof.identity.domain.dto.moduleDto.ModuleDataDto;
import com.kynsof.identity.domain.dto.moduleDto.ModuleNodeDto;
import com.kynsof.identity.domain.interfaces.service.IModuleService;
import com.kynsof.identity.infrastructure.identity.SystemModule;
import com.kynsof.identity.infrastructure.repository.command.ModuleWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.ModuleReadDataJPARepository;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ModuleServiceImpl implements IModuleService {

    @Autowired
    private ModuleWriteDataJPARepository commandRepository;

    @Autowired
    private ModuleReadDataJPARepository queryRepository;

    @Override
    public void create(ModuleDto object) {
        this.commandRepository.save(new SystemModule(object));
    }

    @Override
    public void update(ModuleDto object) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(object, "Module", "Module DTO cannot be null."));
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(object.getId(), "Module.id", "Module ID cannot be null."));

        SystemModule objectUpdate = this.queryRepository.findById(object.getId())
                .orElseThrow(() -> new BusinessException(DomainErrorMessage.QUALIFICATION_NOT_FOUND, "Module not found."));
        
        objectUpdate.setDescription(object.getDescription() != null ? object.getDescription() : objectUpdate.getDescription());
        objectUpdate.setImage(object.getImage() != null ? object.getImage() : objectUpdate.getImage());
        objectUpdate.setName(object.getName() != null ? object.getName() : objectUpdate.getName());

        this.commandRepository.save(objectUpdate);        
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ModuleDto findById(UUID id) {
        Optional<SystemModule> object = this.queryRepository.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Module not found.");

    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<ModuleResponse> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<SystemModule> data = this.queryRepository.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<SystemModule> data) {
        List<ModuleResponse> patients = new ArrayList<>();
        for (SystemModule o : data.getContent()) {
            patients.add(new ModuleResponse(o.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }


    public List<ModuleNodeDto> buildStructure() {
        // Inicializa tu estructura root aquí.
        List<SystemModule> modules = queryRepository.findAll();
        List<ModuleNodeDto> root = new ArrayList<>();

        for (SystemModule module : modules) {
            ModuleNodeDto moduleNode = new ModuleNodeDto();
            moduleNode.setKey(module.getId().toString());
            moduleNode.setData(new ModuleDataDto(module.getName(), "Module", module.getName()));

            // Asumiendo que puedes acceder a los permisos relacionados de alguna manera desde el módulo
//            List<ModuleNodeDto> permissionsNodes = module.get().stream().map(permission -> {
//                ModuleNodeDto permissionNode = new ModuleNodeDto();
//                permissionNode.setKey(permission.getId().toString());
//                ModuleDataDto permissionData = new ModuleDataDto(permission.getDescription(), "Permission", permission.getCode());
//                permissionNode.setData(permissionData);
//                // No children para permisos
//                return permissionNode;
//            }).collect(Collectors.toList());

        //    moduleNode.setChildren(permissionsNodes); // Agrega los permisos como hijos del módulo

            root.add(moduleNode);
        }

        return root;
    }



}
