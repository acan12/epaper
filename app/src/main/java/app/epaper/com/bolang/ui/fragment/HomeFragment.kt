package app.epaper.com.bolang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import app.beelabs.com.codebase.base.BaseFragment
import app.epaper.com.bolang.App
import app.epaper.com.bolang.databinding.FragmentHomeBinding
import app.epaper.com.bolang.databinding.FragmentLoginBinding
import app.epaper.com.bolang.ui.adapter.EpaperCardAdapter

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding){
        rvGridView.layoutManager = GridLayoutManager(activity, 3)
        rvGridView.adapter = EpaperCardAdapter(null, requireActivity())
    }
}