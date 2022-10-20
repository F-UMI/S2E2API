package com.project.bloodDonation.repository;

import com.project.bloodDonation.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodDonationRepository extends JpaRepository<User,Long> {
}
