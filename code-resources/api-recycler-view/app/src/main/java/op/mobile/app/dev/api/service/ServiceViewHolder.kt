package op.mobile.app.dev.api.service

import androidx.recyclerview.widget.RecyclerView
import op.mobile.app.dev.api.databinding.RecyclerViewItemBinding
import op.mobile.app.dev.api.model.Country

class ServiceViewHolder(private var binding: RecyclerViewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(country: Country) {
        binding.country = country
        binding.executePendingBindings()
    }
}