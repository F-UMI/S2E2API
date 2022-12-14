package com.S2E2API.service;


import com.S2E2API.domain.BloodDonation;
import com.S2E2API.domain.BloodDonationHouse;
import com.S2E2API.dto.StatusDTO;
import com.S2E2API.repository.BloodDonationHouseRepository;
import com.S2E2API.repository.BloodDonationRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BloodDonateServiceImpl {

  private final static int AFTER_BLOOD_DONATION_DATE = 56;
  private final static long USER_ID = 1L;
  private final static int OVER = 0;

  final BloodDonationRepository bloodDonationRepository;
  final BloodDonationHouseRepository bloodDonationHouseRepository;
  LocalDate localDate = LocalDate.now();

  public BloodDonation getDonationInfo(Long id) {
    BloodDonation donation = bloodDonationRepository.findById(id).get();
    return BloodDonation.builder()
        .id(donation.getId())
        .count(donation.getCount())
        .date(donation.getDate())
        .availableDate(count_D_Day(donation.getAvailableDate()))
        .build();
  }

  public ResponseEntity updateDonationInfo(StatusDTO bloodDonationDTO) {
    BloodDonation donation = bloodDonationRepository.getById(1L);
    if (bloodDonationDTO.getStatus().equals("YES")) {
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

  public List<BloodDonationHouse> getHouseInfo() {
    return new ArrayList<>(
        bloodDonationHouseRepository.findAll());
  }
}