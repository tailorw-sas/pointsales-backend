package com.kynsoft.notification.infrastructure.service;

import com.kynsof.share.utils.ConfigureTimeZone;
import com.kynsoft.notification.domain.dto.AFileDto;
import com.kynsoft.notification.domain.service.IAFileService;
import com.kynsoft.notification.infrastructure.entity.AFile;
import com.kynsoft.notification.infrastructure.repository.command.FileWriteDataJPARepository;
import com.kynsoft.notification.infrastructure.repository.query.FileReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AFileServiceImpl implements IAFileService {

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
        this.commandRepository.deleteById(id);
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
