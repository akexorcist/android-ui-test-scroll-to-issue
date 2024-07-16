package com.akexorcist.uitest.issue.scrollto.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akexorcist.uitest.scrollto.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        private const val EXTRA_TARGET = "target"

        fun newIntent(context: Context, target: String) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_TARGET, target)
            }
    }

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.textViewRequest.text = intent.getStringExtra(EXTRA_TARGET)
    }
}
