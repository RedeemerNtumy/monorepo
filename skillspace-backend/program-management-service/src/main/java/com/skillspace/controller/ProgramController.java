package com.skillspace.controller;

import com.skillspace.model.ChangeLog;
import com.skillspace.model.Program;
import com.skillspace.service.ProgramService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
@AllArgsConstructor
class ProgramController {

    private ProgramService programService;

    @PostMapping("/create")
    public ResponseEntity<Program> createProgram(@RequestBody Program program) {
        Program savedProgram = programService.createProgram(program);
        return ResponseEntity.ok(savedProgram);
    }

    @GetMapping
    public List<Program> getAllPrograms() {
        return programService.getAllPrograms();
    }

    @GetMapping("/changelogs")
    public ResponseEntity<List<ChangeLog>> getAllChangeLogs() {
        List<ChangeLog> changeLogs = programService.getAllChangeLogs();
        if (changeLogs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(changeLogs);
    }

    @GetMapping("/drafts")
    public List<Program> getAllDrafts() {
        return programService.getAllDrafts();
    }

    @GetMapping("/{programId}")
    public ResponseEntity<Program> getProgramById(@PathVariable Long programId) {
        return programService.getProgramById(programId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{programId}/changelogs")
    public ResponseEntity<List<ChangeLog>> getProgramChangeLogs(@PathVariable Long programId) {
        List<ChangeLog> changeLogs = programService.getProgramChangeLogs(programId);
        if (changeLogs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(changeLogs);
    }

    @PutMapping("/{programId}")
    public ResponseEntity<Program> updateProgram(@PathVariable Long programId,
                                                 @RequestBody Program programDetails,
                                                 @RequestParam String updatedBy) {
        return programService.updateProgram(programId, programDetails, updatedBy)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{programId}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long programId) {
        if (programService.deleteProgram(programId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{programId}/publish")
    public ResponseEntity<Program> publishProgram(
            @PathVariable Long programId,
            @RequestParam String publishedBy) {

        return programService.publishProgram(programId, publishedBy)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

