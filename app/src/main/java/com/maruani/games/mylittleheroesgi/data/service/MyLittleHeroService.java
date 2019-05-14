package com.maruani.games.mylittleheroesgi.data.service;

import com.maruani.games.mylittleheroesgi.data.dto.WeaponDTO;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MyLittleHeroService {
  @GET("weapons") Call<List<WeaponDTO>> getWeapons();
}
