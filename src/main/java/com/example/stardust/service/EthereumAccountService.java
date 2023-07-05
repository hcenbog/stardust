package com.example.stardust.service;

import org.springframework.stereotype.Service;
import org.web3j.crypto.*;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @author AlHeae
 * @Description
 * @date 2023/5/4 10:09
 */
@Service
public class EthereumAccountService {
    private static final String KEYSTORE_FOLDER = "D:\\zhuomian\\stardust\\mde\\ethereum\\keystore";
    private static final String DEFAULT_PASSWORD = "254233";

    public File createNewAccount() throws CipherException, IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        ECKeyPair keyPair = Keys.createEcKeyPair();
        String walletFileName = WalletUtils.generateNewWalletFile(DEFAULT_PASSWORD, new File(KEYSTORE_FOLDER), false);
        File walletFile = new File(KEYSTORE_FOLDER, walletFileName);

        return walletFile;
    }
}
