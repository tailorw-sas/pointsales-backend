package com.kynsoft.notification.domain.bean;

import com.kynsof.share.core.application.excel.annotation.Cell;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImportEmailListRow {
   @Cell(position = 0)
   private String email;
   @Cell(position = 1)
   private String clientName;
   @Cell(position = 2)
   private String clientLastname;
}
