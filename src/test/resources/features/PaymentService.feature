Feature: Payment Service Steps

  Scenario: Successfully generate QR Code
    Given a valid PreferenceDTO
    When QR code generation is requested
    Then a valid QR code is generated

  Scenario: Link generation fails with LibException
    Given a valid PreferenceDTO
    And link generation fails with LibException
    Then a FailedDependencyException is thrown

  Scenario: Mercado Pago response is null
    Given a valid PreferenceDTO
    And Mercado Pago response is null
    Then a FailedDependencyException is thrown
