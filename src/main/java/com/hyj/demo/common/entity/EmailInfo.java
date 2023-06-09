package com.hyj.demo.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailInfo {
    private String emailAddress;
    private String username;
    private String subject;
    private String soeid;
}
