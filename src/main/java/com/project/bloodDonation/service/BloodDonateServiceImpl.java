package com.project.bloodDonation.service;


import com.project.bloodDonation.domain.BloodDonation;
import com.project.bloodDonation.dto.BloodDonationDTO;
import com.project.bloodDonation.repository.BloodDonationRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BloodDonateServiceImpl {

  private final static int AFTER_BLOOD_DONATION_DATE = 56;
  private final static long USER_ID = 1L;
  private final static int OVER = 0;

  final BloodDonationRepository bloodDonationRepository;
  LocalDate localDate = LocalDate.now();

  public ResponseEntity createDonationInfo(BloodDonationDTO donationDTO) {
    Optional<BloodDonation> donation = bloodDonationRepository.findById(USER_ID);
    if (donation.isEmpty()) {
      BloodDonation donationInfo = BloodDonation.builder()
          .id(donationDTO.getId())
          .count(donationDTO.getBlood_Donation_Count() + 1)
          .date(LocalDateTime.now())
          .availableDate(calculationAvailableDate())
          .build();

      bloodDonationRepository.save(donationInfo);

      return new ResponseEntity("success", HttpStatus.OK);
    }
    return new ResponseEntity("fail", HttpStatus.OK);
  }

  public BloodDonation getDonationInfo(Long id) {
    Optional<BloodDonation> donation = bloodDonationRepository.findById(id);
    return BloodDonation.builder()
        .id(donation.get().getId())
        .count(donation.get().getCount())
        .date(donation.get().getDate())
        .availableDate(count_D_Day(donation.get().getAvailableDate()))
        .build();
  }

  public ResponseEntity updateDonationInfo(Long id, BloodDonationDTO bloodDonationDTO) {
    BloodDonation donation = bloodDonationRepository.findById(id).get();
    if (bloodDonationDTO.getBlood_Donation_Count() <= 0) {
      donation.update(LocalDateTime.now(), calculationAvailableDate());
      bloodDonationRepository.save(donation);
      return new ResponseEntity("success", HttpStatus.OK);
    }
    return new ResponseEntity("fail", HttpStatus.OK);
  }

  private int calculationAvailableDate() {
    return localDate.get(ChronoField.DAY_OF_YEAR) + AFTER_BLOOD_DONATION_DATE;
  }
  public int count_D_Day(int blood_Donation_Available_Date) {
    return blood_Donation_Available_Date - localDate.get(ChronoField.DAY_OF_YEAR);
  }
}