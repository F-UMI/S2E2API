package com.project.bloodDonation.service;


import com.project.bloodDonation.domain.User;
import com.project.bloodDonation.dto.BloodDonationDTO;
import com.project.bloodDonation.repository.BloodDonationRepository;
import java.io.Console;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.xml.crypto.KeySelector.Purpose;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BloodDonateServiceImpl {

  private final static int AFTER_BLOOD_DONATION_DATE = 56;

  final BloodDonationRepository bloodDonationRepository;
  LocalDate localDate = LocalDate.now();
  public ResponseEntity registerUser(BloodDonationDTO donationDTO) {
    Optional<User> user = bloodDonationRepository.findById(donationDTO.getId());
    if (user.isEmpty()) {
      User newUser = User.builder()
          .id(donationDTO.getId())
          .count(donationDTO.getBlood_Donation_Count() + 1)
          .date(LocalDateTime.now())
          .availableDate(localDate.get(ChronoField.DAY_OF_YEAR) + AFTER_BLOOD_DONATION_DATE)
          .build();

      bloodDonationRepository.save(newUser);

      return new ResponseEntity("success", HttpStatus.OK);
    }
    return new ResponseEntity("fail", HttpStatus.OK);

  }

//  public User getStatus(Long id) {
//    Optional<User> user = bloodDonationRepository.findById(id);
//    User userEntity = user.orElse(null);
//    LocalDate localDate = LocalDate.now();
//
//    return User.builder()
//        .id(userEntity.getId())
//        .count(userEntity.getCount())
//        .date(userEntity.getDate())
//        .availableDate(userEntity.getAvailableDate() - localDate.get(ChronoField.DAY_OF_YEAR))
//        .build();
//
//  }
public List<BloodDonationDTO> getAll() {

  List<User> users = bloodDonationRepository.findAll();
  List<BloodDonationDTO> list = new ArrayList<>();

  for (User user : users) {
    BloodDonationDTO donationDTO = BloodDonationDTO.builder()
        .id(user.getId())
        .blood_Donation_Count(user.getCount())
        .blood_Donation_Date(user.getDate())
        .blood_Donation_Available_Date(user.getAvailableDate() - localDate.get(ChronoField.DAY_OF_YEAR))
        .build();

        list.add(donationDTO);
  }
  return list;
}





}