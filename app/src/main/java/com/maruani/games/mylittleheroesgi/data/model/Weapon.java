package com.maruani.games.mylittleheroesgi.data.model;

public class Weapon {
  private WeaponType type;
  private String name;
  private String pictureUrl;

  public WeaponType getType() {
    return type;
  }

  public void setType(WeaponType type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPictureUrl() {
    return pictureUrl;
  }

  public void setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }

  @Override public String toString() {
    return "Weapon{" + "type=" + type + ", name='" + name + '\'' + ", pictureUrl='" + pictureUrl + '\'' + '}';
  }
}
