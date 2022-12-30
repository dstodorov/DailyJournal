package com.dst.dailyjournal.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.applandeo.materialcalendarview.EventDay
import com.dst.dailyjournal.R
import com.dst.dailyjournal.databinding.FragmentHomeBinding
import com.dst.dailyjournal.ui.training.TrainingViewModel
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by activityViewModels()

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

        if (arguments?.getLong("date") != null) {
            homeViewModel.setBundle(requireArguments())
            homeViewModel.setCurrentDate(Date(arguments?.getLong("date")!!))
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