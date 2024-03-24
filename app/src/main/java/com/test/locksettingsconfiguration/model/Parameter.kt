package com.test.locksettingsconfiguration.model

import android.os.Parcel
import android.os.Parcelable

data class Parameter(
    val parameterName: String?,
    val dataModel: ParameterModel,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable<ParameterModel>(ParameterModel::class.java.classLoader)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(parameterName)
        parcel.writeParcelable(dataModel, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Parameter> {
        override fun createFromParcel(parcel: Parcel): Parameter {
            return Parameter(parcel)
        }

        override fun newArray(size: Int): Array<Parameter?> {
            return arrayOfNulls(size)
        }
    }
}

