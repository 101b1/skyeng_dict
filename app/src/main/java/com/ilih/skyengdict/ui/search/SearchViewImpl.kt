package com.ilih.skyengdict.ui.search

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ilih.skyengdict.databinding.FragmentSearchBinding
import com.ilih.skyengdict.di.MainComponent
import com.ilih.skyengdict.domain.dto.SearchResultDto

const val RECYCLER_THRESHOLD = 5

class SearchViewImpl(
    private val binding: FragmentSearchBinding,
    private val viewModel: SearchViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val mainComponent: MainComponent
) : SearchView, SearchResultAdapter.Listener {

    private lateinit var listener: SearchView.Listener
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var currentState: SearchState

    override fun onFinishInflate(listener: SearchView.Listener) {
        this.listener = listener
        initViews()
        setListeners()
    }

    override fun initViews() {
        layoutManager = LinearLayoutManager(mainComponent.getContext(), RecyclerView.VERTICAL, false)
        binding.recyclerSearch.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0 || currentState is SearchLoading) return

                val visibleItemCount = recyclerView.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if (totalItemCount - visibleItemCount <= firstVisibleItem + RECYCLER_THRESHOLD) {
                    listener.search(binding.editTextSearch.text.toString().trim())
                }
            }
        })
        viewModel.getData().observe(lifecycleOwner) {
            if (binding.recyclerSearch.adapter == null) {
                binding.recyclerSearch.layoutManager = layoutManager
                binding.recyclerSearch.adapter = SearchResultAdapter(ArrayList(it), this)
            } else {
                (binding.recyclerSearch.adapter as SearchResultAdapter).updateData(ArrayList(it))
            }
        }
        viewModel.getState().observe(lifecycleOwner) {
            currentState = it
            when (it) {
                is SearchError -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(mainComponent.getContext(), it.error, Toast.LENGTH_LONG).show()
                }
                is SearchLoading -> {
                    binding.progress.visibility = View.VISIBLE
                }
                is SearchSuccess -> {
                    binding.progress.visibility = View.GONE
                }
                is SearchNoResults -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(mainComponent.getContext(), "Нет результатов", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun setListeners() {
        binding.buttonSearch.setOnClickListener {
            listener.search(binding.editTextSearch.text.toString().trim())
        }
    }

    override fun onItemClicked(row: SearchResultDto) {
        mainComponent.getRouter().navToMeaning(row)
    }
}