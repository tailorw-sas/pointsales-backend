package com.kynsoft.notification.infrastructure.service;

import com.kynsof.share.utils.ConfigureTimeZone;
import com.kynsoft.notification.domain.dto.AFileDto;
import com.kynsoft.notification.domain.service.IAFileService;
import com.kynsoft.notification.infrastructure.entity.AFile;
import com.kynsoft.notification.infrastructure.repository.command.FileWriteDataJPARepository;
import com.kynsoft.notification.infrastructure.repository.query.FileReadDataJPARepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AFileService implements IAFileService {

    @Autowired
    private FileWriteDataJPARepository commandRepository;

    @Autowired
    private FileReadDataJPARepository queryRepository;

    @Override
    public void create(AFileDto object) {
        object.setCreateAt(ConfigureTimeZone.getTimeZone());
        this.commandRepository.save(new AFile(object));
    }

    @Override
    public void update(AFileDto object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AFileDto findById(UUID id) {
        Optional<AFile> file = this.queryRepository.findById(id);
        if (file.isPresent()) {
            return file.get().toAggregate();
        }
        return null;
    }

}
