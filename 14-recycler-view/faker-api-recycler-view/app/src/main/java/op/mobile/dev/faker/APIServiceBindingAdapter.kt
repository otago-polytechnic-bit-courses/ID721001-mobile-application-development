package op.mobile.dev.faker

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<APIServiceProperty>?) {
    val adapter = recyclerView.adapter as APIServiceAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiServiceStatus")
fun bindAPIServiceStatus(statusTextView: TextView, status: APIServiceStatus?) {
    when (status) {
        APIServiceStatus.LOADING -> {
            statusTextView.visibility = View.VISIBLE
            statusTextView.text = statusTextView.context.getString(R.string.loading)
        }
        APIServiceStatus.ERROR -> {
            statusTextView.visibility = View.VISIBLE
            statusTextView.text = statusTextView.context.getString(R.string.connection_error)
        }
        APIServiceStatus.COMPLETE -> statusTextView.visibility = View.GONE
    }
}