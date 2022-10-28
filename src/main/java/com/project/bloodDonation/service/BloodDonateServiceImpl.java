package com.project.bloodDonation.service;


import com.project.bloodDonation.domain.BloodDonation;
import com.project.bloodDonation.dto.BloodDonationDTO;
import com.project.bloodDonation.repository.BloodDonationRepository;
import java.security.DomainLoadStoreParameter;
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

  public BloodDonationDTO createDonationInfo() {
    Optional<BloodDonation> donation = bloodDonationRepository.findById(USER_ID);
    if (donation.isEmpty()) {
      BloodDonation donationInfo = BloodDonation.builder()
          .id(USER_ID)
          .count(1)
          .date(LocalDateTime.now())
          .availableDate(calculationAvailableDate())
          .build();
      bloodDonationRepository.save(donationInfo);
      return new BloodDonationDTO(donationInfo);
    }
    return new BloodDonationDTO(donation.get());
  }

  public BloodDonation getDonationInfo(Long id) {
    BloodDonation donation = bloodDonationRepository.findById(id).get();
    return BloodDonation.builder()
        .id(donation.getId())
        .count(donation.getCount())
        .date(donation.getDate())
        .availableDate(count_D_Day(donation.getAvailableDate()))
        .build();
  }

  public ResponseEntity updateDonationInfo(Long id, BloodDonationDTO bloodDonationDTO) {
    BloodDonation donation = bloodDonationRepository.findById(id).get();
    if (bloodDonationDTO.getBlood_Donation_Available_Date() <= OVER) {
      donation.update(donation.getCount() + 1,LocalDateTime.now(), calculationAvailableDate());
      bloodDonationRepository.save(donation);
      return new ResponseEntity("success", HttpStatus.OK);
    }
    return new ResponseEntity("fail", HttpStatus.OK);
  }

  private int calculationAvailableDate() {
    return localDate.get(ChronoField.DAY_OF_YEAR) + AFTER_BLOOD_DONATION_DATE;
  }

  private int count_D_Day(int blood_Donation_Available_Date) {
    return blood_Donation_Available_Date - localDate.get(ChronoField.DAY_OF_YEAR);
  }
}