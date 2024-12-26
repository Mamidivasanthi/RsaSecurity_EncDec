package com.Instanpe.RSAEncryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rsa")
public class RSAController {
    @Autowired
    private EncryptDecryptService encryptDecryptService;
    @PostMapping("/encrypt")
    public String encrypt(@RequestBody String data) throws Exception {
        return encryptDecryptService.encrypt(data);

    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestBody String encryptedData) throws Exception {
        return encryptDecryptService.decrypt(encryptedData);

    }


}
