package com.example.tinyUrl.request;

public class RedirectCreationRequest {
    private String alias;
    private String url;

    public RedirectCreationRequest(final String alias, final String url) {
        this.alias = alias;
        this.url = url;
    }

    public String getAlias() {
        return alias;
    }

    public String getUrl() {
        return url;
    }

}
