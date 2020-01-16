package com.example.meetfriends3.ui.tools

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.meetfriends3.R
import kotlinx.android.synthetic.main.fragment_invite.*
import android.content.ClipboardManager
import android.content.ClipData
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Context
import com.example.meetfriends3.MainActivity


class InviteFragment : Fragment() {

    private lateinit var toolsViewModel: ToolsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toolsViewModel =
            ViewModelProviders.of(this).get(ToolsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_invite, container, false).findViewById<Button>(
            R.id.buttonCopy)
        //val textView: TextView = root.findViewById(R.id.text_tools)
        //toolsViewModel.text.observe(this, Observer {
         //   textView.text = it
        //})
        root.setOnClickListener(this)

        return root
    }
}

private fun Button.setOnClickListener(inviteFragment: InviteFragment) {

}
