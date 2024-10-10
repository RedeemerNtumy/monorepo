package com.skillspace.service;

import com.skillspace.model.ChangeLog;
import com.skillspace.model.Program;
import com.skillspace.repository.ChangeLogRepository;
import com.skillspace.repository.ProgramRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProgramService {

    private ProgramRepository programRepository;
    private ChangeLogRepository changeLogRepository;

    public Program createProgram(Program program) {
        program.setDraft(false);
        return programRepository.save(program);
    }

    public List<Program> getAllPrograms() {
        return programRepository.findByIsDraftAndDeletedFalse(false);
    }

    public List<Program> getAllDrafts() {
        return programRepository.findByIsDraftAndDeletedFalse(true);
    }

    public Optional<Program> getProgramById(Long id) {
        return programRepository.findByIdAndDeletedFalse(id);
    }

    @Transactional
    public Optional<Program> updateProgram(Long id, Program programDetails, String updatedBy) {
        Optional<Program> optionalProgram = programRepository.findById(id);
        if (optionalProgram.isPresent()) {
            Program program = optionalProgram.get();

            updateProgramFields(program, programDetails);
            Program updatedProgram = programRepository.save(program);

            if (!program.isDraft()) {
               createChangeLog(program, updatedBy, "Program updated");
            }

            return Optional.of(updatedProgram);
        }
        return Optional.empty();
    }

    public boolean deleteProgram(Long id) {
        Optional<Program> optionalProgram = programRepository.findById(id);
        if (optionalProgram.isPresent()) {
            Program program = optionalProgram.get();
            program.setDeleted(true);
            programRepository.save(program);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<Program> publishProgram(Long id, String publishedBy) {
        Optional<Program> optionalProgram = programRepository.findById(id);
        if (optionalProgram.isPresent()) {
            Program program = optionalProgram.get();
            program.setDraft(false);
            Program publishedProgram = programRepository.save(program);

            createChangeLog(program, publishedBy, "Program published");

            return Optional.of(publishedProgram);
        }
        return Optional.empty();
    }

    private void updateProgramFields(Program program, Program programDetails) {
        program.setName(programDetails.getName());
        program.setDescription(programDetails.getDescription());
        program.setRequirement(programDetails.getRequirement());
        program.setRequiredEarnedBadges(programDetails.getRequiredEarnedBadges());
        program.setAdditionalEarnedBadges(programDetails.getAdditionalEarnedBadges());
        program.setDateOfCommencement(programDetails.getDateOfCommencement());
        program.setDateOfCompletion(programDetails.getDateOfCompletion());
        program.setStatus(programDetails.getStatus());
        program.setCoverImageForProgram(programDetails.getCoverImageForProgram());
    }

    private void createChangeLog(Program program, String changedBy, String changeDescription) {
        ChangeLog changeLog = new ChangeLog();
        changeLog.setProgram(program);
        changeLog.setChangeDate(LocalDateTime.now());
        changeLog.setChangedBy(changedBy);
        changeLog.setChangeDescription(changeDescription);
        changeLogRepository.save(changeLog);
    }

    public List<ChangeLog> getProgramChangeLogs(Long programId) {
        return changeLogRepository.findByProgramIdOrderByChangeDateDesc(programId);
    }

    public List<ChangeLog> getAllChangeLogs() {
        return changeLogRepository.findAllByOrderByChangeDateDesc();
    }
}


