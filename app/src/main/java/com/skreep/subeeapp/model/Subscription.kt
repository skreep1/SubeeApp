package com.skreep.subeeapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "subscription_table")
data class Subscription(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val nameSub: String,
        val descSub: String,
        val priceSub: String
): Parcelable