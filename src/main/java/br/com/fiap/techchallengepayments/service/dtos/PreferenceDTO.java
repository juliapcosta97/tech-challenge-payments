package br.com.fiap.techchallengepayments.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreferenceDTO {

    private String description;
    private double amount;
    private String currency;
}
