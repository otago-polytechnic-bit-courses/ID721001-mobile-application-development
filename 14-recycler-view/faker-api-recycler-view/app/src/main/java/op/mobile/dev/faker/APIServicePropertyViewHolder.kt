package op.mobile.dev.faker

import androidx.recyclerview.widget.RecyclerView
import op.mobile.dev.faker.databinding.RecyclerViewItemBinding

class APIServicePropertyViewHolder(private var binding: RecyclerViewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(apiServiceProperty: APIServiceProperty) {
        binding.apiServiceProperty = apiServiceProperty
        binding.executePendingBindings()
    }
}