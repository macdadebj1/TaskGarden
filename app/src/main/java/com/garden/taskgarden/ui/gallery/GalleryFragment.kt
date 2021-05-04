package com.garden.taskgarden.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.garden.taskgarden.R

class GalleryFragment : Fragment() {
    private var galleryViewModel: GalleryViewModel? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //galleryViewModel = ViewModelProvider(this).get(GalleryViewModel)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val textView = root.findViewById<TextView>(R.id.text_gallery)
        //galleryViewModel.getText().observe(viewLifecycleOwner, { s -> textView.text = s })
        return root
    }
}