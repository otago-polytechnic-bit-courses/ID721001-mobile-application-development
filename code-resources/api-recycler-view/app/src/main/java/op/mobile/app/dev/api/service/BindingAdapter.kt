package op.mobile.app.dev.api.service

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import op.mobile.app.dev.api.R
import op.mobile.app.dev.api.model.Country

@BindingAdapter("service_status")
fun bindServiceStatus(tvStatus: TextView, status: ServiceStatus?) {
    when (status) {
        ServiceStatus.LOADING -> {
            tvStatus.visibility = View.VISIBLE
            tvStatus.text = tvStatus.context.getString(R.string.loading)
        }
        ServiceStatus.ERROR -> {
            tvStatus.visibility = View.VISIBLE
            tvStatus.text = tvStatus.context.getString(R.string.connection_error)
        }
        ServiceStatus.COMPLETE -> tvStatus.visibility = View.GONE
    }
}

@BindingAdapter("list_data")
fun bindListData(recyclerView: RecyclerView, data: List<Country>?) {
    val adapter = recyclerView.adapter as ServiceAdapter
    adapter.submitList(data)
}