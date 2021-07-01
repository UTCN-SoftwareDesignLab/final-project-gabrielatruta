package com.finalsdproject.report;

import java.io.ByteArrayOutputStream;

public interface ReportService {

    ByteArrayOutputStream export();
    ReportType getType();

}
