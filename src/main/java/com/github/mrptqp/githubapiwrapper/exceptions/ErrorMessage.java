package com.github.mrptqp.githubapiwrapper.exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class ErrorMessage {

    private final LocalDateTime timestamp;

    private final String message;

    private final int status;

    private final String errorMessage;
}
