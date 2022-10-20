package com.project.bloodDonation.dto;


import com.project.bloodDonation.domain.User;
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

}