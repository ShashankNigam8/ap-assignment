package com.apassignemnt.ap.entity;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String errorId;
    private String errorMessage;
}
