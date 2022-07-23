package poyraz.apps.foodorder.data.entity

import com.google.gson.annotations.SerializedName

data class BasketResponse(@SerializedName("sepet_yemekler") var foods:List<BasketFoods>, @SerializedName("success") var success:Int)
