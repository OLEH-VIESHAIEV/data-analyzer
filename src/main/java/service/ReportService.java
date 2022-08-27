package service;

import java.util.List;
import model.Record;

public interface ReportService {
    String createReport(List<Record> records);
}
