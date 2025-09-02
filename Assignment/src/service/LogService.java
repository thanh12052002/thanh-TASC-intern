package service;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.util.List;

import dto.SearchRequest;
import exception.LogProcessingException;
import model.LogEntry;
import repository.LogRepository;
import util.ValidationUtil;

public class LogService {
    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    //
    public void processLogs(String inputFile, String outputFile, SearchRequest request) {

        try {
            ValidationUtil.validateSearchRequest(request);
            List<LogEntry> logs = logRepository.searchLogs(inputFile, request);
            try (PrintWriter out = new PrintWriter(outputFile)) {
                for (LogEntry entry : logs) {
                    out.println(entry);
                }
            }
            //
            System.out.println("Xu ly thanh cong, ket qua luu tai: " + outputFile);
            //
        } catch (IllegalArgumentException e) {
            System.err.println("Input error: " + e.getMessage());
            throw e;
        } catch (LogProcessingException e) {
            System.err.println("Error khi doc file input: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } catch (FileNotFoundException e) {
            System.err.println("Not open file output: " + outputFile);
        } catch (UncheckedIOException e) {
            System.err.println("Loi khi ghi du lieu ra file: " + outputFile);
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            System.err.println("Da xay ra loi khong xac dinh: " + e.getMessage());
            e.printStackTrace();
            throw new LogProcessingException("Unexpected error in processLogs", e);
        }
    }
}
