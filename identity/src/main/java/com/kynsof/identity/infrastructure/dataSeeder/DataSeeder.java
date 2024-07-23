package com.kynsof.identity.infrastructure.dataSeeder;

import com.kynsof.identity.domain.dto.UserStatus;
import com.kynsof.identity.domain.dto.UserSystemDto;
import com.kynsof.identity.domain.dto.enumType.EBusinessStatus;
import com.kynsof.identity.infrastructure.identity.Business;
import com.kynsof.identity.infrastructure.identity.BusinessModule;
import com.kynsof.identity.infrastructure.identity.ModuleSystem;
import com.kynsof.identity.infrastructure.identity.UserSystem;
import com.kynsof.identity.infrastructure.repository.command.BusinessModuleWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repository.command.BusinessWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repository.command.UserSystemsWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.BusinessModuleReadDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.BusinessReadDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.ModuleReadDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.UserSystemReadDataJPARepository;
import com.kynsof.share.core.domain.EUserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataSeeder implements ApplicationRunner {

    private final UserSystemReadDataJPARepository readRepository;
    private final UserSystemsWriteDataJPARepository writeRepository;

    private final BusinessReadDataJPARepository businessReadDataJPARepository;
    private final BusinessWriteDataJPARepository businessWriteDataJPARepository;

    private final BusinessModuleWriteDataJPARepository businessModuleWriteDataJPARepository;

    private final ModuleReadDataJPARepository moduleReadDataJPARepository;


    @Autowired
    public DataSeeder(UserSystemReadDataJPARepository readRepository, UserSystemsWriteDataJPARepository writeRepository,
                      BusinessReadDataJPARepository businessReadDataJPARepository,
                      BusinessWriteDataJPARepository businessWriteDataJPARepository,
                      BusinessModuleReadDataJPARepository businessModuleReadDataJPARepository,
                      BusinessModuleWriteDataJPARepository businessModuleWriteDataJPARepository,
                      ModuleReadDataJPARepository moduleReadDataJPARepository) {
        this.readRepository = readRepository;
        this.writeRepository = writeRepository;
        this.businessReadDataJPARepository = businessReadDataJPARepository;
        this.businessWriteDataJPARepository = businessWriteDataJPARepository;
        this.businessModuleWriteDataJPARepository = businessModuleWriteDataJPARepository;
        this.moduleReadDataJPARepository = moduleReadDataJPARepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // ID del usuario que deseas verificar y crear si no existe
        UUID userId = UUID.fromString("1a570163-5761-434c-9072-fb3f76bfe501");

        if (!readRepository.existsById(userId)) {
            // Si no existe, crear el usuario
            UserSystemDto userDto = new UserSystemDto();
            userDto.setId(userId);
            userDto.setUserName("admin-user@gmail.com");
            userDto.setEmail("admin-user@gmail.com");
            userDto.setName("ADMIN");
            userDto.setLastName("ADMIN");
            userDto.setStatus(UserStatus.ACTIVE);
            userDto.setUserType(EUserType.SUPER_ADMIN);
            writeRepository.save(new UserSystem(userDto));

            System.out.println("Seeder: Usuario creado con éxito.");
        } else {
            System.out.println("Seeder: El usuario con ID " + userId + " ya existe.");
        }

        if (businessReadDataJPARepository.findAll().isEmpty()) {
            Business business = new Business();
            business.setId(UUID.randomUUID());
            business.setName("KYNSOFT");
            business.setStatus(EBusinessStatus.ACTIVE);
            business.setDescription("KYNSOFT");
            business.setLatitude("");
            business.setLongitude("");
            business.setLogo("");
            business.setRuc("1793211446001");
            businessWriteDataJPARepository.save(business);

            BusinessModule businessModule = new BusinessModule();
            businessModule.setId(UUID.randomUUID());
            businessModule.setBusiness(business);

            ModuleSystem moduleSystem = moduleReadDataJPARepository.findAll().get(0);
            businessModule.setModule(moduleSystem);
            businessModuleWriteDataJPARepository.save(businessModule);
        }
    }
}
