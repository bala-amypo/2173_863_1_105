package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitorDTO {

    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String idProofNumber;
}
