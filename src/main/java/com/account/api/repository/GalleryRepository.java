package com.account.api.repository;

import com.account.api.domain.Account;
import com.account.api.domain.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    Optional<Gallery> findByAccount(Account account);
}