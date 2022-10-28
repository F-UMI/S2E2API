package com.project.bloodDonation.dto;


import com.project.bloodDonation.domain.BloodDonation;
import java.time.LocalDateTime;
import javax.xml.crypto.KeySelector.Purpose;
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

  public void update(int count, LocalDateTime date, int available_Date) {
    this.blood_Donation_Count = count;
    this.blood_Donation_Date = date;
    this.blood_Donation_Available_Date = available_Date;
  }

  public BloodDonation toEntity(){
    return BloodDonation.builder()
        .id(this.getId())
        .count(this.getBlood_Donation_Count())
        .date(this.getBlood_Donation_Date())
        .availableDate(this.getBlood_Donation_Available_Date())
        .build();
  }



}