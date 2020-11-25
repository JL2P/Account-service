package com.account.api.domain.logic;

import com.account.api.domain.Account;
import com.account.api.domain.Gallery;
import com.account.api.domain.service.GalleryService;
import com.account.api.repository.GalleryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryRepository;


    @Override
    public List<Gallery> getGalleries() throws NoSuchElementException {
        return null;
    }

    @Override
    public Gallery getGallery(Account account) throws NoSuchElementException {
        return galleryRepository.findByAccount(account).orElse(null);
    }

    @Override
    public Gallery addGallery(Gallery gallery) throws NoSuchElementException  {
        return galleryRepository.save(gallery);
    }

    @Override
    public Gallery modifyGallery(Gallery gallery) throws NoSuchElementException {
        return null;
    }

    @Override
    public void deleteGallery(Long galleryId) throws NoSuchElementException {

    }

    @Override
    public boolean isExist(Long galleryId) {
        return false;
    }
}
