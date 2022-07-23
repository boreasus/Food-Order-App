package poyraz.apps.foodorder.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import poyraz.apps.foodorder.data.entity.BasketFoods
import poyraz.apps.foodorder.data.entity.Foods
import poyraz.apps.foodorder.data.repo.FoodsDaoRepository
import javax.inject.Inject


@HiltViewModel
class BasketFragmentViewModel @Inject constructor(var frepo: FoodsDaoRepository): ViewModel(){
    var basketFoodList = MutableLiveData<List<BasketFoods>>()
    var price = MutableLiveData<String>()

    init {
        getAllFoods()
        basketFoodList = frepo.getBasketFoods()
        price = frepo.returnPrice()
    }



    fun getAllFoods(){
        frepo.getFoodsFromBasket()
    }

    fun deleteFoodFromBasket(sepet_yemek_id:String,extract: String){
        frepo.deleteFoodFromBasket(sepet_yemek_id,extract)
        getAllFoods()
    }

    fun exractPrice(extract:String){
        frepo.extractPrice(extract)
    }

}