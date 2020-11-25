package com.account.api.domain.service;

import com.account.api.domain.Account;
import com.account.api.domain.Gallery;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;


public interface GalleryService {
    public List<Gallery> getGalleries() throws NoSuchElementException;
    public Gallery getGallery(Account account) throws NoSuchElementException;
    public Gallery addGallery(Gallery gallery) throws NoSuchElementException;
    public Gallery modifyGallery(Gallery gallery) throws NoSuchElementException;
    public void deleteGallery(Long galleryId) throws NoSuchElementException;
    public boolean isExist(Long galleryId);

}