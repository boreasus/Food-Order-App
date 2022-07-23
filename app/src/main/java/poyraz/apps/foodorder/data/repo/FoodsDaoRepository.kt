package poyraz.apps.foodorder.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import poyraz.apps.foodorder.data.entity.*
import poyraz.apps.foodorder.retrofit.FoodsDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodsDaoRepository (var fdao:FoodsDao){
    var foodList:MutableLiveData<List<Foods>>
    var foodCount = MutableLiveData<String>()
    var basketFoodList:MutableLiveData<List<BasketFoods>>
    var price = MutableLiveData<String>()

    val kullaniciadi = "safauslu"
    init {
        foodList = MutableLiveData()
        foodCount = MutableLiveData("0")
        basketFoodList = MutableLiveData()
        price = MutableLiveData("0")
    }

    fun getFoods():MutableLiveData<List<Foods>>{
        return foodList
    }

    fun getBasketFoods(): MutableLiveData<List<BasketFoods>>{
        return basketFoodList
    }


    fun getAllFoods(){
        fdao.allFoods().enqueue(object : Callback<FoodsResponse>{
            override fun onResponse(call: Call<FoodsResponse>?, response: Response<FoodsResponse>
            ) {
                val list = response.body().foods
                foodList.value = list
            }

            override fun onFailure(call: Call<FoodsResponse>?, t: Throwable?){}
        })
    }

    fun addFoodtoBasket(yemek_adi:String,
                        yemek_resim_adi:String,
                        yemek_fiyat:Int,
                        yemek_siparis_adet:Int){
        fdao.addFoodtoBasket(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,"safauslu").enqueue(object: Callback<CRUDResponse>{
            override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>) {
                val basari = response.body().success
                val mesaj = response.body().message
                Log.e("Yemek Kayıt","$basari - $mesaj")
            }

            override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {
            }
        })

    }

    fun deleteFoodFromBasket(sepet_yemek_id:String,extract: String){
        fdao.deleteFoodFromBasket(sepet_yemek_id,kullaniciadi).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>) {
                val basari = response.body().success
                val mesaj = response.body().message
                Log.e("Yemek Sil","$basari - $mesaj")
                getFoodsFromBasket()
                extractPrice(extract)
            }

            override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {
            }

        })
        var temp = foodCount.value
        temp = (foodCount.value!!.toInt()-1).toString()
        foodCount.value = temp
    }

    fun getFoodsFromBasket(){
        fdao.getFoodsFromBasket(kullaniciadi).enqueue(object: Callback<BasketResponse>{
            override fun onResponse(
                call: Call<BasketResponse>?,
                response: Response<BasketResponse>
            ) {
                val basketlist = response.body().foods
                basketFoodList.value = basketlist           }

            override fun onFailure(call: Call<BasketResponse>?, t: Throwable?) {
            }
        })


    }




    /////

    fun returnFoodCount():MutableLiveData<String>{
        return foodCount
    }

    fun declareFoodCount(value:String){
        foodCount.value = value
    }

    fun returnPrice():MutableLiveData<String>{
        return price
    }

    fun addPrice(add:String){
        var temp = price.value!!.toInt()
        temp+=add.toInt()
        price.value = temp.toString()
    }

    fun extractPrice(extract:String){
        var temp = price.value!!.toInt()
        temp-=extract.toInt()
        price.value = temp.toString()
    }

    fun mpClick(count:String,operator:String):String //minus plus click
    {
        var temp = count.toInt()
        if (temp == 1 && operator == "-") {
            return "1"
        } else if (operator == "-") {
            temp -= 1
            return temp.toString()
        }
        else if (operator == "+"){
            temp+=1
            return temp.toString()
        }
        return temp.toString()
    }

    fun addToCart(food: Foods, count:String){
        var temp = foodCount.value
        var result:Int = temp!!.toInt() + count.toInt()
        foodCount.value = result.toString()
    }

}