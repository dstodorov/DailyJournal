package com.dst.dailyjournal.ui.eating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dst.dailyjournal.R
import com.dst.dailyjournal.databinding.FragmentEatingBinding
import com.dst.dailyjournal.eating.domain.model.Eating
import com.dst.dailyjournal.eating.domain.model.EatingState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class EatingFragment : Fragment() {

    private var _binding: FragmentEatingBinding? = null

    private val binding get() = _binding!!
    private val eatingViewModel: EatingViewModel by activityViewModels()
    private lateinit var eating: Eating

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEatingBinding.inflate(inflater, container, false)

        val root = binding.root
        setupOnClickListeners()


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments?.getLong("date") != null) {
            eatingViewModel.setCurrentDate(Date(arguments?.getLong("date")!!))
        }

        eatingViewModel.loadCurrentDayEating()

        eatingViewModel.currentEating.observe(viewLifecycleOwner) {
            eating = it
            updateEatingScreen()
        }


    }

    private fun updateEatingScreen() {
        val dateString = eating.eatingDate.dateToString("dd MMM yyyy")
        binding.tvEatingDate.text = dateString

        when (eating.eatingState) {
            EatingState.NONE -> {
                changeEatingStatus(binding.ivEatingStatusNotDone, binding.ivEatingStatusDone)
            }
            EatingState.BAD -> {
                switchEatingButton(
                    binding.btnEatingBad,
                    binding.btnEatingAverage,
                    binding.btnEatingGreat,
                    EatingState.BAD
                )
                changeEatingStatus(binding.ivEatingStatusDone, binding.ivEatingStatusNotDone)
            }
            EatingState.AVERAGE -> {
                switchEatingButton(
                    binding.btnEatingAverage,
                    binding.btnEatingBad,
                    binding.btnEatingGreat,
                    EatingState.AVERAGE
                )
                changeEatingStatus(binding.ivEatingStatusDone, binding.ivEatingStatusNotDone)
            }
            EatingState.GREAT -> {
                switchEatingButton(
                    binding.btnEatingGreat,
                    binding.btnEatingBad,
                    binding.btnEatingAverage,
                    EatingState.GREAT
                )
                changeEatingStatus(binding.ivEatingStatusDone, binding.ivEatingStatusNotDone)
            }
        }
    }

    private fun switchEatingButton(
        clickedButton: Button,
        defaultButtonOne: Button,
        defaultButtonTwo: Button,
        toState: EatingState
    ) {
        if (eatingViewModel.eatingState == toState) {
            eatingViewModel.eatingState = EatingState.NONE
            setDefaultButtonStyle(clickedButton)
            changeEatingStatus(binding.ivEatingStatusNotDone, binding.ivEatingStatusDone)
            return
        }

        changeEatingStatus(binding.ivEatingStatusDone, binding.ivEatingStatusNotDone)
        setClickedButtonStyle(clickedButton)
        setDefaultButtonStyle(defaultButtonOne)
        setDefaultButtonStyle(defaultButtonTwo)
        eatingViewModel.eatingState = toState
    }

    private fun setupOnClickListeners() {

        binding.btnEatingBad.setOnClickListener {
            switchEatingButton(
                clickedButton = binding.btnEatingBad,
                defaultButtonOne = binding.btnEatingAverage,
                defaultButtonTwo = binding.btnEatingGreat,
                toState = EatingState.BAD
            )
        }

        binding.btnEatingAverage.setOnClickListener {
            switchEatingButton(
                clickedButton = binding.btnEatingAverage,
                defaultButtonOne = binding.btnEatingBad,
                defaultButtonTwo = binding.btnEatingGreat,
                toState = EatingState.AVERAGE
            )
        }

        binding.btnEatingGreat.setOnClickListener {
            switchEatingButton(
                clickedButton = binding.btnEatingGreat,
                defaultButtonOne = binding.btnEatingAverage,
                defaultButtonTwo = binding.btnEatingBad,
                toState = EatingState.GREAT
            )
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_eating_to_navigation_home)
        }

        binding.btnSave.setOnClickListener {
            eatingViewModel.saveEating()
            Snackbar.make(binding.root, "Saved", Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_navigation_eating_to_navigation_home)
        }
    }

    private fun Date.dateToString(format: String): String {
        val dateFormatter = SimpleDateFormat(format, Locale.getDefault())

        return dateFormatter.format(this)
    }

    private fun changeEatingStatus(visibleIndicator: ImageView, invisibleIndicator: ImageView) {
        visibleIndicator.visibility = View.VISIBLE
        invisibleIndicator.visibility = View.GONE
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