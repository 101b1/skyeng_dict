package com.ilih.skyengdict.ui.meaning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ilih.skyengdict.MainActivity
import com.ilih.skyengdict.R
import com.ilih.skyengdict.databinding.FragmentMeaningBinding
import com.ilih.skyengdict.databinding.FragmentSearchBinding
import com.ilih.skyengdict.domain.dto.SearchResultDto

const val MEANING: String = "meaning"

class MeaningFragment : Fragment() {

    private var meaning: SearchResultDto? = null

    lateinit var meaningView: MeaningView

    private var _binding: FragmentMeaningBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            meaning = it.getParcelable(MEANING)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (meaning == null){
            Toast.makeText(requireContext(), "No meaning passed", Toast.LENGTH_LONG).show()
            return null
        } else{
            _binding = FragmentMeaningBinding.inflate(inflater, container, false)
            val view = binding.root
            meaningView = MeaningViewImpl(meaning!!, binding, (activity as MainActivity).getMainComponent())
            return view
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(MEANING, meaning)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MeaningFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MEANING, meaning)
                }
            }
    }
}