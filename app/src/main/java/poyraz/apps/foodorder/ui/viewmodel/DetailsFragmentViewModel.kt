package poyraz.apps.foodorder.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import poyraz.apps.foodorder.data.entity.Foods
import poyraz.apps.foodorder.data.repo.FoodsDaoRepository
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(var frepo: FoodsDaoRepository): ViewModel(){

    var foodCount = MutableLiveData<String>()
    //var price = MutableLiveData<String>()
    init {
        //price = frepo.returnPrice()
        foodCount = frepo.returnFoodCount()
    }


    fun mpClick(count:String,operator:String):String //minus plus click
    {
       return frepo.mpClick(count,operator)
    }

    fun addToCart(food: Foods, count:String){
        frepo.addToCart(food,count)
        for(i in 1..count.toInt()) {
            frepo.addFoodtoBasket(food.yemek_adi,food.yemek_resim_adi,food.yemek_fiyat,1)
            frepo.addPrice(food.yemek_fiyat.toString())
        }
    }

    fun returnPrice(){
       // price = frepo.returnPrice()
    }

    fun addPrice(add:String){
        frepo.addPrice(add)
    }




}