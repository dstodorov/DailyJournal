package com.dst.dailyjournal.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.applandeo.materialcalendarview.EventDay
import com.dst.dailyjournal.R
import com.dst.dailyjournal.core.data.data_source.DailyQuotes
import com.dst.dailyjournal.databinding.FragmentHomeBinding
import com.dst.dailyjournal.diary.domain.model.Note
import com.dst.dailyjournal.eating.domain.model.Eating
import com.dst.dailyjournal.training.domain.model.Training
import com.dst.dailyjournal.ui.diary.DiaryViewModel
import com.dst.dailyjournal.ui.eating.EatingViewModel
import com.dst.dailyjournal.ui.training.TrainingViewModel
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by activityViewModels()
    private val trainingViewModel: TrainingViewModel by activityViewModels()
    private val diaryViewModel: DiaryViewModel by activityViewModels()
    private val eatingViewModel: EatingViewModel by activityViewModels()

    lateinit var training: Training
    lateinit var diary: Note
    lateinit var eating: Eating

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val quote = DailyQuotes.getRandomQuote()

        binding.tvQuote.text = quote.quote
        binding.tvAuthor.text = quote.author

        if (arguments?.getLong("date") != null) {
            homeViewModel.setBundle(requireArguments())
            homeViewModel.setCurrentDate(Date(arguments?.getLong("date")!!))
        }

        homeViewModel.currentDate.observe(viewLifecycleOwner) {
            binding.tvDate.text = it
            trainingViewModel.setCurrentDate(homeViewModel.date!!)
            eatingViewModel.setCurrentDate(homeViewModel.date!!)
            diaryViewModel.setCurrentDate(homeViewModel.date!!)

            trainingViewModel.loadCurrentDayTraining()
            eatingViewModel.loadCurrentDayEating()
            diaryViewModel.loadCurrentDayDiary()
        }

        trainingViewModel.currentTraining.observe(viewLifecycleOwner) {
            binding.tvStrengthStatus.text = it.strengthTraining.name
            trainingViewModel.strengthTrainingState = it.strengthTraining

            binding.tvCardioStatus.text = it.cardioTraining.name
            trainingViewModel.cardioTrainingState = it.cardioTraining

            binding.tvSteps.text = it.dailySteps.toString()
            trainingViewModel.dailyStepsStatus = it.dailyStepsStatus
        }

        eatingViewModel.currentEating.observe(viewLifecycleOwner) {
            binding.tvEatingStatus.text = it.eatingState.name
            eatingViewModel.eatingState = it.eatingState
        }

        diaryViewModel.currentDiary.observe(viewLifecycleOwner) {
            binding.etDiary.text = it.text
        }

        setupOnClickListeners()
    }


    private fun setupOnClickListeners() {

        binding.btnTraining.setOnClickListener {
            if (homeViewModel.bundle.containsKey("date")) {
                findNavController().navigate(
                    R.id.action_navigation_home_to_navigation_training,
                    homeViewModel.bundle
                )
            } else {
                findNavController().navigate(
                    R.id.action_navigation_home_to_navigation_training
                )
            }
        }
        binding.btnDiary.setOnClickListener {
            if (homeViewModel.bundle.containsKey("date")) {
                findNavController().navigate(
                    R.id.action_navigation_home_to_navigation_diary,
                    homeViewModel.bundle
                )
            } else {
                findNavController().navigate(
                    R.id.action_navigation_home_to_navigation_diary
                )
            }
        }

        binding.btnEating.setOnClickListener {
            if (homeViewModel.bundle.containsKey("date")) {
                findNavController().navigate(
                    R.id.action_navigation_home_to_navigation_eating,
                    homeViewModel.bundle
                )
            } else {
                findNavController().navigate(
                    R.id.action_navigation_home_to_navigation_eating
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}