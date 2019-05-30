package com.united.sportsmens.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class GameModel(val iconRes: Int, val titleRes: Int, val descriptionRes: Int): Parcelable
