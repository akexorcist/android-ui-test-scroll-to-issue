package com.akexorcist.uitest.issue.scrollto.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.akexorcist.uitest.issue.scrollto.ui.detail.DetailActivity
import com.akexorcist.uitest.scrollto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context) =
            Intent(context, MainActivity::class.java)
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    private val primaryAdapter: PrimaryAdapter by lazy {
        PrimaryAdapter(
            onItemButtonClickListener = { target: String ->
                startActivity(DetailActivity.newIntent(context = this, target = target))
            },
            onDetailButtonClickListener = { target: String ->
                startActivity(DetailActivity.newIntent(context = this, target = target))
            },
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerViewPrimary.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = primaryAdapter
        }

        viewModel.sampleData.observe(this) { data ->
            primaryAdapter.setTypes(data)
        }

        if (savedInstanceState == null) {
            viewModel.loadData()
        }
    }
}
