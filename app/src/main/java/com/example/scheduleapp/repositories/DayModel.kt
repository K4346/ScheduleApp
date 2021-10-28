package com.example.scheduleapp.repositories

import android.os.Parcel
import android.os.Parcelable
import android.util.ArrayMap


class DayModel() : Parcelable {
    var dayOfWeek: String?=""
    var lessons: ArrayList<String>?= arrayListOf()
    var things: ArrayMap<String, List<String>>? = ArrayMap()

    constructor(parcel: Parcel) : this() {
        dayOfWeek = parcel.readString()
        lessons = parcel.createStringArrayList()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(dayOfWeek)
        parcel.writeStringList(lessons)
    }

    override fun describeContents(): Int {
        return 0
    }

     companion object CREATOR : Parcelable.Creator<DayModel> {
         override fun createFromParcel(parcel: Parcel): DayModel {
             return DayModel(parcel)
         }

         override fun newArray(size: Int): Array<DayModel?> {
             return arrayOfNulls(size)
         }
     }
 }
