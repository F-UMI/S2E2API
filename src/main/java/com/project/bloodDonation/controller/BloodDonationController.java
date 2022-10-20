package com.project.bloodDonation.controller;

import com.project.bloodDonation.domain.User;
import com.project.bloodDonation.dto.BloodDonationDTO;
import com.project.bloodDonation.service.BloodDonateServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BloodDonationController {

  private final BloodDonateServiceImpl bloodDonateService;

//  @GetMapping("status")
//  public ResponseEntity test(@RequestBody BloodDonationDTO donationDTO) {
//    ResponseEntity responseEntity = bloodDonateService.registerUser(donationDTO);
//    return responseEntity;
//  }
//
//@


  @RequestMapping("/status")
  public ResponseEntity save(@RequestBody BloodDonationDTO donationDTO){
    ResponseEntity responseEntity = bloodDonateService.registerUser(donationDTO);
    return responseEntity;
  }


//  @RequestMapping("/")
//  public String index(Model model) {
//    List<BloodDonationDTO> posts = bloodDonateService.getAll();
//    model.addAttribute("posts", posts);
//    return "status";
//  }


//  @GetMapping(value = "/donation-status/{count}")
//  public String viewMain(int count) {
//    return "";
//  }
//
//  @GetMapping(value = "/donation-status/{date}")
//  public String viewMap() {
//    return "";
//  }
//
//  @PatchMapping (value = "/donation-status/{date}")
//  public String DonationAvailableDateCount(LocalDateTime date) {
//    return "";
//  }
//
//  @PatchMapping(value = "/donation-status/{count}")
//  public String increaseDonationCount(int count) {
//    return "";
//  }
}
