package com.dst.dailyjournal.ui.training

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dst.dailyjournal.R
import com.dst.dailyjournal.databinding.FragmentTrainingBinding
import com.dst.dailyjournal.training.domain.model.CardioTrainingState
import com.dst.dailyjournal.training.domain.model.StepsState
import com.dst.dailyjournal.training.domain.model.StrengthTrainingState
import com.dst.dailyjournal.training.domain.model.Training
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class TrainingFragment : Fragment() {

    private var _binding: FragmentTrainingBinding? = null

    private val binding get() = _binding!!
    private val trainingViewModel: TrainingViewModel by activityViewModels()
    private lateinit var training: Training

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTrainingBinding.inflate(inflater, container, false)

        val root: View = binding.root
        setupOnClickListeners()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        trainingViewModel.loadCurrentDayTraining()

        trainingViewModel.currentTraining.observe(viewLifecycleOwner) {
            training = it
            updateTrainingScreen()
        }
    }

    private fun updateTrainingScreen() {
        val dateString = training.trainingDate.dateToString("dd MMM yyyy")
        binding.tvTrainingDate.text = dateString
        binding.etSteps.setText(training.dailySteps.toString())

        when (training.strengthTraining) {
            StrengthTrainingState.NONE -> {
                changeActivityStatus(binding.ivStrengthStatusNotDone, binding.ivStrengthStatusDone)
            }
            StrengthTrainingState.LIGHT -> {
                switchStrengthButton(
                    binding.btnStrengthLight,
                    binding.btnStrengthModerate,
                    binding.btnStrengthHeavy,
                    StrengthTrainingState.LIGHT
                )
                changeActivityStatus(binding.ivStrengthStatusDone, binding.ivStrengthStatusNotDone)
            }
            StrengthTrainingState.MODERATE -> {
                switchStrengthButton(
                    binding.btnStrengthModerate,
                    binding.btnStrengthLight,
                    binding.btnStrengthHeavy,
                    StrengthTrainingState.MODERATE
                )
                changeActivityStatus(binding.ivStrengthStatusDone, binding.ivStrengthStatusNotDone)
            }
            StrengthTrainingState.HEAVY -> {
                switchStrengthButton(
                    binding.btnStrengthHeavy,
                    binding.btnStrengthLight,
                    binding.btnStrengthModerate,
                    StrengthTrainingState.HEAVY
                )
                changeActivityStatus(binding.ivStrengthStatusDone, binding.ivStrengthStatusNotDone)
            }
        }

        when (training.cardioTraining) {
            CardioTrainingState.NONE -> {
                changeActivityStatus(binding.ivCardioStatusNotDone, binding.ivCardioStatusDone)
            }
            CardioTrainingState.LIGHT -> {
                switchCardioButton(
                    binding.btnCardioLight,
                    binding.btnCardioModerate,
                    binding.btnCardioVigorous,
                    CardioTrainingState.LIGHT
                )
                changeActivityStatus(binding.ivCardioStatusDone, binding.ivCardioStatusNotDone)
            }
            CardioTrainingState.MODERATE -> {
                switchCardioButton(
                    binding.btnCardioModerate,
                    binding.btnCardioLight,
                    binding.btnCardioVigorous,
                    CardioTrainingState.MODERATE
                )
                changeActivityStatus(binding.ivCardioStatusDone, binding.ivCardioStatusNotDone)
            }
            CardioTrainingState.VIGOROUS -> {
                switchCardioButton(
                    binding.btnCardioVigorous,
                    binding.btnCardioLight,
                    binding.btnCardioModerate,
                    CardioTrainingState.VIGOROUS
                )
                changeActivityStatus(binding.ivCardioStatusDone, binding.ivCardioStatusNotDone)
            }
        }

        when (training.dailyStepsStatus) {
            StepsState.NOT_DONE -> {
                switchStepsButton(
                    binding.btnStepsNotCompleted,
                    binding.btnStepsCompleted,
                    StepsState.NOT_DONE
                )
                changeActivityStatus(binding.ivStepsStatusNotDone, binding.ivStepsStatusDone)
            }
            StepsState.DONE -> {
                switchStepsButton(
                    binding.btnStepsCompleted,
                    binding.btnStepsNotCompleted,
                    StepsState.DONE
                )
                changeActivityStatus(binding.ivStepsStatusDone, binding.ivStepsStatusNotDone)
            }
        }
    }

    private fun changeActivityStatus(visibleIndicator: ImageView, invisibleIndicator: ImageView) {
        visibleIndicator.visibility = View.VISIBLE
        invisibleIndicator.visibility = View.GONE
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

        binding.btnStepsNotCompleted.setOnClickListener {
            switchStepsButton(
                clickedButton = binding.btnStepsNotCompleted,
                defaultButton = binding.btnStepsCompleted,
                toState = StepsState.NOT_DONE
            )
        }

        binding.btnStepsCompleted.setOnClickListener {
            switchStepsButton(
                clickedButton = binding.btnStepsCompleted,
                defaultButton = binding.btnStepsNotCompleted,
                toState = StepsState.DONE
            )
        }

        binding.etSteps.addTextChangedListener {
            if (binding.etSteps.text.isNotBlank())
                trainingViewModel.dailyStepsCount = binding.etSteps.text.toString().toInt()
        }

        binding.btnSave.setOnClickListener {
            trainingViewModel.saveTraining()
            Snackbar.make(binding.root, "Saved", Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_navigation_training_to_navigation_home)
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
            changeActivityStatus(binding.ivStrengthStatusNotDone, binding.ivStrengthStatusDone)
            return
        }

        changeActivityStatus(binding.ivStrengthStatusDone, binding.ivStrengthStatusNotDone)
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
            changeActivityStatus(binding.ivCardioStatusNotDone, binding.ivCardioStatusDone)
            return
        }

        changeActivityStatus(binding.ivCardioStatusDone, binding.ivCardioStatusNotDone)
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
        if (toState == StepsState.NOT_DONE) {
            changeActivityStatus(binding.ivStepsStatusNotDone, binding.ivStepsStatusDone)
        } else {
            changeActivityStatus(binding.ivStepsStatusDone, binding.ivStepsStatusNotDone)
        }
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

    private fun Date.dateToString(format: String): String {
        val dateFormatter = SimpleDateFormat(format, Locale.getDefault())

        return dateFormatter.format(this)
    }

}