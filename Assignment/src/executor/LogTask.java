package executor;

import java.util.*;
import java.util.concurrent.Callable;

import core.LogMatcher;
import core.LogParser;
import dto.SearchRequest;
import model.LogEntry;

public class LogTask implements Callable<List<LogEntry>> {

    private final List<String> lines;
    private final SearchRequest request;

    public LogTask(List<String> lines, SearchRequest request) {
        this.lines = lines;
        this.request = request;
    }

    @Override
    public List<LogEntry> call() {
        List<LogEntry> result = new ArrayList<>();
        for (String line : lines) {
            try {
                LogEntry entry = LogParser.parse(line);
                if (entry != null && LogMatcher.match(entry, request)) {
                    result.add(entry);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                if (e.getCause() != null) {
                    System.out.println(e);
                }
                //
                e.printStackTrace();
            }
        }
        return result;
    }
}
