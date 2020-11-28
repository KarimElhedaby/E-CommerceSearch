package com.example.e_commerce_search.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e_commerce_search.app.data.ui_models.ProductResponse
import com.e_commerce_search.app.utils.loadImage
import com.e_commerce_search.app.utils.setSearchTextHighlightSimpleSpannable
import com.example.e_commerce_search.R
import kotlinx.android.synthetic.main.item_product_grid.view.*
import org.koin.core.KoinComponent


class ProductsAdapter(
    val data: MutableList<ProductResponse>,
    var searchText: String = ""
) : RecyclerView.Adapter<ProductsAdapter.ProductsAdapterViewHolder>(), KoinComponent {

    var isViewAllData = false


    inner class ProductsAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {

            itemView.setOnClickListener {
                //create any interaction listeners
            }
        }


        fun bind(item: ProductResponse) = with(itemView) {

            productImageView.loadImage(item.image)

            productTextView.text = item.name

            // highlight all searchText in TextView

            setSearchTextHighlightSimpleSpannable(
                productTextView,
                searchText
            )


            productRateTextView.text = item.rate.toString()
            productPriceTextView.text = """${item.price}$"""
            productCategoryTextView.text = item.category

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapterViewHolder {

        return ProductsAdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product_grid, null)
        )
    }

    override fun onBindViewHolder(holder: ProductsAdapterViewHolder, position: Int) =
        holder.bind(data[position])

    fun addSingleItemDataFromFirst(recivedData: ProductResponse) {
        data.add(0, recivedData)
        notifyItemInserted(0)
    }

    fun addSingleItemDataFromLast(receivedData: ProductResponse) {
        data.add(receivedData)
        notifyItemInserted(data.size)
    }


    fun addMultipleItemsData(receivedData: MutableList<ProductResponse>) {
        data.addAll(receivedData)
        notifyDataSetChanged()
    }


    fun swapDataWithSearchKey(
        receivedData: MutableList<ProductResponse>,
        searchKey: String
    ) {
        data.clear()
        data.addAll(receivedData)
        searchText = searchKey
        notifyDataSetChanged()
    }

    fun swapData(receivedData: MutableList<ProductResponse>) {
        data.clear()
        data.addAll(receivedData)
        notifyDataSetChanged()
    }


    fun removeSingleItemData(receivedData: ProductResponse) {
        data.remove(receivedData)
        notifyDataSetChanged()
    }


    fun removeAllData() {
        data.clear()
        notifyDataSetChanged()
    }

    fun changeDataCount() {
        if (data.size > 10) {
            isViewAllData = isViewAllData.not()
            notifyItemInserted(9)
        }
    }


    override fun getItemCount(): Int =
        if (isViewAllData && data.size > 10)
            data.size
        else if (!isViewAllData && data.size > 10)
            10
        else data.size ?: 0


}

