package controller;

import java.time.LocalDateTime;

import dto.SearchRequest;
import repository.LogRepositoryImpl;
import service.LogService;

public class LogController {
    public static void main(String[] args) {
        String inputFile = "data/system_logs.txt";
        String outputFile = "data/filtered_logs.txt";

        SearchRequest request = new SearchRequest();
        // request = null;
        //
        request.setLevel(null);
        request.setMessageKeyword("Piarams: {id=12323}");
        request.setStartTime(LocalDateTime.parse("2023-08-01T00:00:00"));
        request.setEndTime(LocalDateTime.parse("2023-08-30T23:59:59"));
        LogService service = new LogService(new LogRepositoryImpl());
        try {
            service.processLogs(inputFile, outputFile, request);
            System.out.println("Done ! result saved to " + outputFile);
        } catch (Exception e) {
            System.err.println("Processing failed: " + e.getMessage());
        }
    }
}
