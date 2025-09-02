package core;

import dto.SearchRequest;
import model.LogEntry;

public class LogMatcher {
    public static boolean match(LogEntry entry, SearchRequest request) {
        boolean hasCondition = false;

        if (request.getLevel() != null) {
            hasCondition = true;
            if (!request.getLevel().equalsIgnoreCase(entry.getLevel())) {
                return false;
            }
        }

        if (request.getMessageKeyword() != null) {
            hasCondition = true;
            if (!entry.getMessage().contains(request.getMessageKeyword())) {
                return false;
            }
        }

        if (request.getStartTime() != null) {
            hasCondition = true;
            if (entry.getTimestamp().isBefore(request.getStartTime())) {
                return false;
            }
        }

        if (request.getEndTime() != null) {
            hasCondition = true;
            if (entry.getTimestamp().isAfter(request.getEndTime())) {
                return false;
            }
        }

        // Nếu không có điều kiện nào -> không match
        return hasCondition;
    }

}
