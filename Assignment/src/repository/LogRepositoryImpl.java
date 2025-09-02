package repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import dto.SearchRequest;
import exception.LogFileReadException;
import exception.LogThreadException;
import model.LogEntry;
import executor.LogTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LogRepositoryImpl implements LogRepository {
    private final ExecutorService executor = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    private final int CHUNK_SIZE = 50000;

    @Override
    public List<LogEntry> searchLogs(String filePath, SearchRequest search) throws Exception {
        ExecutorCompletionService<List<LogEntry>> completionService = new ExecutorCompletionService<>(executor);

        List<String> buffer = new ArrayList<>();
        int taskCount = 0;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.add(line);
                if (buffer.size() == CHUNK_SIZE) {
                    completionService.submit(new LogTask(new ArrayList<>(buffer), search));
                    buffer.clear();
                    taskCount++;
                }
            }
            if (!buffer.isEmpty()) {
                completionService.submit(new LogTask(new ArrayList<>(buffer), search));
                taskCount++;
            }
        } catch (IOException e) {
            throw new LogFileReadException("Loi khi doc file input:" + filePath, e);
        }

        List<LogEntry> finalResults = new ArrayList<>();
        for (int i = 0; i < taskCount; i++) {
            try {
                Future<List<LogEntry>> f = completionService.take(); // lấy task xong đầu tiên
                finalResults.addAll(f.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new LogThreadException("Error Thread", e);
            }
        }
        return finalResults;
    }
}
