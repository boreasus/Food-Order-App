package poyraz.apps.foodorder.retrofit

import poyraz.apps.foodorder.data.entity.BasketResponse
import poyraz.apps.foodorder.data.entity.CRUDResponse
import poyraz.apps.foodorder.data.entity.FoodsResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodsDao {

    @GET("yemekler/tumYemekleriGetir.php")
    fun allFoods() : Call<FoodsResponse>

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun addFoodtoBasket(@Field("yemek_adi") yemek_adi:String,
                        @Field("yemek_resim_adi") yemek_resim_adi:String,
                        @Field("yemek_fiyat") yemek_fiyat:Int,
                        @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                        @Field("kullanici_adi") kullanici_adi:String) :Call<CRUDResponse>

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun getFoodsFromBasket(@Field("kullanici_adi") kullanici_adi: String):Call<BasketResponse>


    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun deleteFoodFromBasket(@Field("sepet_yemek_id")sepet_yemek_id:String,
                             @Field("kullanici_adi")kullanici_adi: String):Call<CRUDResponse>
}