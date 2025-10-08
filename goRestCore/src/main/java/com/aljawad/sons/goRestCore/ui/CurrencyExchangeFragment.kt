package com.aljawad.sons.goRestCore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.aljawad.sons.goRestCore.databinding.FragmentCurrencyExchangeBinding
import com.aljawad.sons.goRestCore.databinding.FragmentUserListBinding
import com.aljawad.sons.goRestCore.viewModels.CurrencyExchangeViewModel
import com.aljawad.sons.goRestCore.viewModels.UseViewModel

class CurrencyExchangeFragment : Fragment() {

    private val currencyExchangeViewModel: CurrencyExchangeViewModel by activityViewModels()

    private lateinit var binding: FragmentCurrencyExchangeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyExchangeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.currencyPostModel = currencyExchangeViewModel.postModel

        binding.calculateBtn.setOnClickListener {
            binding.currencyPostModel?.apply {
                currencyExchangeViewModel.exchange(this)
            }

        }

    }

}