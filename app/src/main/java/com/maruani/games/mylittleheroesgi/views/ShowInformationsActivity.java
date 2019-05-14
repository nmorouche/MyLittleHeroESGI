package com.maruani.games.mylittleheroesgi.views;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maruani.games.mylittleheroesgi.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowInformationsActivity extends AppCompatActivity {

    @BindView(R.id.show_information_activity_username_tv)
    TextView username;
    @BindView(R.id.show_information_activity_imv)
    ImageView image;
    @BindView(R.id.show_information_activity_gender_tv)
    TextView gender;
    @BindView(R.id.show_information_activity_birthdate_tv)
    TextView birthdate;
    @BindView(R.id.show_information_activity_weapon_type_tv)
    TextView weaponType;
    @BindView(R.id.show_information_activity_weapon_name_tv)
    TextView weaponName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_informations);
        ButterKnife.bind(this);
        ArrayList<String> user = getArrayListExtra("userInformations");
        ArrayList<String> weapon = getArrayListExtra("weaponInformations");
        displayDatas(user, weapon);
    }

    private ArrayList<String> getArrayListExtra(String key) {
        Intent intent = getIntent();
        ArrayList<String> extra = intent.getStringArrayListExtra(key);
        return extra;
    }

    private void displayDatas(ArrayList<String> user, ArrayList<String> weapon) {
        username.setText(getString(R.string.activity_show_informations_username, user.get(0)));
        gender.setText(getString(R.string.activity_show_informations_gender, user.get(1)));
        birthdate.setText(getString(R.string.activity_show_informations_birthdate, user.get(2)));
        weaponType.setText(getString(R.string.activity_show_informations_weapon_type, weapon.get(0)));
        weaponName.setText(getString(R.string.activity_show_informations_weapon_name, weapon.get(1)));
        Glide.with(this).load(weapon.get(2)).into(image);
    }

}