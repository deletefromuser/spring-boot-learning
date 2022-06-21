package com.example.springboot.dao.model;

public class User {
    private String username;

    private String password;

    private Boolean enabled;

    private String provider;

    private String oauthId;

    public User(String username, String password, Boolean enabled, String provider, String oauthId) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.provider = provider;
        this.oauthId = oauthId;
    }

    public User() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider == null ? null : provider.trim();
    }

    public String getOauthId() {
        return oauthId;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId == null ? null : oauthId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", enabled=").append(enabled);
        sb.append(", provider=").append(provider);
        sb.append(", oauthId=").append(oauthId);
        sb.append("]");
        return sb.toString();
    }
}