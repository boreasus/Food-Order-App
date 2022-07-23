package poyraz.apps.foodorder.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import poyraz.apps.foodorder.R
import poyraz.apps.foodorder.databinding.FragmentMainpageBinding
import poyraz.apps.foodorder.ui.adapter.MainPageFoodAdapter
import poyraz.apps.foodorder.ui.viewmodel.MainpageFragmentViewModel

@AndroidEntryPoint
class MainpageFragment : Fragment() {
    private lateinit var binding:FragmentMainpageBinding
    private lateinit var viewModel:MainpageFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_mainpage,container,false)
        binding.mainPageFragment = this


        viewModel.foodList.observe(viewLifecycleOwner){
            val adapter = MainPageFoodAdapter(requireContext(),it,viewModel)
            binding.foodsAdapter = adapter
        }



        viewModel.foodCount.observe(viewLifecycleOwner){
            binding.textViewTopBasket.text = it
        }

        binding.basket.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.mainpage2basket)
        }

        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.mainpage2basket)
        }

        return binding.root
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel:MainpageFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }




}