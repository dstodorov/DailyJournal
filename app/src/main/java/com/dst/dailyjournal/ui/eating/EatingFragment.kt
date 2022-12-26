package com.dst.dailyjournal.ui.eating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dst.dailyjournal.databinding.FragmentEatingBinding
import com.dst.dailyjournal.ui.diary.DiaryViewModel

class EatingFragment : Fragment() {
    private var _binding: FragmentEatingBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEatingBinding.inflate(inflater, container, false)

        val root = binding.root

        val diaryViewModel =
            ViewModelProvider(this)[EatingViewModel::class.java]


        return root
    }
}