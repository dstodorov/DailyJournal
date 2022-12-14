package com.dst.dailyjournal.ui.training

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dst.dailyjournal.R
import com.dst.dailyjournal.databinding.FragmentTrainingBinding
import com.dst.dailyjournal.ui.settings.SettingsViewModel

class TrainingFragment : Fragment() {
    // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding: FragmentTrainingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val trainingViewModel =
            ViewModelProvider(this).get(TrainingViewModel::class.java)

        _binding = FragmentTrainingBinding.inflate(inflater, container, false)

        val root: View = binding.root
//
//        val textView: TextView = binding.textTraining
//        trainingViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_training_to_navigation_home)
        }
        binding.btnStrengthNone.setOnClickListener {
            binding.strengthNoneLine.visibility = View.VISIBLE
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}