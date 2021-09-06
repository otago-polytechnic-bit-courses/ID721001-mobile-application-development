package op.mobile.app.dev.login.login

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import op.mobile.app.dev.login.model.Login

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Login>?) {
    val adapter = recyclerView.adapter as LoginAdapter
    adapter.submitList(data)
}