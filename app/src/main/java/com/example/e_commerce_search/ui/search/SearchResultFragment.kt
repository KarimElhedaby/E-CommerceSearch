package com.example.e_commerce_search.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.e_commerce_search.app.utils.ItsText
import com.e_commerce_search.app.utils.secretB
import com.e_commerce_search.app.utils.showB
import com.example.e_commerce_search.R
import com.example.e_commerce_search.ui.main.MainActivity
import com.example.e_commerce_search.ui.main.MainViewModel
import com.example.e_commerce_search.ui.search.adapter.ProductsAdapter
import kotlinx.android.synthetic.main.search_border_item.*
import kotlinx.android.synthetic.main.search_result_fragment.*

class SearchResultFragment : Fragment() {

    var layoutManager: GridLayoutManager? = null
    var adapter: ProductsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.search_result_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val activity = activity as MainActivity

        setUpRecyclerView()

        // have all view controllers
        setUpController()

        /* That way, when the fragments each get the ViewModelProvider,
        // they receive the same SharedViewModel instance,
         which is scoped to this activity.
        */

        val viewModel: MainViewModel by activityViewModels()

        viewModel.getSearchResults().observe(
            viewLifecycleOwner, Observer { searchResults ->
                if (searchResults.isNullOrEmpty()) {
                    emptyLayout.showB()
                    resultLayout.secretB()
                    // check case to  visible  sea more


                } else {
                    emptyLayout.secretB()
                    resultLayout.showB()
                    toTopButton.secretB()

                    if (searchResults.size > 10)
                        seeMoreButton.showB()
                    else
                        seeMoreButton.secretB()

                    adapter?.swapDataWithSearchKey(
                        searchResults,
                        activity.searchEditText.ItsText()
                    )
                }
            })
    }


    private fun setUpRecyclerView() {
        layoutManager = GridLayoutManager(context, 2)
        searchRecyclerView?.layoutManager = layoutManager
        adapter = ProductsAdapter(mutableListOf())
        searchRecyclerView.adapter = adapter
    }


    private fun setUpController() {

        seeMoreButton.setOnClickListener {
            seeMoreButton.secretB()
            toTopButton.showB()
            adapter?.changeDataCount()
        }

        //smooth scroll to top
        toTopButton.setOnClickListener {
            searchRecyclerView.smoothScrollToPosition(0)
        }
    }

    companion object {
        fun newInstance() = SearchResultFragment()
    }

}