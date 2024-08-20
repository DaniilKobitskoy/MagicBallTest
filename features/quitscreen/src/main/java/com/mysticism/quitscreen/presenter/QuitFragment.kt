package com.mysticism.quitscreen.presenter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import com.mysticism.core.media.MusicPlayerManager
import com.mysticism.quitscreen.databinding.FragmentQuitBinding
import org.koin.android.ext.android.inject

class QuitFragment : Fragment(), QuitContract.View {

    private val presenter: QuitPresenter by inject()
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var binding: FragmentQuitBinding
    private val router: Router by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onViewReady()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = binding.webview
        progressBar = binding.progressBar
        Toast.makeText(requireContext(), "Wait please...", Toast.LENGTH_SHORT).show()
        MusicPlayerManager.initialize(requireContext())
        MusicPlayerManager.stop()
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            mediaPlaybackRequiresUserGesture = false
        }

        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                webView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                showError("Failed to load page. Check internet connection.")
            }
        }

        webView.loadUrl("https://youtu.be/KM8rr3XJpZY?si=3cxms2HosRWKJJ2L")
    }

    override fun handleBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        router.exit()
    }

    override fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
            }
        }
    }

    companion object {
        private const val REQUEST_IMAGE_PICK = 1001

        fun newInstance(): QuitFragment {
            return QuitFragment()
        }
    }
}