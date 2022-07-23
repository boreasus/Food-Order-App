package poyraz.apps.foodorder.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import poyraz.apps.foodorder.data.entity.Foods
import poyraz.apps.foodorder.data.repo.FoodsDaoRepository
import javax.inject.Inject


@HiltViewModel
class MainpageFragmentViewModel @Inject constructor(var frepo:FoodsDaoRepository):ViewModel(){
    var foodList = MutableLiveData<List<Foods>>()
    var foodCount = MutableLiveData<String>()

    init {
        getAllFoods()
        foodList = frepo.getFoods()
        foodCount = frepo.returnFoodCount()

    }

    fun getAllFoods(){
        frepo.getAllFoods()
    }
}