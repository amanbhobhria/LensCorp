package com.example.lensassignment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView

import androidx.viewpager2.widget.ViewPager2


class HomeFragment : Fragment() {
    private lateinit var viewPager: ViewPager2
    // Declare button references
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button


    // Declare views for heading text, full text, and close button
    private lateinit var headingText: TextView
    private lateinit var fullText: TextView
    private lateinit var crossButton: ImageView


    // Declare the full text container
    private lateinit var fullTextContainer: LinearLayout

    // Initialize a flag to track whether the text is visible
    private var isTextVisible: Boolean = false

    private lateinit var imageList: List<Int>




    private val handler = Handler(Looper.getMainLooper())
    private val slideRunnable: Runnable = Runnable {
        slideImages()
    }
    private var currentImageIndex = 0






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
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        viewPager = view.findViewById(R.id.viewPager)


         imageList = listOf(
            R.drawable.img1,  // Replace with your image resource ID
            R.drawable.img2,  // Replace with your image resource ID
            R.drawable.img3   // Replace with your image resource ID
        )


        val adapter = ImagePagerAdapter(imageList)
        viewPager.adapter = adapter


        // Start the image slider
        startImageSlider()



        val movingTextView = view.findViewById<TextView>(R.id.movingTextView)

        // Get the width of the screen
        val screenWidth = resources.displayMetrics.widthPixels

        // Set up the translation animation
        val animation = TranslateAnimation(
            Animation.ABSOLUTE, screenWidth.toFloat(), // From X: Starting from the right edge of the screen
            Animation.ABSOLUTE, -movingTextView.width.toFloat(), // To X: Moving off the left side of the screen
            Animation.ABSOLUTE, 0f, // From Y: No vertical movement
            Animation.ABSOLUTE, 0f
        )

        // Set animation duration (in milliseconds) and repetition
        animation.duration = 5000 // Duration of the animation in milliseconds
        animation.repeatCount = Animation.INFINITE // Repeat the animation indefinitely
        animation.repeatMode = Animation.RESTART // Restart the animation when it ends

        // Start the animation
        movingTextView.startAnimation(animation)


        // Initialize button references
        button1 = view.findViewById(R.id.button1)
        button2 = view.findViewById(R.id.button2)
        button3 = view.findViewById(R.id.button3)
        button4 = view.findViewById(R.id.button4)
        button5 = view.findViewById(R.id.button5)

        // Initialize views for heading text, full text, and close button
        headingText = view.findViewById(R.id.headingText)
        fullText = view.findViewById(R.id.fullText)
        crossButton = view.findViewById(R.id.backButton)

        // Initialize the full text container
        fullTextContainer = view.findViewById(R.id.fullTextContainer)

        // Set initial visibility of the full text container to hidden
        fullTextContainer.visibility = View.GONE

        // Set OnClickListener for each button
        setButtonClickListeners()






        return view
    }


    // Set OnClickListener for each button
    private fun setButtonClickListeners() {
        // Define a lambda function that will handle the button clicks
        val buttonClickHandler: (String, String) -> Unit = { heading, paragraph ->
            displayFullText(heading, paragraph)
        }

        // Assign click listeners and corresponding heading and paragraph texts
        button1.setOnClickListener { buttonClickHandler("Indian Railway Promotee Officers Federation (IRPOF)",requireContext().getString(R.string.full_txt1)
        ) }
        button2.setOnClickListener { buttonClickHandler("Our Mission", requireContext().getString(R.string.full_txt2))}
        button3.setOnClickListener { buttonClickHandler("Recent Events",requireContext().getString(R.string.full_txt3))}
        button4.setOnClickListener { buttonClickHandler("Images", requireContext().getString(R.string.full_txt4))}
        button5.setOnClickListener { buttonClickHandler("Videos", requireContext().getString(R.string.full_txt5))}

        // Set OnClickListener for the cross button to hide the full text
        crossButton.setOnClickListener {
            hideText()
        }
    }

    // Function to display the full text and heading
    private fun displayFullText(heading: String, text: String) {
        headingText.text = heading
        fullText.text = text

        // Hide other buttons
        hideButtons()


        fullTextContainer.visibility = View.VISIBLE
        fullTextContainer.alpha = 0.0f
        fullTextContainer.animate()
            .alpha(1.0f)
            .setDuration(300)
            .start()
        isTextVisible = true
    }

    // Function to hide the full text
    private fun hideText() {
        fullTextContainer.animate()
            .alpha(0.0f)
            .setDuration(300)
            .withEndAction {
                fullTextContainer.visibility = View.GONE
                // Show other buttons
                showButtons()


            }
            .start()
        isTextVisible = false
    }


    // Function to hide all buttons
    private fun hideButtons() {
        button1.visibility = View.GONE
        button2.visibility = View.GONE
        button3.visibility = View.GONE
        button4.visibility = View.GONE
        button5.visibility = View.GONE
    }

    // Function to show all buttons
    private fun showButtons() {
        button1.visibility = View.VISIBLE
        button2.visibility = View.VISIBLE
        button3.visibility = View.VISIBLE
        button4.visibility = View.VISIBLE
        button5.visibility = View.VISIBLE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        // Remove the handler callbacks when the view is destroyed
        handler.removeCallbacks(slideRunnable)
    }

    // Function to start the image slider
    private fun startImageSlider() {
        // Start the image slider by posting the Runnable with a 4-second delay
        handler.postDelayed(slideRunnable, 4000)
    }

    // Function to slide the images
    private fun slideImages() {
        currentImageIndex++
        if (currentImageIndex >= imageList.size) {
            currentImageIndex = 0
        }
        // Slide the ViewPager2 to the next image
        viewPager.setCurrentItem(currentImageIndex, true)

        // Post the Runnable again with a 4-second delay to slide the next image
        handler.postDelayed(slideRunnable, 4000)
    }




    private class ImagePagerAdapter(private val images: List<Int>) : RecyclerView.Adapter<ImagePagerAdapter.ViewHolder>() {

        class ViewHolder(itemView: AppCompatImageView) : RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val imageView = AppCompatImageView(parent.context)
            imageView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            return ViewHolder(imageView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val imageView = holder.itemView as ImageView
            imageView.setImageResource(images[position])
        }

        override fun getItemCount(): Int = images.size
    }


}