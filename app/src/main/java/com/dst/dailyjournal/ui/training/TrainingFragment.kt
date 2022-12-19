package com.dst.dailyjournal.ui.training

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dst.dailyjournal.R
import com.dst.dailyjournal.databinding.FragmentTrainingBinding
import com.dst.dailyjournal.training.domain.model.CardioTrainingState
import com.dst.dailyjournal.training.domain.model.StepsState
import com.dst.dailyjournal.training.domain.model.StrengthTrainingState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrainingFragment : Fragment() {
    // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding: FragmentTrainingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val trainingViewModel: TrainingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val trainingViewModel = ViewModelProvider(this).get(TrainingViewModel::class.java)


        _binding = FragmentTrainingBinding.inflate(inflater, container, false)

        val root: View = binding.root

        trainingViewModel.currentDate.observe(viewLifecycleOwner) {
            binding.tvTrainingDate.text = it
        }

        setClickedButtonStyle(binding.btnStepsNotDone)

        setupOnClickListeners()

        return root
    }

    private fun setupOnClickListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_training_to_navigation_home)
        }
        binding.btnStrengthLight.setOnClickListener {
            switchStrengthButton(
                clickedButton = binding.btnStrengthLight,
                defaultButtonOne = binding.btnStrengthModerate,
                defaultButtonTwo = binding.btnStrengthHeavy,
                toState = StrengthTrainingState.LIGHT
            )
        }

        binding.btnStrengthModerate.setOnClickListener {
            switchStrengthButton(
                clickedButton = binding.btnStrengthModerate,
                defaultButtonOne = binding.btnStrengthLight,
                defaultButtonTwo = binding.btnStrengthHeavy,
                toState = StrengthTrainingState.MODERATE
            )
        }

        binding.btnStrengthHeavy.setOnClickListener {
            switchStrengthButton(
                clickedButton = binding.btnStrengthHeavy,
                defaultButtonOne = binding.btnStrengthModerate,
                defaultButtonTwo = binding.btnStrengthLight,
                toState = StrengthTrainingState.HEAVY
            )
        }

        binding.btnCardioLight.setOnClickListener {
            switchCardioButton(
                clickedButton = binding.btnCardioLight,
                defaultButtonOne = binding.btnCardioModerate,
                defaultButtonTwo = binding.btnCardioVigorous,
                toState = CardioTrainingState.LIGHT
            )
        }

        binding.btnCardioModerate.setOnClickListener {

            switchCardioButton(
                clickedButton = binding.btnCardioModerate,
                defaultButtonOne = binding.btnCardioLight,
                defaultButtonTwo = binding.btnCardioVigorous,
                toState = CardioTrainingState.MODERATE
            )
        }

        binding.btnCardioVigorous.setOnClickListener {
            switchCardioButton(
                clickedButton = binding.btnCardioVigorous,
                defaultButtonOne = binding.btnCardioLight,
                defaultButtonTwo = binding.btnCardioModerate,
                toState = CardioTrainingState.VIGOROUS
            )
        }

        binding.btnStepsNotDone.setOnClickListener {
            switchStepsButton(
                clickedButton = binding.btnStepsNotDone,
                defaultButton = binding.btnStepsDone, StepsState.NOT_DONE
            )
        }

        binding.btnStepsDone.setOnClickListener {
            switchStepsButton(
                clickedButton = binding.btnStepsDone,
                defaultButton = binding.btnStepsNotDone, StepsState.DONE
            )
        }
    }

    private fun switchStrengthButton(
        clickedButton: Button,
        defaultButtonOne: Button,
        defaultButtonTwo: Button,
        toState: StrengthTrainingState
    ) {

        if (trainingViewModel.strengthTrainingState == toState) {
            trainingViewModel.strengthTrainingState = StrengthTrainingState.NONE
            setDefaultButtonStyle(clickedButton)
            return
        }

        setClickedButtonStyle(clickedButton)
        setDefaultButtonStyle(defaultButtonOne)
        setDefaultButtonStyle(defaultButtonTwo)
        trainingViewModel.strengthTrainingState = toState
    }

    private fun switchCardioButton(
        clickedButton: Button,
        defaultButtonOne: Button,
        defaultButtonTwo: Button,
        toState: CardioTrainingState
    ) {

        if (trainingViewModel.cardioTrainingState == toState) {
            trainingViewModel.cardioTrainingState = CardioTrainingState.NONE
            setDefaultButtonStyle(clickedButton)
            return
        }

        setClickedButtonStyle(clickedButton)
        setDefaultButtonStyle(defaultButtonOne)
        setDefaultButtonStyle(defaultButtonTwo)
        trainingViewModel.cardioTrainingState = toState
    }

    private fun switchStepsButton(
        clickedButton: Button,
        defaultButton: Button,
        toState: StepsState
    ) {
        setClickedButtonStyle(clickedButton)
        setDefaultButtonStyle(defaultButton)
        trainingViewModel.dailyStepsStatus = toState
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