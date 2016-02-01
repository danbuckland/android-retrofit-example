package com.blocksolid.retrofittutorial.model;

    import com.google.gson.annotations.Expose;
    import com.google.gson.annotations.SerializedName;

public class GitHubUser {

//Created using http://www.jsonschema2pojo.org/

    private String login;
    private Integer id;
    private String name;
    private String company;
    private String blog;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBlog() {
        return blog;
    }

    public String getCompany() {
        return company;
    }

    public String getLogin() {
        return login;
    }


}
