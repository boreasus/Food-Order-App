package poyraz.apps.foodorder.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import poyraz.apps.foodorder.R
import poyraz.apps.foodorder.data.entity.BasketFoods
import poyraz.apps.foodorder.databinding.BasketCardBinding
import poyraz.apps.foodorder.ui.viewmodel.BasketFragmentViewModel

class BasketPageFoodAdapter(var mContext:Context,
                            var foodList: List<BasketFoods>,
                            var viewModel:BasketFragmentViewModel):RecyclerView.Adapter<BasketPageFoodAdapter.CardHolder>() {



    inner class CardHolder(binding: BasketCardBinding):RecyclerView.ViewHolder(binding.root){
        var binding:BasketCardBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding:BasketCardBinding = DataBindingUtil.inflate(layoutInflater, R.layout.basket_card,parent,false)
        return CardHolder(binding)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        var url = "http://kasimadalan.pe.hu/yemekler/resimler/"
        val food = foodList.get(position)
        val h = holder.binding
        url = url.plus(food.yemek_resim_adi)
        h.BasketCardFoodName.text = food.yemek_adi
        h.BasketCardPrice.text = ("₺${food.yemek_fiyat.toString()}")
        Picasso.get().load(url).into(h.imageView3)




        h.delete.setOnClickListener {
            Snackbar.make(it,"Do you want to delete ${food.yemek_adi}",Snackbar.LENGTH_SHORT).setAction("Evet",
                View.OnClickListener {
                    viewModel.deleteFoodFromBasket(food.yemek_id.toString(),food.yemek_fiyat.toString())
                        foodList
                        notifyItemRemoved(position)
                        notifyDataSetChanged()




                }).show()
        }

    }

    override fun getItemCount(): Int {
        return foodList.size
    }


}