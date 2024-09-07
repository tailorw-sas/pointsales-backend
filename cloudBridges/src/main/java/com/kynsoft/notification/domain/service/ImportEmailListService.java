package com.kynsoft.notification.domain.service;

import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsoft.notification.application.command.emaillist.importEmailList.ImportEmailListRequest;
import com.kynsoft.notification.application.query.emaillist.importError.ImportEmailListErrorRequest;
import com.kynsoft.notification.application.query.emaillist.processStatus.ImportEmailListProcessStatusRequest;
import com.kynsoft.notification.application.query.emaillist.processStatus.ImportEmailListProcessStatusResponse;

public interface ImportEmailListService {

   void importEmailList(ImportEmailListRequest request);

   PaginatedResponse getImportEmailListErrors(ImportEmailListErrorRequest request);

   ImportEmailListProcessStatusResponse getImportEmailListProcessStatus(ImportEmailListProcessStatusRequest request);
}
