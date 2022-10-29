package com.S2E2API.dto;


import com.S2E2API.domain.BloodDonation;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BloodDonationDTO {

  private Long id;
  private int blood_Donation_Count;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime blood_Donation_Date;
  private int blood_Donation_Available_Date;


  public BloodDonationDTO(BloodDonation donation) {
    this.id = donation.getId();
    this.blood_Donation_Count = donation.getCount();
    this.blood_Donation_Date = donation.getDate();
    this.blood_Donation_Available_Date = donation.getAvailableDate();
  }
}