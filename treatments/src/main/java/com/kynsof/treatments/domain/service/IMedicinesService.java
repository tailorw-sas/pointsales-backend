package com.kynsof.treatments.domain.service;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.domain.dto.MedicinesDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface IMedicinesService {

    void create(MedicinesDto medicines);

    void update(MedicinesDto medicines);

    MedicinesDto findById(UUID id);

    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);

}