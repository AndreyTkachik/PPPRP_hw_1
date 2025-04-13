package sbertech.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sbertech.app.components.AppValues;
import sbertech.app.dto.LogRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final AppValues appValues;

    @GetMapping("/")
    public String welcomePhrase() {
        return appValues.getWelcomeMessage();
    }

    @GetMapping("/status")
    public ResponseEntity<?> status() {
        return ResponseEntity.ok().body("{\"status\":\"ok\"}");
    }

    @PostMapping("/log")
    public ResponseEntity<?> log(@RequestBody LogRequest request) {
        try {
            Path logFilePath = Paths.get(appValues.getFilePath());
            Files.createDirectories(logFilePath.getParent());
            Files.write(logFilePath, (request.getMessage() + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            return ResponseEntity.ok().body("{\"status\":\"ok\"}");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed to write in log file");
        }
    }

    @GetMapping("/logs")
    public ResponseEntity<?> logs() {
        try {
            Path logFilePath = Paths.get(appValues.getFilePath());
            if (Files.exists(logFilePath)) {
                List<String> lines = Files.readAllLines(logFilePath);
                return ResponseEntity.ok().body(lines);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no log file");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed to read log file");
        }
    }
}
