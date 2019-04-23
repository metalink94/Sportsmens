package com.united.sportsmens.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Sportsmen(val icon: Int, val title: String, val description: String): Parcelable
