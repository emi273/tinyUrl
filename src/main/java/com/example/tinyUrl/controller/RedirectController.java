package com.example.tinyUrl.controller;

import com.example.tinyUrl.entity.Redirect;
import com.example.tinyUrl.request.RedirectCreationRequest;
import com.example.tinyUrl.service.HashService;
import com.example.tinyUrl.service.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class RedirectController {

    private RedirectService redirectService;

    @Autowired
    public RedirectController(RedirectService redirectService) {
        this.redirectService = redirectService;
    }

    @GetMapping("/{alias}")
    public String handleRedirect(@PathVariable String alias) {
        if(!redirectService.ifAliasExist(alias)) return "No url found for this alias.";
        Redirect redirect = redirectService.getRedirectByAlias(alias);
        return "https://tinyutl.comhash={"+redirect.getUrl()+"}";
    }




    @PostMapping("/{url}")
    public String createRedirect(@PathVariable final String url) {
        final String alias = UUID.randomUUID().toString();
        //HashService hashService = new HashService(url);
        //final String alias = hashService.hash();
        RedirectCreationRequest redirectCreationRequest = new RedirectCreationRequest(alias, url);
        if(redirectService.ifUrlExist(url)) return "Your new alias for:" + url+" is: "+redirectService.getRedirectByUrl(url).getAlias();
        ResponseEntity.ok(redirectService.createRedirect(redirectCreationRequest));
        return "Your new alias for:" + url+" is: "+alias ;

    }
}

/*
package com.example.tinyUrl.controller;

import com.example.tinyUrl.entity.Redirect;
import com.example.tinyUrl.request.RedirectCreationRequest;
import com.example.tinyUrl.service.HashService;
import com.example.tinyUrl.service.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

@RestController
public class RedirectController {

    private RedirectService redirectService;

    @Autowired
    public RedirectController(RedirectService redirectService) {
        this.redirectService = redirectService;
    }

    @GetMapping("/{alias}")
    public String handleRedirect(@PathVariable String alias) throws URISyntaxException {
        Redirect redirect = redirectService.getRedirect(alias);
        System.out.println("We're redirecting here!" + redirect.getUrl().toString());
        return "https://tinyutl.comhash={"+redirect.getUrl().toString()+"}";
    }



    @PostMapping("/{url}")
    public ResponseEntity createRedirect(@PathVariable final String url) {
        System.out.println(url);
        System.out.println("***");
        HashService hashService = new HashService(url);
        final String alias = hashService.hash();
        System.out.println(alias);
        RedirectCreationRequest redirectCreationRequest = new RedirectCreationRequest(alias, url);

        //JSONObject sampleObject = new JSONObject();
        //System.out.println(redirectCreationRequest.toString());
        return ResponseEntity.ok(redirectService.createRedirect(redirectCreationRequest));

    }
}


 */