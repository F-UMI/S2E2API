package com.S2E2API.repository;

import com.S2E2API.domain.BloodDonation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodDonationRepository extends JpaRepository<BloodDonation,Long> {
}
