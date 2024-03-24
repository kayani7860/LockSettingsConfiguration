package com.test.locksettingsconfiguration.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
data class Range(
    val min: Double? = null,
    val max: Double? = null
)

@Serializable
data class LockConfig(
    val lockVoltage: LockVoltageConfig,
    val lockType: LockVoltageConfig,
    val lockKick: LockVoltageConfig,
    val lockRelease: LockVoltageConfig
)

@Serializable
data class LockVoltageConfig(
    val values: List<String>,
    val default: String,
    val common: Boolean = false
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

    companion object CREATOR : Parcelable.Creator<LockVoltageConfig> {
        override fun createFromParcel(parcel: Parcel): LockVoltageConfig {
            return LockVoltageConfig(parcel)
        }

        override fun newArray(size: Int): Array<LockVoltageConfig?> {
            return arrayOfNulls(size)
        }
    }
}

data class ParameterModel(
    val parameterName: String?,
    val dataModel: LockVoltageConfig,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable<LockVoltageConfig>(LockVoltageConfig::class.java.classLoader)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(parameterName)
        parcel.writeParcelable(dataModel, flags)
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

