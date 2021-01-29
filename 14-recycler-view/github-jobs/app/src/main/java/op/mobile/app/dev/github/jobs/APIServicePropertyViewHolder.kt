package op.mobile.app.dev.github.jobs

import androidx.recyclerview.widget.RecyclerView
import op.mobile.app.dev.github.jobs.databinding.RecyclerViewItemBinding

class APIServicePropertyViewHolder(private var binding: RecyclerViewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(apiServiceProperty: APIServiceProperty) {
        binding.apiServiceProperty = apiServiceProperty
        binding.executePendingBindings()
    }
}