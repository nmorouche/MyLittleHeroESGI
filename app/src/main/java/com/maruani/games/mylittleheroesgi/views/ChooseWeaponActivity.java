package com.maruani.games.mylittleheroesgi.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.maruani.games.mylittleheroesgi.R;
import com.maruani.games.mylittleheroesgi.data.model.Weapon;
import com.maruani.games.mylittleheroesgi.data.service.NetworkProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseWeaponActivity extends AppCompatActivity {

    @BindView(R.id.activity_choose_weapon_rcv)
    RecyclerView weaponChoiceRCV;
    @BindView(R.id.activity_choose_weapon_selectedImage)
    ImageView selectedImage;
    @BindView(R.id.activity_choose_weapon_next_btn)
    Button next;
    private WeaponAdapter weaponAdapter;
    private long mLastClickTime = 0;
    Weapon tmp = new Weapon();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_weapon);
        ButterKnife.bind(this);
        initRecyclerView();
        loadData();
    }

    private void initRecyclerView(){
        weaponChoiceRCV.setLayoutManager(new LinearLayoutManager(this));
        weaponAdapter = new WeaponAdapter();
        weaponChoiceRCV.setAdapter(weaponAdapter);
        weaponAdapter.setItemClickListener(weapon -> {
            Glide.with(this).load(weapon.getPictureUrl()).into(selectedImage);
            tmp.setName(weapon.getName());
            tmp.setType(weapon.getType());
            tmp.setPictureUrl(weapon.getPictureUrl());
        });
    }

    private void loadData(){
        NetworkProvider.getInstance().getWeapons(new NetworkProvider.Listener<List<Weapon>>() {
            @Override public void onSuccess(List<Weapon> data) {
                weaponAdapter.setWeaponList(data);
            }

            @Override public void onError(Throwable t) {

            }
        });
    }

    private void saveDataTest(){
        SharedPreferences sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("key", true).apply();
    }
    private void readDataTest(){
        SharedPreferences sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
        sharedPreferences.getBoolean("key", false);

    }
    private ArrayList<String> getArrayListExtra(String key){
        Intent intent = getIntent();
        ArrayList<String> extra = intent.getStringArrayListExtra(key);
        return extra;
    }
    @OnClick(R.id.activity_choose_weapon_next_btn) void onSubmitClick() {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        if(tmp.getName() == null) {
            Toast.makeText(this, "Sélectionner une arme avant de continuer", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, ShowInformationsActivity.class);
            ArrayList<String> userData = getArrayListExtra("createPlayerInfo");
            ArrayList<String> weaponData = new ArrayList<>();
            weaponData.add(tmp.getName());
            weaponData.add(tmp.getType().toString());
            weaponData.add(tmp.getPictureUrl());
            intent.putExtra("userInformations", userData);
            intent.putExtra("weaponInformations", weaponData);
            startActivity(intent);
        }
    }
}