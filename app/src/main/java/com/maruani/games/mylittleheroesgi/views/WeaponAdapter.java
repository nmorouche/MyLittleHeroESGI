package com.maruani.games.mylittleheroesgi.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.bumptech.glide.Glide;
import com.maruani.games.mylittleheroesgi.R;
import com.maruani.games.mylittleheroesgi.data.model.Weapon;
import java.util.List;

public class WeaponAdapter extends RecyclerView.Adapter<WeaponAdapter.WeaponViewHolder> {
  private List<Weapon> weaponList;
  private ItemClickListener itemClickListener;

  public void setWeaponList(List<Weapon> weaponList) {
    this.weaponList = weaponList;
    notifyDataSetChanged();
  }

  public void setItemClickListener(ItemClickListener itemClickListener) {
    this.itemClickListener = itemClickListener;
  }

  @NonNull @Override public WeaponViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weapon, viewGroup, false);
    return new WeaponViewHolder(view);
  }

  @Override public void onBindViewHolder(@NonNull WeaponViewHolder weaponViewHolder, int i) {
    Weapon weapon = weaponList.get(i);
    weaponViewHolder.nameTv.setText(weapon.getName());
    weaponViewHolder.typeTv.setText(weapon.getType().toString());
    Glide.with(weaponViewHolder.itemView).load(weapon.getPictureUrl()).into(weaponViewHolder.pictureImv);

    if(itemClickListener != null){
      weaponViewHolder.itemView.setOnClickListener((View v) -> {
        itemClickListener.onClick(weapon);
      });
    }
  }

  @Override public int getItemCount() {
    return weaponList != null ? weaponList.size() : 0;
  }

  class WeaponViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_weapon_picture_imv) ImageView pictureImv;
    @BindView(R.id.item_weapon_type_tv) TextView typeTv;
    @BindView(R.id.item_weapon_name_tv) TextView nameTv;

    public WeaponViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public interface ItemClickListener {
    void onClick(Weapon weapon);
  }
}
