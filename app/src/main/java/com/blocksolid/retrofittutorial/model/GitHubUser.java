package com.blocksolid.retrofittutorial.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubUser {

    private String name;
    private String company;
    private String blog;
    private String email;
    private String location;

    public String getName() {
        return name;
    }

    public String getBlog() {
        return blog;
    }

    public String getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }
}
