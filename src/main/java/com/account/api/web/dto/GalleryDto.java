package com.account.api.web.dto;

import com.account.api.domain.Account;
import com.account.api.domain.Gallery;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GalleryDto implements Serializable {
    private Long id;
    private String title;
    private String filePath;
    private String accountId;

    public GalleryDto(Gallery gallery){
        BeanUtils.copyProperties(gallery,this);
        this.accountId = gallery.getAccount().getAccountId();
    }

    public Gallery toDomain(Account account){
        Gallery gallery = new Gallery();
        BeanUtils.copyProperties(this,gallery);

        gallery.setAccount(account);
        return gallery;
    }

}
