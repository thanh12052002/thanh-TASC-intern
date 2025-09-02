package core;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.LogEntry;

public class LogParser {
    private static final Pattern PATTERN = Pattern.compile("^\\[(.+?)] \\[(.+?)] \\[(.+?)]- (.*)$");

    public static LogEntry parse(String line) {
        Matcher matcher = PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Log format invalid: " + line);
        try {
            return new LogEntry(
                    LocalDateTime.parse(matcher.group(1)),
                    matcher.group(2),
                    matcher.group(3),
                    matcher.group(4));
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse log line: " + line, e);
        }
    }
}
