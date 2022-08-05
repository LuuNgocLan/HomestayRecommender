package com.ltn.travel.module.base;

public interface IBaseItemListener {

    void openDetailHomestay(String idHomestay);

    void onClickFavoriteHomestay(String idHomestay, boolean isFavorite);
}
