package com.project.bloodDonation.repository;

import com.project.bloodDonation.domain.BloodDonation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodDonationRepository extends JpaRepository<BloodDonation,Long> {
}
