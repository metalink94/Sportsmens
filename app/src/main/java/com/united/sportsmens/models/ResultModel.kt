package com.united.sportsmens.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ResultModel(val leftTeam: Team,
                  val score: String,
                  val rightTeam: Team,
                  val description: String,
                  val detailDescription: String): Parcelable

@Parcelize
class Team(val iconRes: Int, val teamName: String, val teamCoef: String): Parcelable
