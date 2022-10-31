package com.S2E2API.controller;

import com.S2E2API.domain.BloodDonationHouse;
import com.S2E2API.dto.BloodDonationDTO;
import com.S2E2API.dto.BloodDonationHouseDTO;
import com.S2E2API.service.BloodDonateServiceImpl;;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

  @RequestMapping(value = "/")
  public BloodDonationDTO createInfo() {
    return bloodDonateService.createDonationInfo();
  }


  @RequestMapping(value = "/{id}")
  public ResponseEntity getInfo(@PathVariable("id") Long id) {
    return ResponseEntity.ok(bloodDonateService.getDonationInfo(id));
  }

  @Transactional
  @RequestMapping(value = "/update/{id}")
  public ResponseEntity updateInfo(@PathVariable("id") Long id, @RequestBody BloodDonationDTO bloodDonationDTO) {
    return ResponseEntity.ok(bloodDonateService.updateDonationInfo(id, bloodDonationDTO));
  }

  @RequestMapping("/main")
  public String getMain() {
    return "main";
  }

  @RequestMapping("/house/get")
  public ResponseEntity<List<BloodDonationHouse>> getHouseInfo() {
    List<BloodDonationHouse> bloodDonationHouseList = bloodDonateService.getHouseInfo();
    return new ResponseEntity<>(bloodDonationHouseList, HttpStatus.OK);
  }

}

