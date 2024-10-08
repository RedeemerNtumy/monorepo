package com.dennis.user_service.dto;

import com.dennis.user_service.model.Status;
import lombok.Data;

@Data
public class AdminApprovalRequest {
    private String email;
    private Status status;
}
