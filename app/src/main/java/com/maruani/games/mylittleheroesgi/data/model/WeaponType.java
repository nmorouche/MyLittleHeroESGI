package com.maruani.games.mylittleheroesgi.data.model;

public enum WeaponType {
  NONE("", ""),
  BOW("1", "Bow"),
  SWORD("2", "Sword"),
  AXE("3", "Axe"),
  STAFF("4", "Staff");

  private String id;
  private String name;

  WeaponType(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public static WeaponType getById(String id) {
    for (WeaponType value : values()) {
      if (value.id.equalsIgnoreCase(id)) return value;
    }
    return NONE;
  }

  @Override public String toString() {
    return name;
  }
}
