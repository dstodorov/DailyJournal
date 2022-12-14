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

        trainingViewModel.currentDate.observe(viewLifecycleOwner) {
            binding.tvTrainingDate.text = it
        }
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
            binding.strengthLightLine.visibility = View.GONE
            binding.strengthStrongLine.visibility = View.GONE
        }

        binding.btnStrengthLight.setOnClickListener {
            binding.strengthNoneLine.visibility = View.GONE
            binding.strengthLightLine.visibility = View.VISIBLE
            binding.strengthStrongLine.visibility = View.GONE
        }

        binding.btnStrengthStrong.setOnClickListener {
            binding.strengthNoneLine.visibility = View.GONE
            binding.strengthLightLine.visibility = View.GONE
            binding.strengthStrongLine.visibility = View.VISIBLE
        }

        binding.btnCardioLight.setOnClickListener {
            binding.cardioLightLine.visibility = View.VISIBLE
            binding.cardioModerateLine.visibility = View.GONE
            binding.cardioVigorousLine.visibility = View.GONE
        }

        binding.btnCardioModerate.setOnClickListener {
            binding.cardioLightLine.visibility = View.GONE
            binding.cardioModerateLine.visibility = View.VISIBLE
            binding.cardioVigorousLine.visibility = View.GONE
        }

        binding.btnCardioVigorous.setOnClickListener {
            binding.cardioLightLine.visibility = View.GONE
            binding.cardioModerateLine.visibility = View.GONE
            binding.cardioVigorousLine.visibility = View.VISIBLE
        }

        binding.btnStepsNotDone.setOnClickListener {
            binding.stepsNotDoneLine.visibility = View.VISIBLE
            binding.stepsDoneLine.visibility = View.GONE
        }

        binding.btnStepsDone.setOnClickListener {
            binding.stepsNotDoneLine.visibility = View.GONE
            binding.stepsDoneLine.visibility = View.VISIBLE
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}