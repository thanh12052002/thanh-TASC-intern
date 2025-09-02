package repository;

import java.util.List;

import dto.SearchRequest;
import model.LogEntry;

public interface LogRepository {
    List<LogEntry> searchLogs(String filePath, SearchRequest searchRequest) throws Exception;
}
