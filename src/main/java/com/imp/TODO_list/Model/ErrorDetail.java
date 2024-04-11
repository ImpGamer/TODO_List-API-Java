package com.imp.TODO_list.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {

    private String title;
    private int status;
    private String detail;
    private String developMessage;
}