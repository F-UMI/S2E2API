package com.project.bloodDonation.controller;

import com.project.bloodDonation.domain.BloodDonation;
import com.project.bloodDonation.dto.BloodDonationDTO;
import com.project.bloodDonation.service.BloodDonateServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BloodDonationController {

  private final BloodDonateServiceImpl bloodDonateService;

  @RequestMapping(value = "/status", method = {RequestMethod.POST})
  public ResponseEntity createInfo(@RequestBody BloodDonationDTO donationDTO){
    ResponseEntity responseEntity = bloodDonateService.createDonationInfo(donationDTO);
    return responseEntity;
  }

  //이거는 수정해야겠는데?
  @RequestMapping(value = "/status/load", method = {RequestMethod.POST})
  public BloodDonation getInfo(BloodDonationDTO donationDTO) {
    return bloodDonateService.getDonationInfo(donationDTO);
  }

  @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
  public ResponseEntity deleteInfo(@PathVariable Long id) {
    ResponseEntity responseEntity = bloodDonateService.deleteDonationInfo(id);
    return responseEntity;
  }
  //  }
}
