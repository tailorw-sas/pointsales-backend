package com.kynsoft.notification.infrastructure.entity.redis;

import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsoft.notification.domain.bean.ImportEmailListRow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;
@Getter
@AllArgsConstructor
@RedisHash(value = "emaillisterror",timeToLive = 3600)
public class ImportEmailListErrorRow {
    @Id
    public String id;
    public int rowNumber;
    @Indexed
    public String importProcessId;
    public List<ErrorField> errorFields;
    public ImportEmailListRow row;
}
