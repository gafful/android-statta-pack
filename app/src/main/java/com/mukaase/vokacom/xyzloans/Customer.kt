package com.mukaase.vokacom.xyzloans

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customer(
    /*@ColumnInfo(name = "word")*/
    val firstName: String, val lastName: String, val marStatus: String, val empStatus: String,
    val empName: String, val dob: String, val idCardType: String, val address: String, val phone: String,
    val principal: Float
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat()
    ) {
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(marStatus)
        parcel.writeString(empStatus)
        parcel.writeString(empName)
        parcel.writeString(dob)
        parcel.writeString(idCardType)
        parcel.writeString(address)
        parcel.writeString(phone)
        parcel.writeFloat(principal)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Customer> {
        override fun createFromParcel(parcel: Parcel): Customer {
            return Customer(parcel)
        }

        override fun newArray(size: Int): Array<Customer?> {
            return arrayOfNulls(size)
        }
    }


}