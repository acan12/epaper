package app.epaper.com.bolang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import app.beelabs.com.codebase.base.BaseFragment
import app.epaper.com.bolang.R
import app.epaper.com.bolang.databinding.FragmentSubscribeBinding
import app.epaper.com.bolang.ui.adapter.PaketCardAdapter
import app.epaper.com.bolang.ui.dialog.SubscribeOfferDialog
import app.epaper.com.bolang.ui.dialog.SubscribeProcessingDialog

class SubscribeFragment : BaseFragment() {
    private lateinit var binding: FragmentSubscribeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubscribeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding) {

        rvGridPackage.layoutManager = GridLayoutManager(requireContext(), 2)
        rvGridPackage.adapter =
            PaketCardAdapter(null, binding.root, this@SubscribeFragment.resources)
        btnNext.setOnClickListener {
            SubscribeProcessingDialog(root, R.style.CoconutDialogFullScreen).show()
        }
    }
}