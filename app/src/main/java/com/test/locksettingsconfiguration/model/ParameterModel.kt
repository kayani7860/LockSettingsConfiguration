package com.test.locksettingsconfiguration.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
data class ParameterModel(
    var values: List<String>,
    var default: String,
    var common: Boolean = false
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createStringArrayList() ?: listOf(),
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(values)
        parcel.writeString(default)
        parcel.writeByte(if (common) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParameterModel> {
        override fun createFromParcel(parcel: Parcel): ParameterModel {
            return ParameterModel(parcel)
        }

        override fun newArray(size: Int): Array<ParameterModel?> {
            return arrayOfNulls(size)
        }
    }
}