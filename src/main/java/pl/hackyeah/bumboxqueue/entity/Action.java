package pl.hackyeah.bumboxqueue.entity;

public enum Action {
  ADD_DOCTOR("ADD_ACTION"),
  ADD_PATIENT("ADD_PATIENT"),
  ADD_VISIT("ADD_VISIT"),
  REMOVE_DOCTOR("REMOVE_DOCTOR"),
  REMOVE_PATIENT("REMOVE_PATIENT"),
  REMOVE_VISIT("REMOVE_VISIT");


  private String action;

  Action(String action) {
    this.action = action;
  }

  public String getAction() {
    return action;
  }
}
