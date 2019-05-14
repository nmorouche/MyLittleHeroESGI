package com.maruani.games.mylittleheroesgi.data.dto.mapper;

import com.maruani.games.mylittleheroesgi.data.dto.WeaponDTO;
import com.maruani.games.mylittleheroesgi.data.model.Weapon;
import com.maruani.games.mylittleheroesgi.data.model.WeaponType;
import java.util.ArrayList;
import java.util.List;

public class WeaponMapper {

  public static List<Weapon> map(List<WeaponDTO> weaponDTOList) {
    List<Weapon> weaponList = new ArrayList<>();
    for (WeaponDTO weaponDTO : weaponDTOList) {
      weaponList.add(map(weaponDTO));
    }
    return weaponList;
  }

  private static Weapon map(WeaponDTO weaponDTO) {
    Weapon weapon = new Weapon();
    weapon.setName(weaponDTO.getName());
    weapon.setPictureUrl(weaponDTO.getUrl());
    weapon.setType(WeaponType.getById(weaponDTO.getType()));
    return weapon;
  }
}
