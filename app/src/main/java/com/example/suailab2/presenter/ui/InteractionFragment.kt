package com.example.suailab2.presenter.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.suailab2.R
import com.example.suailab2.databinding.FragmentInteractionBinding
import com.example.suailab2.di.App
import com.example.suailab2.presenter.ExceptionState
import com.example.suailab2.presenter.viewmodel.InteractionFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class InteractionFragment : Fragment(R.layout.fragment_interaction) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: InteractionFragmentViewModel
    by viewModels{viewModelFactory}

    private lateinit var binding: FragmentInteractionBinding

    private var snackBar: Snackbar? = null



    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInteractionBinding.bind(view)
        setOnSendButtonClickListener()
        setOnGetSavedButton()
        setExceptionObserver()
        startAutoSender()
    }

    private fun setOnSendButtonClickListener(){
        binding.sendButton.setOnClickListener {
            val url:String? = binding.urlField.text.toString().ifBlank{null}
            val valueStr:String = binding.valueField.text.toString().ifBlank { "0.0" }
            val value:Double = valueStr.toDouble()
            viewModel.sendValue(value, url)
        }
    }

    private fun setOnGetSavedButton(){
        binding.getSavedButton.setOnClickListener {
            viewModel.printSavedValues()
        }
    }

    private fun setExceptionObserver(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.exceptionsState.collect{
                    Log.d("state", "collect")
                        if(it is ExceptionState.Exception )
                            showSnackBar(getString(R.string.exception_message))
                }
            }
        }
    }

    private fun startAutoSender(){
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO){
            repeatOnLifecycle(Lifecycle.State.STARTED){
                while (true) {
                    viewModel.sendRandomValue()
                    delay(30000)
                }
            }
        }
    }
    private fun showSnackBar(message: String){
        snackBar = Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
        snackBar?.show()
    }
}