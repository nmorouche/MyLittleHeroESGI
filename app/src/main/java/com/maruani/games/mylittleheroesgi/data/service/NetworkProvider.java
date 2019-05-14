package com.maruani.games.mylittleheroesgi.data.service;

import com.maruani.games.mylittleheroesgi.data.dto.WeaponDTO;
import com.maruani.games.mylittleheroesgi.data.dto.mapper.WeaponMapper;
import com.maruani.games.mylittleheroesgi.data.model.Weapon;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkProvider {
  MyLittleHeroService myLittleHeroService;

  private static NetworkProvider instance;

  public static NetworkProvider getInstance() {
    if (instance == null) {
      instance = new NetworkProvider();
    }
    return instance;
  }

  private NetworkProvider() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://demo9070682.mockable.io/mylittlehero/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    myLittleHeroService = retrofit.create(MyLittleHeroService.class);
  }

  public void getWeapons(Listener<List<Weapon>> listener) {
    myLittleHeroService.getWeapons().enqueue(new Callback<List<WeaponDTO>>() {
      @Override public void onResponse(Call<List<WeaponDTO>> call, Response<List<WeaponDTO>> response) {
        List<WeaponDTO> weaponDTOList = response.body();
        List<Weapon> weaponList = WeaponMapper.map(weaponDTOList);

        listener.onSuccess(weaponList);
      }

      @Override public void onFailure(Call<List<WeaponDTO>> call, Throwable t) {
        listener.onError(t);
      }
    });
  }

  public interface Listener<T> {
    void onSuccess(T data);

    void onError(Throwable t);
  }
}
