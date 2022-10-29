package com.project.bloodDonation.dto;

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
  private double longitude;
  private double latitude;
}
