package com.uc.ms_security.dto_permission;

import lombok.Value;

@Value
public class PermissionResponseDTO {
    Long id;
    String url;
    String method;
    String model;
}