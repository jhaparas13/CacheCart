package com.paras.CacheCart.DTO;

import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ApiResponse<T> implements Serializable{
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timeStamp;
}
