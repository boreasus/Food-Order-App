package poyraz.apps.foodorder.ui.fragment

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import poyraz.apps.foodorder.R
import poyraz.apps.foodorder.data.entity.Foods
import poyraz.apps.foodorder.databinding.FragmentDetailsBinding
import poyraz.apps.foodorder.ui.viewmodel.DetailsFragmentViewModel
import poyraz.apps.foodorder.ui.viewmodel.MainpageFragmentViewModel


@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel:DetailsFragmentViewModel
    var url = "http://kasimadalan.pe.hu/yemekler/resimler/"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_details,container,false)
        binding.detailsFragment = this
        val bundle:DetailsFragmentArgs by navArgs()
        val navFood = bundle.foodDetails
        binding.foodObject = navFood



        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation



        url = url.plus(bundle.foodDetails.yemek_resim_adi)
        Picasso.get().load(url).into(binding.DetailsFoodImage)




        viewModel.foodCount.observe(viewLifecycleOwner){
            binding.textViewTopBasket.text = it
        }


        binding.BasketTop.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.details2basket)
        }
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: DetailsFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }


    fun mpClick(count:String,operator:String){ //minus plus click
        binding.foodCount.text = viewModel.mpClick(count,operator)
    }

    fun addToCart(food:Foods,count:String){
        viewModel.addToCart(food,count)
    }



}