package com.maruani.games.mylittleheroesgi.data.dto;

import com.google.gson.annotations.SerializedName;

public class WeaponDTO {
  @SerializedName("name") private String name;
  @SerializedName("type") private String type;
  @SerializedName("pictureUrl") private String url;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override public String toString() {
    return "WeaponDTO{" + "name='" + name + '\'' + ", type='" + type + '\'' + ", url='" + url + '\'' + '}';
  }
}
