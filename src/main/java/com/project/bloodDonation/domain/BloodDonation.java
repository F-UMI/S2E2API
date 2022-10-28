package com.project.bloodDonation.domain;

import com.project.bloodDonation.dto.BloodDonationDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class BloodDonation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "blood_Donation_Count", nullable=false)
  private int count;

  @Column(name = "blood_Donation_Date", nullable = false)
  private LocalDateTime date;

  @Column(name = "blood_Donation_Available_Date", nullable=false)
  private int availableDate;

  public void update(int count, LocalDateTime date, int availableDate) {
    this.count = count;
    this.date = date;
    this.availableDate = availableDate;
  }
}
