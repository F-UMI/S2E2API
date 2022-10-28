package com.project.bloodDonation.controller;

import com.project.bloodDonation.domain.BloodDonation;
import com.project.bloodDonation.dto.BloodDonationDTO;
import com.project.bloodDonation.service.BloodDonateServiceImpl;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice.OffsetMapping.Target.ForField.ReadOnly;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
  public ResponseEntity createInfo(@RequestBody BloodDonationDTO donationDTO){
    return ResponseEntity.ok(bloodDonateService.createDonationInfo(donationDTO));
  }


  @RequestMapping(value = "/{id}")
  public ResponseEntity getInfo(@PathVariable("id") Long id) {
    return ResponseEntity.ok(bloodDonateService.getDonationInfo(id));
  }

  @Transactional
  @PatchMapping("/{id}")
  public ResponseEntity resetInfo(@PathVariable("id") Long id, @RequestBody BloodDonationDTO bloodDonationDTO) {
    return ResponseEntity.ok(bloodDonateService.updateDonationInfo(id, bloodDonationDTO));
  }

}
