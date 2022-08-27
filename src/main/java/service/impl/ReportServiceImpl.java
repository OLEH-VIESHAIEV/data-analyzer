package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import model.Record;
import service.ReportService;

public class ReportServiceImpl implements ReportService {
    @Override
    public String createReport(List<Record> records) {
        StringBuilder builder = new StringBuilder();
        List<Record> waitingTimeRecords = new ArrayList<>();
        for (Record record : records) {
            if (record.getRecordType() == Record.RecordType.WAITING_TIME) {
                waitingTimeRecords.add(record);
            } else {
                Integer averageWaitingTime
                        = calculateAverageWaitingTime(waitingTimeRecords, record);
                if (averageWaitingTime == null) {
                    builder.append("-").append(System.lineSeparator());
                    continue;
                }
                builder.append(averageWaitingTime).append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    private Integer calculateAverageWaitingTime(List<Record> waitingTimeRecords, Record query) {
        List<Record> matchingWaitingTimeRecords
                = getMatchingWaitingTimeRecords(waitingTimeRecords, query);
        if (matchingWaitingTimeRecords.isEmpty()) {
            return null;
        }
        int sum = matchingWaitingTimeRecords
                .stream()
                .mapToInt(record -> Math.toIntExact(record.getTime()))
                .sum();
        return sum / matchingWaitingTimeRecords.size();
    }

    private List<Record> getMatchingWaitingTimeRecords(List<Record> waitingTimeRecords,
                                                       Record query) {
        Objects.requireNonNull(query);
        return waitingTimeRecords
                .stream()
                .filter(query::matches)
                .collect(Collectors.toList());
    }
}
