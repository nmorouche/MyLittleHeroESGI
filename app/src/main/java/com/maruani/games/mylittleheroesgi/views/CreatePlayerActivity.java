package com.maruani.games.mylittleheroesgi.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.maruani.games.mylittleheroesgi.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CreatePlayerActivity extends AppCompatActivity {

  @BindView(R.id.activity_create_player_pseudo_edt) EditText pseudoEdt;
  @BindView(R.id.activity_create_player_birthdate_edt) EditText birthdateEdt;
  @BindView(R.id.activity_create_player_gender_gr) RadioGroup genderGr;
  DatePickerDialog datePickerDialog;
  private long mLastClickTime = 0;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_player);
    ButterKnife.bind(this);
    initDatePicker();
    birthdateEdt.setInputType(InputType.TYPE_NULL);
  }

  private void initDatePicker() {
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
      String format = "dd/MM/yy";
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.FRANCE);

      calendar.set(Calendar.YEAR, year1);
      calendar.set(Calendar.MONTH, month1);
      calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

      birthdateEdt.setText(simpleDateFormat.format(calendar.getTime()));
    }, year, month, day);
  }

  @OnClick(R.id.activity_create_player_birthdate_edt) void onBirthdateClick() {
    datePickerDialog.show();
  }

  @OnClick(R.id.activity_create_player_next_btn) void onNextButtonClick() {
    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
      return;
    }
    mLastClickTime = SystemClock.elapsedRealtime();
    if(pseudoEdt.getText().length() == 0){
      Toast.makeText(this, "Entrer un nom d'utilisateur", Toast.LENGTH_SHORT).show();
    } else if(genderGr.getCheckedRadioButtonId() == -1) {
      Toast.makeText(this, "Sélectionner un genre", Toast.LENGTH_SHORT).show();
    } else if(birthdateEdt.getText().length() == 0){
      Toast.makeText(this, "Sélectionner une date de naissance", Toast.LENGTH_SHORT).show();
    } else {
      Intent intent = new Intent(this, ChooseWeaponActivity.class);
      ArrayList<String> data = new ArrayList<>();
      data.add(pseudoEdt.getText().toString());
      if(genderGr.getCheckedRadioButtonId() == R.id.activity_create_player_radio_button_male){
        data.add("male");
      } else {
        data.add("female");
      }
      data.add(birthdateEdt.getText().toString());
      intent.putExtra("createPlayerInfo", data);
      startActivity(intent);
    }
  }
}
