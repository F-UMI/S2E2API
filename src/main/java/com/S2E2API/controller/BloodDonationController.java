package com.S2E2API.controller;

import com.S2E2API.domain.BloodDonationHouse;
import com.S2E2API.dto.BloodDonationDTO;
import com.S2E2API.dto.BloodDonationHouseDTO;
import com.S2E2API.service.BloodDonateServiceImpl;;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.http.PATCH;

@RestController
@RequiredArgsConstructor
public class BloodDonationController {

  private final BloodDonateServiceImpl bloodDonateService;

  @RequestMapping(value = "/{id}")
  public ResponseEntity getInfo(@PathVariable("id") Long id) {
    return ResponseEntity.ok(bloodDonateService.getDonationInfo(id));
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
  public ResponseEntity resetInfo(@PathVariable
      ("id") Long id) {
    return ResponseEntity.ok(bloodDonateService.updateDonationInfo(id));
  }

  @RequestMapping(value = "/findAll"  ,produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<List<BloodDonationHouse>> getHouseInfo() {
    List<BloodDonationHouse> bloodDonationHouseList = bloodDonateService.getHouseInfo();
    return new ResponseEntity<>(bloodDonationHouseList, HttpStatus.OK);
  }

  //Resolved [org.springframework.web.method.annotation.MethodArgumentTypeMismatchException: Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; nested exception is java.lang.NumberFormatException: For input string: "findAll"]


}

