package poyraz.apps.foodorder.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import poyraz.apps.foodorder.R
import poyraz.apps.foodorder.databinding.FragmentBasketBinding
import poyraz.apps.foodorder.ui.adapter.BasketPageFoodAdapter
import poyraz.apps.foodorder.ui.viewmodel.BasketFragmentViewModel

@AndroidEntryPoint
class BasketFragment : Fragment() {
    private lateinit var binding:FragmentBasketBinding
    private lateinit var viewModel:BasketFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_basket,container,false)
        binding.basketFragment = this


       viewModel.basketFoodList.observe(viewLifecycleOwner){
           Log.e("safa","${it.size}")
           val adapter = BasketPageFoodAdapter(requireContext(),it,viewModel)
           Log.e("safa","calisti")
           binding.adapter = adapter
       }


        viewModel.price.observe(viewLifecycleOwner){
            binding.Price.text = "₺${it}"
        }


        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:BasketFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

}