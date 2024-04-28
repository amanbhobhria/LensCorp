package com.example.lensassignment

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast

class OrganizationFragment : Fragment() {


    private lateinit var downloadButtons: List<ImageView>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_organization, container, false)


        // Initialize download buttons
        downloadButtons = listOf(
            view.findViewById(R.id.downloadButton1),
            view.findViewById(R.id.downloadButton2),

        )

        // Set click listeners for the download buttons
        downloadButtons.forEachIndexed { index, button ->
            button.setOnClickListener { downloadPDF(index) }
        }

        return view
    }


    private fun downloadPDF(index: Int) {
        val pdfUrl = when (index) {
            0 -> "https://res.cloudinary.com/dbxbqgkbz/image/upload/v1712652351/irpof/ORGANIZATION/CONSTITUTION/wqka5yfi2ioxjoaekj7j.pdf"
            1 -> "https://res.cloudinary.com/dbxbqgkbz/image/upload/v1712652351/irpof/ORGANIZATION/CONSTITUTION/wqka5yfi2ioxjoaekj7j.pdf"


            else -> return
        }

        // Create a DownloadManager.Request
        val request = DownloadManager.Request(Uri.parse(pdfUrl))
            .setTitle("Downloading PDF")
            .setDescription("Downloading your PDF file")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "downloaded_pdf_$index.pdf")

        // Get the DownloadManager instance and enqueue the download request
        val downloadManager = requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)

        // Show a Toast to indicate the download has started
        Toast.makeText(requireContext(), "Download started", Toast.LENGTH_SHORT).show()

    }



}