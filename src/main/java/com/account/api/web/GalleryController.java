package com.account.api.web;

import com.account.api.domain.Account;
import com.account.api.domain.Gallery;
import com.account.api.domain.service.AccountService;
import com.account.api.domain.service.GalleryService;
import com.account.api.domain.service.S3Service;
import com.account.api.web.dto.GalleryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = {"2. Grallery"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts/") //컨트롤러 기본 URL
public class GalleryController {
    private final S3Service s3Service;
    private final GalleryService galleryService;
    private final AccountService accountService;

    @Value("${cloud.aws.s3.url}")
    private String S3_url;

    @GetMapping("gallery")
    public String dispWrite() {
        return "/gallery";
    }

    @ApiOperation(value = "filePath 추가", notes = "filePath 데이터를 받아온다.")
    @PostMapping("{accountId}/gallery")
    public GalleryDto execWrite(@RequestParam("file") MultipartFile file, @PathVariable String accountId) throws IOException {
        Account account = accountService.getAccount(accountId);
        //그룹에 맞는 갤러리가 있는지
        Gallery gallery = galleryService.getGallery(account);
        String filePath = null;

        if(gallery != null ){
            filePath = gallery.getTitle();
        }

        String fileName = s3Service.upload(filePath,file);
//        galleryService.getGallery().getFilePath()
//        String filename= file.getOriginalFilename();
        //생성자와 같지만 더 명확하게 보여줌 //데이터를 받아오는 건 엔티티 객체로 저장
        Gallery newGallery = Gallery.builder()
                .title(fileName)
                .filePath(S3_url+fileName)
                .account(account)
                .build();
        //겔러리가 이미 있는 건 UPDATE
        //겔러리의 ID를 취득하여 newGallery에 넣어줌
        //save시에 ID 값이 있으면 업데이트가 됨
        if(gallery != null) newGallery.setId((gallery.getId()));

        //엔티티를 DTO로 변환해서 클라이언트에 반환
        return new GalleryDto(galleryService.addGallery(newGallery));

    }
}
