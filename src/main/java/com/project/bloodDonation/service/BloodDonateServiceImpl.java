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
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BloodDonateServiceImpl {

  private final static int AFTER_BLOOD_DONATION_DATE = 56;
  private final static int OVER = 0;

  final BloodDonationRepository bloodDonationRepository;
  LocalDate localDate = LocalDate.now();

  public ResponseEntity createDonationInfo(BloodDonationDTO donationDTO) {
    Optional<BloodDonation> donation = bloodDonationRepository.findById(1L);
    if (donation.isEmpty()) {
      BloodDonation donationInfo = BloodDonation.builder()
          .id(donationDTO.getId())
          .count(donationDTO.getBlood_Donation_Count() + 1)
          .date(LocalDateTime.now())
          .availableDate(localDate.get(ChronoField.DAY_OF_YEAR) + AFTER_BLOOD_DONATION_DATE)
          .build();

      bloodDonationRepository.save(donationInfo);

      return new ResponseEntity("success", HttpStatus.OK);
    }
    return new ResponseEntity("fail", HttpStatus.OK);
  }

  //초기 데이터 생성후 상태 페이지 나올때매다 날짜 값이랑 헌혈 횟수를 보여준다.
  public BloodDonation getDonationInfo(BloodDonationDTO donationDTO) {
    Optional<BloodDonation> donation = bloodDonationRepository.findById(donationDTO.getId());
    if (donation.isPresent()) {
      BloodDonation donationInfo = BloodDonation.builder()
          .id(donationDTO.getId())
          .count(donationDTO.getBlood_Donation_Count())
          .date(donationDTO.getBlood_Donation_Date())
          .availableDate(count_D_Day(donationDTO.getBlood_Donation_Available_Date()))
          .build();

      return donationInfo;
    }
    return null;
  }

  public ResponseEntity deleteDonationInfo(Long id) {
    BloodDonation donation = bloodDonationRepository.findById(id).get();
    if (count_D_Day(donation.getAvailableDate()) == OVER) {
      bloodDonationRepository.deleteById(id);
      return new ResponseEntity("success", HttpStatus.OK);
    }
    return new ResponseEntity("fail", HttpStatus.OK);
  }


  private int count_D_Day(int blood_Donation_Available_Date) {
    return blood_Donation_Available_Date - localDate.get(ChronoField.DAY_OF_YEAR);
  }

}