package com.example.tinyUrl.service;

import com.example.tinyUrl.entity.Redirect;
import com.example.tinyUrl.repository.RedirectRepository;
import com.example.tinyUrl.request.RedirectCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedirectService {

    private RedirectRepository redirectRepository;

    @Autowired
    public RedirectService(RedirectRepository redirectRepository) {
        this.redirectRepository = redirectRepository;
    }

    public Optional<Redirect> createRedirect(RedirectCreationRequest redirectCreationRequest) {

        if (redirectRepository.existsByAlias(redirectCreationRequest.getAlias())) {
            System.out.println("Alias already exists");
            return null;
        }
        Redirect redirect = new Redirect(redirectCreationRequest.getAlias(), redirectCreationRequest.getUrl());

        Redirect postSaveRedirect = redirectRepository.save(redirect);
        return Optional.ofNullable(postSaveRedirect);
    }


    public Redirect getRedirectByAlias(String alias) {
        Redirect redirect = redirectRepository.findByAlias(alias).orElseThrow(()->{return null;});
        return redirect;
    }

    public Redirect getRedirectByUrl(String url) {
        Redirect redirect = redirectRepository.findByUrl(url).orElseThrow(()->{return null;});
        return redirect;
    }

    public Boolean ifUrlExist (String url){
        return redirectRepository.existsByUrl(url);
    }

    public boolean ifAliasExist(String alias) {
        return redirectRepository.existsByAlias(alias);
    }
}
