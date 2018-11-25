package pl.hackyeah.bumboxqueue.entity;

public enum RoleUser {
  ADMIN("ROLE_ADMIN"),
  USER("ROLE_USER");

  private final String role;

  RoleUser(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }
}