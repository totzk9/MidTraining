package com.lig.midtraining.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(val title: String,
                 val overview: String,
                 val backdrop_path: String,
                 val poster_path: String) : Parcelable