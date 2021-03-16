package com.ayeshaazeema.nonton.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    private var uid: String = "0",
    private var fullName: String = "",
    private var email: String = "",
    private var photo: String = ""
) : Parcelable