package com.bunyaminkalkan.asksomeone.repos;

import com.bunyaminkalkan.asksomeone.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    RefreshToken findByUserId(Long id);
}
