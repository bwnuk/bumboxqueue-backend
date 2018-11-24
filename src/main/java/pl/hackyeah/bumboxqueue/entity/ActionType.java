package pl.hackyeah.bumboxqueue.entity;

public enum ActionType {
  ADD_DOCTOR("ADD_ACTION"),
  ADD_PATIENT("ADD_PATIENT"),
  ADD_VISIT("ADD_VISIT"),
  REMOVE_DOCTOR("REMOVE_DOCTOR"),
  REMOVE_PATIENT("REMOVE_PATIENT"),
  REMOVE_VISIT("REMOVE_VISIT"),
  MODIFY_PATIENT("MODIFY_PATIENT");


  private String action;

  ActionType(String action) {
    this.action = action;
  }

  public String getAction() {
    return action;
  }
}
