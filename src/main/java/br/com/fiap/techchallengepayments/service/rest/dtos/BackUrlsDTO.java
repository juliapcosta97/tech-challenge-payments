package br.com.fiap.techchallengepayments.service.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BackUrlsDTO {

    private String success;
    private String failure;
    private String pending;
}
