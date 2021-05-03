package com.imaginato.imaginato_practical.ui.details

import android.annotation.SuppressLint
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.imaginato.imaginato_practical.R
import com.imaginato.imaginato_practical.databinding.FragmentDetailsBinding
import com.imaginato.imaginato_practical.ui.base.BaseFragment
import com.stfalcon.imageviewer.StfalconImageViewer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment :
    BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {

    private val args by navArgs<DetailsFragmentArgs>()

    override fun getClassName(): String {
        return this::class.java.simpleName
    }

    @SuppressLint("SetTextI18n")
    override fun initViews() {
        super.initViews()
        val user = args.user
        Glide.with(binding.ivUserThumb)
            .load(user.picture?.medium)
            .transform(
                RoundedCorners(
                    binding.ivUserThumb.context.resources.getDimensionPixelSize(
                        R.dimen._50sdp
                    )
                )
            )
            .into(binding.ivUserThumb)

        binding.tvName.text = "${user.name?.title} ${user.name?.first} ${user.name?.last}"
        binding.tvEmail.text = user.email
        binding.tvGender.text =  "Gender: ${user.gender}"
        binding.tvPhone.text =  "Phone: ${user.phone}"

        binding.ivUserThumb.setOnClickListener {
            StfalconImageViewer.Builder<String>(
                context,
                arrayListOf(user.picture?.large)
            ) { view, image ->
                Glide.with(view)
                    .load(image)
                    .into(view)
            }.show()
        }
    }

}
