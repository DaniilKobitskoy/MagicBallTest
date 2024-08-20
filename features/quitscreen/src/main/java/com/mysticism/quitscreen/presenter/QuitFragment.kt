package com.mysticism.quitscreen.presenter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.mysticism.quitscreen.R
import com.mysticism.quitscreen.databinding.FragmentQuitBinding
import org.koin.android.ext.android.inject

class QuitFragment : Fragment(), QuitContract.View {

    private val presenter: QuitPresenter by inject()
    private lateinit var webView: WebView
    lateinit var binding: FragmentQuitBinding
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

        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            mediaPlaybackRequiresUserGesture = false
        }

        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()

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
        // Реализуйте показ ошибки
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