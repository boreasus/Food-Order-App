package poyraz.apps.foodorder.ui.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import poyraz.apps.foodorder.R
import poyraz.apps.foodorder.data.entity.Foods
import poyraz.apps.foodorder.databinding.FoodCardBinding
import poyraz.apps.foodorder.ui.fragment.MainpageFragmentDirections
import poyraz.apps.foodorder.ui.viewmodel.MainpageFragmentViewModel

class MainPageFoodAdapter (var mContext:Context,
                           var foodList:List<Foods>,
                           var viewModel: MainpageFragmentViewModel)
    :RecyclerView.Adapter<MainPageFoodAdapter.CardHolder>(){


    inner class CardHolder(binding: FoodCardBinding):RecyclerView.ViewHolder(binding.root){
        var binding:FoodCardBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding:FoodCardBinding = DataBindingUtil.inflate(layoutInflater, R.layout.food_card,parent,false)
        return CardHolder(binding)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        var url = "http://kasimadalan.pe.hu/yemekler/resimler/"
        val food = foodList.get(position)
        val h = holder.binding
        url = url.plus(food.yemek_resim_adi)
        h.FoodCardNameText.text = food.yemek_adi
        h.FoodCardPriceText.text = "₺${food.yemek_fiyat}"
        Picasso.get().load(url).into(h.FoodCardImageView)

        h.FoodCardImageView.transitionName = h.FoodCardImageView.transitionName.plus("${position}")
        h.FoodCardNameText.transitionName = h.FoodCardNameText.transitionName.plus("${position}")
        h.FoodCardPriceText.transitionName = h.FoodCardPriceText.transitionName.plus("${position}")

        h.foodCard.setOnClickListener {
            val navigateWithData = MainpageFragmentDirections.mainpage2details(foodDetails = food)
            //Navigation.findNavController(it).navigate(navigateWithData)
            //val bundle = Bundle().putSerializable("food",food)
            val extras = FragmentNavigatorExtras(h.FoodCardImageView to "image_big",h.FoodCardNameText to "name_big")
            Navigation.findNavController(it).navigate(
                 navigateWithData,
                extras,
            )
        }


    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}