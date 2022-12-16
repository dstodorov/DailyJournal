package com.dst.dailyjournal.ui.training

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val trainingViewModel = ViewModelProvider(this).get(TrainingViewModel::class.java)

        _binding = FragmentTrainingBinding.inflate(inflater, container, false)

        val root: View = binding.root

        trainingViewModel.currentDate.observe(viewLifecycleOwner) {
            binding.tvTrainingDate.text = it
        }

        setupOnClickListeners()


        trainingViewModel.strengthTrainingStatus.observe(viewLifecycleOwner) {
            binding
        }

        return root
    }

    private fun setupOnClickListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_training_to_navigation_home)
        }
        binding.btnStrengthLight.setOnClickListener {
            setClickedButtonStyle(binding.btnStrengthLight)
            setDefaultButtonStyle(binding.btnStrengthModerate)
            setDefaultButtonStyle(binding.btnStrengthHeavy)
        }

        binding.btnStrengthModerate.setOnClickListener {
            setDefaultButtonStyle(binding.btnStrengthLight)
            setClickedButtonStyle(binding.btnStrengthModerate)
            setDefaultButtonStyle(binding.btnStrengthHeavy)
        }

        binding.btnStrengthHeavy.setOnClickListener {
            setDefaultButtonStyle(binding.btnStrengthLight)
            setDefaultButtonStyle(binding.btnStrengthModerate)
            setClickedButtonStyle(binding.btnStrengthHeavy)
        }

        binding.btnCardioLight.setOnClickListener {
            setClickedButtonStyle(binding.btnCardioLight)
            setDefaultButtonStyle(binding.btnCardioModerate)
            setDefaultButtonStyle(binding.btnCardioVigorous)
        }

        binding.btnCardioModerate.setOnClickListener {
            setDefaultButtonStyle(binding.btnCardioLight)
            setClickedButtonStyle(binding.btnCardioModerate)
            setDefaultButtonStyle(binding.btnCardioVigorous)
        }

        binding.btnCardioVigorous.setOnClickListener {
            setDefaultButtonStyle(binding.btnCardioLight)
            setDefaultButtonStyle(binding.btnCardioModerate)
            setClickedButtonStyle(binding.btnCardioVigorous)
        }

        binding.btnStepsNotDone.setOnClickListener {
            setClickedButtonStyle(binding.btnStepsNotDone)
            setDefaultButtonStyle(binding.btnStepsDone)
        }

        binding.btnStepsDone.setOnClickListener {
            setDefaultButtonStyle(binding.btnStepsNotDone)
            setClickedButtonStyle(binding.btnStepsDone)
        }
    }

    private fun setClickedButtonStyle(buttonView: Button) {
        buttonView.setBackgroundColor(
            resources.getColor(
                R.color.button_clicked_color, null
            )
        )
        buttonView.setTextColor(resources.getColor(R.color.black, null))
    }

    private fun setDefaultButtonStyle(buttonView: Button) {
        buttonView.setBackgroundColor(
            resources.getColor(
                R.color.button_default_color, null
            )
        )
        buttonView.setTextColor(resources.getColor(R.color.white, null))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}