package app.epaper.com.bolang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import app.beelabs.com.codebase.base.BaseFragment
import app.epaper.com.bolang.databinding.FragmentListPdfeditionBinding
import app.epaper.com.bolang.ui.adapter.EpaperEditionAdapter

class ListPdfEditionFragment : BaseFragment() {
    private lateinit var binding : FragmentListPdfeditionBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListPdfeditionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUI()
    }

    private fun setupUI() = with(binding){
        rvList.layoutManager = LinearLayoutManager(activity)
        rvList.adapter = EpaperEditionAdapter(null, currentActivity)
    }
}