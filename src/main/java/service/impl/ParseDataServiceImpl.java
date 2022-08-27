package service.impl;

import java.util.ArrayList;
import java.util.List;
import model.PeriodOfTime;
import model.Question;
import model.Record;
import model.Service;
import service.ParseDataService;

public class ParseDataServiceImpl implements ParseDataService {
    private static final int RECORD_TYPE_INDEX = 0;
    private static final int SERVICE_INDEX = 1;
    private static final int QUESTION_INDEX = 2;
    private static final int RESPONSE_TYPE_INDEX = 3;
    private static final int PERIOD_OF_TIME_INDEX = 4;
    private static final int TIME_INDEX = 5;

    @Override
    public List<Record> parse(List<String> dataFromFile) {
        List<Record> records = new ArrayList<>();
        for (int i = 1; i < dataFromFile.size(); i++) {
            String line = dataFromFile.get(i);
            String[] strings = line.split(" ");
            records.add(new Record(Record.RecordType.getByName(strings[RECORD_TYPE_INDEX]),
                        Service.parseFromString(strings[SERVICE_INDEX]),
                        Question.parseFromString(strings[QUESTION_INDEX]),
                        Record.ResponseType.getByName(strings[RESPONSE_TYPE_INDEX]),
                        PeriodOfTime.parseFromString(strings[PERIOD_OF_TIME_INDEX]),
                        strings.length == 6 ? Long.parseLong(strings[TIME_INDEX]) : null));
        }
        return records;
    }
}
