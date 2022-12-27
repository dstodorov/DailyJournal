package com.dst.dailyjournal.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.applandeo.materialcalendarview.listeners.OnDayLongClickListener
import com.dst.dailyjournal.databinding.FragmentCalendarBinding


class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val calendarViewModel =
            ViewModelProvider(this).get(CalendarViewModel::class.java)

        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCalendar
        calendarViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val calendar = Calendar.getInstance()
//        val events = mutableListOf<EventDay>()
//
//
//        val bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.ic_dot)
//        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.ic_dot)
//        val bitmap3 = BitmapFactory.decodeResource(resources, R.drawable.ic_dot)
//
//        val layer1 = BitmapDrawable(
//            requireContext().resources,
//            bitmap1
//        )
//        val layer2 = BitmapDrawable(
//            requireContext().resources,
//            bitmap2
//        )
//        val layer3 = BitmapDrawable(
//            requireContext().resources,
//            bitmap3
//        )
//        val layers = arrayOf(layer1, layer2, layer3)
//
//        val layerDrawable = LayerDrawable(layers)
//
//        events.add(EventDay(calendar, layerDrawable))
//
//        binding.calendarView.setEvents(events)

        binding.calendarView.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: EventDay) {
                println(eventDay.calendar.time)
            }

        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}