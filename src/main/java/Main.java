import java.util.List;
import model.Record;
import service.FileReaderService;
import service.ParseDataService;
import service.ReportService;
import service.impl.FileReaderServiceImpl;
import service.impl.ParseDataServiceImpl;
import service.impl.ReportServiceImpl;

public class Main {
    private static final String FROM_FILE = "src/main/resources/data.txt";

    public static void main(String[] args) {
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        List<String> dataFromFile = fileReaderService.read(FROM_FILE);
        ParseDataService parseDataService = new ParseDataServiceImpl();
        List<Record> records = parseDataService.parse(dataFromFile);
        ReportService reportService = new ReportServiceImpl();
        String report = reportService.createReport(records);
        System.out.println(report);
    }
}
