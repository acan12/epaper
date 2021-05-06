package app.epaper.com.bolang.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import app.beelabs.com.codebase.base.BaseFragment
import app.epaper.com.bolang.R
import app.epaper.com.bolang.databinding.FragmentHomeBinding
import app.epaper.com.bolang.presenter.manager.SessionManager
import app.epaper.com.bolang.ui.adapter.EpaperCardAdapter
import app.epaper.com.bolang.ui.tool.UiUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

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

    private fun setupUI() = with(binding) {
        if (SessionManager.isSubscribe(root.context))
            imageSubscribeIndicator.setImageResource(R.drawable.ic_checked_green)

        Glide.with(requireActivity())
            .load("https://epaper.solopos.com/assets/uploads/2021/04/01/0001.jpg")
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(mainContentImage)
        mainContentImage.setOnClickListener { UiUtil.showDetailEpaper(null, root) }

        var layout = LinearLayoutManager(activity)
        layout.orientation = LinearLayoutManager.HORIZONTAL
        rvEditionView.layoutManager = layout
        rvEditionView.adapter = EpaperCardAdapter(null, requireActivity())
    }
}