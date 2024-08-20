package com.kynsof.share.core.application.excel;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class CellInfo {
    private int position;
    private CustomCellType cellType;
    private String headerName;
}
