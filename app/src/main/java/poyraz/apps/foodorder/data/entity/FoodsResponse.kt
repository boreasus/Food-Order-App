package poyraz.apps.foodorder.data.entity

import com.google.gson.annotations.SerializedName

data class FoodsResponse(@SerializedName("yemekler") var foods:List<Foods>,@SerializedName("success") var success:Int)
