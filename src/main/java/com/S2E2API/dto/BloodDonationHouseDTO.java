package com.S2E2API.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BloodDonationHouseDTO {
  private Long id;
  private String name;
  private double latitude;
  private double longitude;
}
