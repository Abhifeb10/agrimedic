package com.agrimedic.agrimedic_backend.dto;

import com.agrimedic.agrimedic_backend.entity.Role;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Role role;
    private boolean enabled;

    public UserDTO() {}

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}