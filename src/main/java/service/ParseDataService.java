package service;

import java.util.List;
import model.Record;

public interface ParseDataService {
    List<Record> parse(List<String> dataFromFile);
}
