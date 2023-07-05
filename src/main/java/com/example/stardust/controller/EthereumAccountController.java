package com.example.stardust.controller;

import com.example.stardust.service.EthereumAccountService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Path;

/**
 * @author AlHeae
 * @Description
 * @date 2023/5/4 10:11
 */
@RestController

public class EthereumAccountController {

    private final EthereumAccountService ethereumAccountService;

    public EthereumAccountController(EthereumAccountService ethereumAccountService) {
        this.ethereumAccountService = ethereumAccountService;
    }

    @GetMapping("/api/createAccountAndDownloadPrivateKey")
    public ResponseEntity<Resource> createAccountAndDownloadPrivateKey(HttpServletResponse response) throws Exception {
        File walletFile = ethereumAccountService.createNewAccount();
        Path walletFilePath = walletFile.toPath();
        UrlResource resource = new UrlResource(walletFilePath.toUri());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + walletFile.getName() + "\"");

        return ResponseEntity.ok().headers(headers).body(resource);
    }
}
