package com.dst.dailyjournal.ui.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dst.dailyjournal.R
import com.dst.dailyjournal.databinding.FragmentDiaryBinding
import com.dst.dailyjournal.diary.domain.model.Note
import com.dst.dailyjournal.eating.domain.model.Eating
import com.dst.dailyjournal.ui.eating.EatingViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class DiaryFragment : Fragment() {

    private var _binding: FragmentDiaryBinding? = null

    private val binding get() = _binding!!

    private val diaryViewModel: DiaryViewModel by activityViewModels()
    private lateinit var note: Note


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiaryBinding.inflate(inflater, container, false)

        val root = binding.root

        setupOnClickListeners()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments?.getLong("date") != null) {
            diaryViewModel.setCurrentDate(Date(arguments?.getLong("date")!!))
        }

        diaryViewModel.loadCurrentDayDiary()

        diaryViewModel.currentDiary.observe(viewLifecycleOwner) {
            note = it
            updateDiaryScreen()
        }
    }

    private fun updateDiaryScreen() {

        val dateString = note.noteDate.dateToString("dd MMM yyyy")
        binding.tvDiaryDate.text = dateString
        binding.etText.setText(note.text)
    }

    private fun setupOnClickListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_diary_to_navigation_home)
        }

        binding.btnSave.setOnClickListener {
            diaryViewModel.diaryText = binding.etText.text.toString()

            diaryViewModel.saveDiary()
            Snackbar.make(binding.root, "Saved", Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_navigation_diary_to_navigation_home)
        }
    }

    private fun Date.dateToString(format: String): String {
        val dateFormatter = SimpleDateFormat(format, Locale.getDefault())

        return dateFormatter.format(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
