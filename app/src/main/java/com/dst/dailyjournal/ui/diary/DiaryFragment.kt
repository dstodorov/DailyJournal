package com.dst.dailyjournal.ui.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dst.dailyjournal.databinding.FragmentDiaryBinding
import com.dst.dailyjournal.ui.training.TrainingViewModel

class DiaryFragment : Fragment() {

    private var _binding: FragmentDiaryBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiaryBinding.inflate(inflater, container, false)

        val root = binding.root

        val diaryViewModel =
            ViewModelProvider(this).get(DiaryViewModel::class.java)

        val textView: TextView = binding.textDiary
        diaryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }
}